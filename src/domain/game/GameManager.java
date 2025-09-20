package domain.game;

import domain.message.ErrorMessages;
import domain.message.GameMessages;
import domain.player.Position;
import domain.player.Team;
import domain.validation.InputValidator;
import domain.player.Player;
import exception.InputIsNotValidNumberException;
import service.MappingDataService;
import service.RandomNumberService;
import service.player.PlayerInitService;
import service.player.PlayerActionService;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;


public class GameManager {

    private final InputView inView;
    private final OutputView outView;
    private final InputValidator inputValidator;
    private final MappingDataService mappingDataService;
    private final PlayerInitService playerInitService;
    private final PlayerActionService playerActionService;
    private final RandomNumberService randomNumberService;
    private final MatchClock matchClock;

    private Player player;

    public GameManager(
        InputView inView,
        OutputView outView,
        InputValidator inputValidator,
        MappingDataService mappingDataService,
        PlayerInitService playerInitService,
        PlayerActionService playerActionService,
        RandomNumberService randomNumberService,
        MatchClock matchClock) {

        this.inView = inView;
        this.outView = outView;
        this.inputValidator = inputValidator;
        this.mappingDataService = mappingDataService;
        this.playerInitService = playerInitService;
        this.playerActionService = playerActionService;
        this.randomNumberService = randomNumberService;
        this.matchClock = matchClock;
    }

    public void startGame() {
        initPlayer();
        Thread matchClockThread = new Thread(matchClock);
        matchClockThread.start();
        startMatch();
        printResult();
    }

    // 선수 등록 및 초기화 (이름, 구단, 포지션, 능력치)
    private void initPlayer() {

        outView.printWelcomeMessage();

        // 이름 입력
        String name;
        while(true) {
            outView.printGetNameMessage();
            name = inView.getPlayerName();
            if (!inputValidator.nameValidator(name)) {
                continue; // 재입력
            }
            break;
        }

        // 팀 번호 입력
        int teamNumber;
        while(true) {
            outView.printGetTeamNumber();
            try {
                teamNumber = inView.getTeamNumber();
                if (!inputValidator.teamValidator(teamNumber)) {
                    continue; // 재입력
                }
                break;
            } catch (InputIsNotValidNumberException e) {
                System.out.println(e.getMessage());
            }
        }

        // 포지션 번호 입력
        int positionNumber;
        while(true) {
            outView.printGetPosition();
            try {
                positionNumber = inView.getPositionNumber();
                if (!inputValidator.positionValidator(positionNumber)) {
                    continue; // 재입력
                }
                break;
            } catch (InputIsNotValidNumberException e) {
                System.out.println(e.getMessage());
            }
        }

        Team team = mappingDataService.getTeamEnumValue(teamNumber);
        Position position = mappingDataService.getPositionEnumValue(positionNumber);
        player = playerInitService.playerInit(name, team, position);
        outView.printBeforeMatch(player.getName(), player.getTeamName());
    }

    // 게임 진행
    private void startMatch() {

        while(true) {
            boolean isRunning = matchClock.getIsRunning();
            if(!isRunning) {
                break;
            }
            outView.printCurrentTime(matchClock.getClockTime());

            int situationNumber = randomNumberService.oneToFour(); // 게임 상황 4 가지를 숫자로 리턴
            ArrayList<Integer> availableActionNumbers;
            String playerName = player.getName();
            String teamName = player.getTeamName();
            switch (situationNumber) { // number 에 맞게 상황 설명을 view 에서 출력
                case 1:
                    outView.printOneVoneChanceSituation(playerName, teamName);
                    break;
                case 2:
                    outView.printNearByBoxSituation(playerName, teamName);
                    break;
                case 3:
                    outView.printCounterattackSituation(playerName, teamName);
                    break;
                default:
                    outView.printDefendSituation(playerName, teamName);
            }
            // 각 역할군에 맞게 할 수 있는 행동들 나열
            availableActionNumbers = playerActionService.getAvailableActionNumbers(player.getPosition(), situationNumber);
            // 행동의 결과를 결과표에 기록
            handleActionInputLoop(availableActionNumbers, inputValidator, mappingDataService, playerActionService);
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
                                       MappingDataService mappingDataService,
                                       PlayerActionService playerActionService) {
        while (true) {
            outView.printAvailableActions(availableActionNumbers, mappingDataService);
            System.out.print(GameMessages.askActionNumber);
            int chosenNumber;
            try  {
                chosenNumber = inView.getActionToTake();
            } catch (InputIsNotValidNumberException e) {
                System.out.println(e.getMessage());
                continue; // 재입력
            }

            if (inputValidator.actionValidator(chosenNumber, availableActionNumbers)) {
                int result = playerActionService.execute(player, chosenNumber);
                String actionName = mappingDataService.getActionName(chosenNumber);

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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
