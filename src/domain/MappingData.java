package domain;

import domain.player.Position;
import domain.player.Team;

import java.util.*;

public class MappingData {
    // 숫자-팀 엔트리 저장
    private final HashMap<Integer, Team> teamByNumberMap;
    // 숫자-포지션 엔트리 저장
    private final HashMap<Integer, Position> positionByNumberMap;
    // 행동마다 숫자-문자열로 매핑
    private final HashMap<Integer, String> actionsByNumberMap;
    // 역할군-가능행동 번호 list 매핑
    private final HashMap<Position, ArrayList<Integer>> actionsByPositionMap;
    // 상황-가능행동 번호 list 매핑
    private final HashMap<Integer, ArrayList<Integer>> actionBySituation;

    public MappingData() {
        teamByNumberMap = new HashMap<>();
        positionByNumberMap = new HashMap<>();
        actionsByNumberMap = new HashMap<>();
        actionsByPositionMap = new HashMap<>();
        actionBySituation = new HashMap<>();

        teamByNumberMap.put(1, Team.ARSENAL);
        teamByNumberMap.put(2, Team.LIVERPOOL);
        teamByNumberMap.put(3, Team.MANCITY);
        teamByNumberMap.put(4, Team.CHELSEA);
        teamByNumberMap.put(5, Team.TOTTENHAM);
        teamByNumberMap.put(6, Team.MANUNITED);

        positionByNumberMap.put(1, Position.STIKER);
        positionByNumberMap.put(2, Position.WINGER);
        positionByNumberMap.put(3, Position.ATTACKING_MIDFIELDER);
        positionByNumberMap.put(4, Position.CENTRAL_MIDFIELDER);
        positionByNumberMap.put(5, Position.DEFENSIVE_MIDFIELDER);
        positionByNumberMap.put(6, Position.FULLBACK);
        positionByNumberMap.put(7, Position.CENTERBACK);

        // 1~4 : 전 포지션 공통
        actionsByNumberMap.put(1, "패스");
        actionsByNumberMap.put(2, "슈팅");
        actionsByNumberMap.put(3, "태클");
        actionsByNumberMap.put(4, "드리블 돌파");
        // 5 : 공격수
        actionsByNumberMap.put(5, "침착한 슛");
        // 6 : 미드필더
        actionsByNumberMap.put(6, "정확한 패스");
        // 7 : 수비수
        actionsByNumberMap.put(7, "침착한 태클");
        // 8 : 윙어
        actionsByNumberMap.put(8, "개인기 돌파");
        // 9 : 스트라이커
        actionsByNumberMap.put(9, "무회전 슛");
        // 10 : 공격형 미드필더
        actionsByNumberMap.put(10, "감아차기");
        // 11 : 중앙 미드필더
        actionsByNumberMap.put(11, "대지를 가르는 패스");
        // 12 : 수비형 미드필더
        actionsByNumberMap.put(12, "볼 리커버리");
        // 13 : 풀백 
        actionsByNumberMap.put(13, "패스 앤 무브");
        // 14 : 센터백
        actionsByNumberMap.put(14, "강력한 몸싸움");

        // 전 포지션 기본 동작 번호 리스트(1~4)
        List<Integer> baseActions = List.of(1, 2, 3, 4);
        actionsByPositionMap.put(Position.WINGER, new ArrayList<>(baseActions));
        actionsByPositionMap.put(Position.STIKER, new ArrayList<>(baseActions));
        actionsByPositionMap.put(Position.ATTACKING_MIDFIELDER, new ArrayList<>(baseActions));
        actionsByPositionMap.put(Position.CENTRAL_MIDFIELDER, new ArrayList<>(baseActions));
        actionsByPositionMap.put(Position.DEFENSIVE_MIDFIELDER, new ArrayList<>(baseActions));
        actionsByPositionMap.put(Position.FULLBACK, new ArrayList<>(baseActions));
        actionsByPositionMap.put(Position.CENTERBACK, new ArrayList<>(baseActions));

        // 각 포지션 마다 할 수 있는 동작
        actionsByPositionMap.get(Position.WINGER).add(5);
        actionsByPositionMap.get(Position.WINGER).add(8);

        actionsByPositionMap.get(Position.STIKER).add(5);
        actionsByPositionMap.get(Position.STIKER).add(9);

        actionsByPositionMap.get(Position.ATTACKING_MIDFIELDER).add(6);
        actionsByPositionMap.get(Position.ATTACKING_MIDFIELDER).add(10);

        actionsByPositionMap.get(Position.CENTRAL_MIDFIELDER).add(6);
        actionsByPositionMap.get(Position.CENTRAL_MIDFIELDER).add(11);

        actionsByPositionMap.get(Position.DEFENSIVE_MIDFIELDER).add(6);
        actionsByPositionMap.get(Position.DEFENSIVE_MIDFIELDER).add(12);

        actionsByPositionMap.get(Position.FULLBACK).add(7);
        actionsByPositionMap.get(Position.FULLBACK).add(13);

        actionsByPositionMap.get(Position.CENTERBACK).add(7);
        actionsByPositionMap.get(Position.CENTERBACK).add(14);

        // 각 상황마다 할 수 있는 행동 매핑
        actionBySituation.put(1, new ArrayList<>()); // 골키퍼 1:1
        actionBySituation.put(2, new ArrayList<>()); // 박스 근처
        actionBySituation.put(3, new ArrayList<>()); // 역습
        actionBySituation.put(4, new ArrayList<>()); // 수비 상황

        ArrayList<Integer> actionList = actionsByPositionMap.get(1);
        actionList.add(2); // 슈팅
        actionList.add(5); // 침착한 슛

        actionList = actionsByPositionMap.get(2);
        actionList.add(1); // 패스
        actionList.add(2); // 슈팅

        actionList.add(5); // 침착한 슛
        actionList.add(8); // 개인기 돌파
        actionList.add(9); // 무회전 슛

        actionList.add(6); // 정확한 패스
        actionList.add(10); // 감아차기

        actionList = actionsByPositionMap.get(3);
        actionList.add(1); // 패스
        actionList.add(11); // 대지를 가르는 패스
        actionList.add(13); // 패스 앤 무브

        actionList = actionsByPositionMap.get(4);
        actionList.add(3); // 태클
        actionList.add(12); // 볼 리커버리
        actionList.add(7); // 침착한 태클
        actionList.add(14); // 강력한 몸싸움
    }

    public Team getTeamEnumValue(int teamNumber) {
        return teamByNumberMap.get(teamNumber);
    }
    public Position getPositionEnumValue(int positionNumber) {
        return positionByNumberMap.get(positionNumber);
    }
    public String getActionName(int actionNumber) {
        return actionsByNumberMap.get(actionNumber);
    }
}
