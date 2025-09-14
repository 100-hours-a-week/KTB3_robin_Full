package domain.game;

import domain.message.ErrorMessages;
import domain.message.GameMessages;
import domain.validation.InputValidator;
import domain.player.Player;
import service.MappingService;
import service.RandomNumberService;
import service.player.PlayerInitService;
import service.player.PlayerActionService;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;


public class GameManager  {

    static Player player;
    static InputView inView = new InputView();
    static OutputView outView = new OutputView();

    public void startGame() throws InterruptedException {
        if (!initPlayer()) {
            return;
        }
        startMatch();
        printResult();
    }

    // 선수 등록 및 초기화 (이름, 구단, 포지션, 능력치)
    private boolean initPlayer() throws InterruptedException {
        InputValidator inputValidator = new InputValidator();

        outView.printWelcomeMessage();

        outView.printGetNameMessage();
        String name = inView.getPlayerName();
        if (!inputValidator.nameValidator(name)) {
            return false;
        }

        outView.printGetTeamNumber();
        int teamNumber = inView.getTeamNumber();
        if (!inputValidator.teamValidator(teamNumber)) {
            return false;
        }

        outView.printGetPosition();
        int positionNumber = inView.getPositionNumber();
        if (!inputValidator.positionValidator(positionNumber)) {
            return false;
        }

        PlayerInitService playerInitService = new PlayerInitService();
        player = playerInitService.playerInit(name, teamNumber, positionNumber);
        outView.printBeforeMatch(player.getName(), player.getTeamName());
        return true;
    }

    // 게임 진행
    private void startMatch() throws InterruptedException {
        MappingService mappingService = new MappingService();
        RandomNumberService randomNumberService = new RandomNumberService();
        PlayerActionService playerActionService = new PlayerActionService();
        InputValidator inputValidator = new InputValidator();

        for (int i = 0; i < 4; i++) {
            int number = randomNumberService.oneToFour(); // 게임 상황 4 가지를 숫자로 리턴
            ArrayList<Integer> availableActionNumbers = new ArrayList<>();

            switch (number) { // number 에 맞게 상황 설명을 view 에서 출력
                case 1:
                    outView.printOneVoneChanceSituation(player.getName(), player.getTeamName());
                    // 각 역할군에 맞게 할 수 있는 행동들 나열
                    availableActionNumbers = playerActionService.getAvailableActionNumbers(player, number);
                    outView.printAvailableActions(availableActionNumbers, mappingService);
                    handleActionInputLoop(availableActionNumbers, inputValidator, mappingService, playerActionService);
                    // 행동의 결과를 결과표에 기록
                    break;
                case 2:
                    outView.printNearByBoxSituation(player.getName(), player.getTeamName());
                    availableActionNumbers = playerActionService.getAvailableActionNumbers(player, number);
                    outView.printAvailableActions(availableActionNumbers, mappingService);
                    handleActionInputLoop(availableActionNumbers, inputValidator, mappingService, playerActionService);
                    break;
                case 3:
                    outView.printCounterattackSituation(player.getName(), player.getTeamName());
                    availableActionNumbers = playerActionService.getAvailableActionNumbers(player, number);
                    outView.printAvailableActions(availableActionNumbers, mappingService);
                    handleActionInputLoop(availableActionNumbers, inputValidator, mappingService, playerActionService);
                    break;
                default:
                    outView.printDefendSituation(player.getName(), player.getTeamName());
                    availableActionNumbers = playerActionService.getAvailableActionNumbers(player, number);
                    outView.printAvailableActions(availableActionNumbers, mappingService);
                    handleActionInputLoop(availableActionNumbers, inputValidator, mappingService, playerActionService);
            }
        }
    }

    // 결과 집계 및 메세지 출력
    private void printResult() {
        String name = player.getName();
        outView.printResult(name);
    }

    // 올바른 행동 번호가 들어올 때까지 반복하고, 성공 시
    private void handleActionInputLoop(ArrayList<Integer> availableActionNumbers,
                                       InputValidator inputValidator,
                                       MappingService mappingService,
                                       PlayerActionService playerActionService) throws InterruptedException {
        while (true) {
            System.out.print(GameMessages.askActionNumber);
            int chosenNumber = inView.getActionToTake();

            if (inputValidator.actionValidator(chosenNumber, availableActionNumbers)) {
                int result = playerActionService.execute(player, chosenNumber);
                String actionName = mappingService.getActionName(chosenNumber);

                // 제대로 구현된 성공/실패 로직이 있었다면, 아래의 코드가 필요함
                if (result == 1) {
                    System.out.println(actionName + GameMessages.actionSuccess);
                } else {
                    System.out.println(actionName + GameMessages.actionFail);
                    continue;
                }
                break;
            } else {
                System.out.println(ErrorMessages.actionInputRetry);
            }
        }
        Thread.sleep(1000);
    }
}
