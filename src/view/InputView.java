package view;

import exception.InputIsNotValidNumberException;

import java.util.Scanner;

public class InputView {
    static Scanner sc = new Scanner(System.in);

    public String getPlayerName() {
        return sc.nextLine();
    }
    public int getTeamNumber() {
        return readInt();
    }
    public int getPositionNumber() {
        return readInt();
    }

    // 가능한 행동 중 번호 하나를 입력받기
    public int getActionToTake() {
        return readInt();
    }

    private int readInt() {
        String input = sc.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputIsNotValidNumberException("유효한 숫자를 입력해 주세요: '" + input + "'");
        }
    }
}
