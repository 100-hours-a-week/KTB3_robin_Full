package domain.message;

public class GameMessages {
    // init
    public static final String welcome = "축구 시뮬레이터 게임에 오신것을 환영합니다.\n당신은 원하는 팀과 포지션을 골라 선수로 뛰게됩니다.";
    public static final String whatIsYourName = "당신의 이름은 무엇인가요 ? (이름은 1~15 글자 내에서 입력해주세요.) : ";
    public static final String chooseYourTeam = "다음의 팀중에 당신이 입단하고 싶은 팀을 고르세요. (숫자로만 입력)\n1. 아스날\n2. 리버풀\n3. 맨시티\n4. 첼시\n5. 토트넘\n6. 맨유";
    public static final String chooseYourPosition = "다음 중 어느 포지션에서 뛰시겠어요? (숫자로만 입력)\n1. 스트라이커\n2. 윙어\n3. 공격형 미드필더\n4. 중앙 미드필더\n5. 수비형 미드필더\n6. 풀백\n7. 센터백";


    //match
    public static final String matchStart = "에서 멋진 커리어를 보내며 어느새 챔피언스리그 결승전에 올랐습니다.\n이제 게임을 시작합니다.\n부디 몇번 없는 기회를 놓치지 말고 팀을 정상으로 올려놓기를 바랍니다.\n";
    public static final String howToPlay = "\n당신은 몇가지 상황을 마주하게 됩니다.\n출력되는 텍스트를 잘읽고 최선의 판단을 입력하면 됩니다.\nGood Luck!\n\n";

    public static final String oneVoneSituation = "상대 골키퍼와 일대일 찬스입니다 !!!\n";
    public static final String nearByBoxSituation = "상대 박스 근처에서 공을 잡았습니다 !!\n";
    public static final String counterattackSituation = "공을 잡은 채로 역습 상황을 맞이했습니다!\n";
    public static final String defendSituation = "앞에 상대 선수가 공을 몰고옵니다.\n";

    // 행동 입력 안내 및 성공 메시지
    public static final String askActionNumber = "위 번호 중 하나를 선택하여 입력하세요.\n";
    public static final String actionSuccess = "을(를) 성공했습니다!\n";
    public static final String actionFail = "을(를) 실패했습니다.\n";

    // 결과 출력 메세지
    public static final String matchEnd = "의 뛰어난 활약으로 우승에 성공했다!\n플레이 해주셔서 감사합니다.";
}
