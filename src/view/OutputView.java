package view;

import domain.message.GameMessages;
import service.MappingService;

import java.lang.reflect.Method;
import java.util.List;

public class OutputView {
    StringBuilder sb = new StringBuilder();

    public void printWelcomeMessage() {
        System.out.println(GameMessages.welcome);
    }

    public void printGetNameMessage() {
        System.out.println(GameMessages.whatIsYourName);
    }

    public void printGetTeamNumber() {
        System.out.println(GameMessages.chooseYourTeam);
    }

    public void printGetPosition() {
        System.out.println(GameMessages.chooseYourPosition);
    }

    public void printBeforeMatch(String name, String teamName) {
        sb.append(name)
            .append(" 은(는) ")
            .append(teamName)
            .append(GameMessages.matchStart)
            .append(GameMessages.howToPlay);

        System.out.println(sb);
        sb.setLength(0);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public void printOneVoneChanceSituation(String name, String teamName) {
        sb.append(teamName)
            .append("의 ")
            .append(name)
            .append("선수...\n")
            .append(GameMessages.oneVoneSituation);

        System.out.println(sb);
        sb.setLength(0);
    }

    public void printNearByBoxSituation(String name, String teamName) {
        sb.append(teamName)
            .append("의 ")
            .append(name)
            .append("선수...\n")
            .append(GameMessages.nearByBoxSituation);

        System.out.println(sb);
        sb.setLength(0);
    }

    public void printCounterattackSituation(String name, String teamName) {
        sb.append(teamName)
            .append("의 ")
            .append(name)
            .append("선수...\n")
            .append(GameMessages.counterattackSituation);

        System.out.println(sb);
        sb.setLength(0);
    }

    public void printDefendSituation(String name, String teamName) {
        sb.append(teamName)
            .append("의 ")
            .append(name)
            .append("선수...\n")
            .append(GameMessages.defendSituation);

        System.out.println(sb);
        sb.setLength(0);
    }

    // 가능한 행동 목록 출력
    public void printAvailableActions(List<Integer> actionNumbers, MappingService mappingService) {
        sb.append("가능한 행동들:\n");
        for (int actionNum : actionNumbers) {
            String name = mappingService.getActionName(actionNum);
            sb.append(actionNum)
                .append(" : ")
                .append(name)
                .append("\n");
        }
        System.out.println(sb);
        sb.setLength(0);
    }

    // 결과를 출력
    public void printResult(String name) {
        sb.append(name).
            append(GameMessages.matchEnd);

        System.out.println(sb);
    }
}
