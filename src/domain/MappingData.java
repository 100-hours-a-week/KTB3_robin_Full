package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MappingData {
    // 행동마다 숫자-문자열로 매핑
    HashMap<Integer, String> actionByNumberMap = new HashMap<>();
    // 역할군-할수있는 행동 번호 list 매핑
    HashMap<String, ArrayList<Integer>> actionsByPositionMap = new HashMap<>();

    public MappingData() {
        // 1~4 : 전 포지션 공통
        actionByNumberMap.put(1, "패스");
        actionByNumberMap.put(2, "슈팅");
        actionByNumberMap.put(3, "태클");
        actionByNumberMap.put(4, "드리블 돌파");
        // 5 : 공격수
        actionByNumberMap.put(5, "침착한 슛");
        // 6 : 미드필더
        actionByNumberMap.put(6, "정교한 패스");
        // 7 : 수비수
        actionByNumberMap.put(7, "침착한 태클");
        // 8 : 윙어
        actionByNumberMap.put(8, "개인기 돌파");
        // 9 : 스트라이커
        actionByNumberMap.put(9, "무회전 슛");
        // 10 : 공격형 미드필더
        actionByNumberMap.put(10, "감아차기");
        // 11 : 중앙 미드필더
        actionByNumberMap.put(11, "대지를 가르는 패스");
        // 12 : 수비형 미드필더
        actionByNumberMap.put(12, "볼 리커버리");
        // 13 : 풀백 
        actionByNumberMap.put(13, "패스 앤 무브");
        // 14 : 센터백
        actionByNumberMap.put(14, "강력한 몸싸움");


        // 전 포지션 기본 동작(1~4)
        List<Integer> baseActions = List.of(1, 2, 3, 4);
        actionsByPositionMap.put("Winger", new ArrayList<>(baseActions));
        actionsByPositionMap.put("Striker", new ArrayList<>(baseActions));
        actionsByPositionMap.put("AttackingMidFielder", new ArrayList<>(baseActions));
        actionsByPositionMap.put("CentralMidFielder", new ArrayList<>(baseActions));
        actionsByPositionMap.put("DefensiveMidFielder", new ArrayList<>(baseActions));
        actionsByPositionMap.put("FullBack", new ArrayList<>(baseActions));
        actionsByPositionMap.put("CenterBack", new ArrayList<>(baseActions));

        // 각 포지션 마다 할 수 있는 동작
        actionsByPositionMap.get("Winger").add(5);
        actionsByPositionMap.get("Winger").add(8);

        actionsByPositionMap.get("Striker").add(5);
        actionsByPositionMap.get("Striker").add(9);

        actionsByPositionMap.get("AttackingMidFielder").add(6);
        actionsByPositionMap.get("AttackingMidFielder").add(10);

        actionsByPositionMap.get("CentralMidFielder").add(6);
        actionsByPositionMap.get("CentralMidFielder").add(11);

        actionsByPositionMap.get("DefensiveMidFielder").add(6);
        actionsByPositionMap.get("DefensiveMidFielder").add(12);

        actionsByPositionMap.get("FullBack").add(7);
        actionsByPositionMap.get("FullBack").add(13);

        actionsByPositionMap.get("CenterBack").add(7);
        actionsByPositionMap.get("CenterBack").add(14);
    }

    public String getActionName(int actionNumber) {
        return actionByNumberMap.get(actionNumber);
    }
}
