package view;

import java.util.Scanner;

public class InputView {
    static Scanner sc = new Scanner(System.in);

    public String getPlayerName() {
        return sc.nextLine();
    }
    public int getTeamNumber() {
        return sc.nextInt();
    }
    public int getPositionNumber() {
        return sc.nextInt();
    }

    // 가능한 행동 중 번호 하나를 입력받기
    public int getActionToTake() {
        return sc.nextInt();
    }
}
