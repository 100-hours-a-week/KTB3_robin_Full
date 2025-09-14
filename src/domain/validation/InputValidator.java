package domain.validation;

import domain.message.ErrorMessages;

import java.util.List;

public class InputValidator {

    // 유효한 사용자 이름인지 검증
    public boolean nameValidator(String name) {
        if(name.length() > 15 || name.isEmpty()) {
            System.out.println("이름" + ErrorMessages.commonInputError);
            return false;
        }
        return true;
    }

    // 유효한 팀 번호인지 검증
    public boolean teamValidator(int teamNumber) {
        if(teamNumber < 1 || teamNumber > 6) {
            System.out.println("팀 번호" + ErrorMessages.commonInputError);
            return false;
        }
        return true;
    }

    // 유효한 포지션 번호인지 검증
    public boolean positionValidator(int positionNumber) {
        if(positionNumber < 1 || positionNumber > 7) {
            System.out.println("포지션 번호" + ErrorMessages.commonInputError);
            return false;
        }
        return true;
    }

    // 입력한 행동 번호가 가능한 행동 번호인지 검증
    public boolean actionValidator(int actionNumber, List<Integer> availableActionNumbers) {
        return availableActionNumbers.contains(actionNumber);
    }
}
