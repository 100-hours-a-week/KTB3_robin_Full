package service.player;

import domain.player.Player;
import domain.player.midfielder.AttackingMidFielder;
import domain.player.midfielder.CentralMidFielder;
import domain.player.midfielder.DefensiveMidFielder;
import domain.player.midfielder.MidFielder;
import domain.player.defender.CenterBack;
import domain.player.defender.Defender;
import domain.player.defender.FullBack;
import domain.player.forward.Forward;
import domain.player.forward.Striker;
import domain.player.forward.Winger;

import java.util.ArrayList;

public class PlayerActionService {

    // 플레이어의 포지션에서 할 수 있는 행동 번호 list를 출력
    public ArrayList<Integer> getAvailableActionNumbers(Player player, int situationNumber) {
        ArrayList<Integer> actions = new ArrayList<>();

        boolean isForward = player instanceof Forward;
        boolean isWinger = player instanceof Winger;
        boolean isStriker = player instanceof Striker;

        boolean isMid = player instanceof MidFielder;
        boolean isAM = player instanceof AttackingMidFielder;
        boolean isCM = player instanceof CentralMidFielder;
        boolean isDM = player instanceof DefensiveMidFielder;

        boolean isDefender = player instanceof Defender;
        boolean isCB = player instanceof CenterBack;
        boolean isFB = player instanceof FullBack;

        switch (situationNumber) {
            case 1: // 골키퍼 1:1
                actions.add(2); // 슈팅
                if (isForward) actions.add(5); // 침착한 슛
                break;
            case 2: // 박스 근처
                actions.add(1); // 패스
                actions.add(2); // 슈팅
                if (isForward) actions.add(5); // 침착한 슛
                if (isWinger) actions.add(8); // 개인기 돌파
                if (isStriker) actions.add(9); // 무회전 슛
                if (isMid) actions.add(6); // 정교한 패스
                if (isAM) actions.add(10); // 감아차기
                break;
            case 3: // 역습
                actions.add(1); // 패스
                if (isCM) actions.add(11); // 대지를 가르는 패스
                if (isFB) actions.add(13); // 패스 앤 무브
                break;
            case 4: // 수비 상황
            default:
                actions.add(3); // 태클
                if (isDM) actions.add(12);     // 볼 리커버리
                if (isDefender) actions.add(7); // 침착한 태클
                if (isCB) actions.add(14);     // 강력한 몸싸움
                break;
        }
        return actions;
    }

    // 원래 Player 계열 클래스들에 각 능력치를 토대로 확률을 계산하는 로직이 있어야하나,
    // 시간 관계상 전부 성공처리로 모든 행동 메소드가 1을 리턴하도록 했습니다
    public int execute(Player player, int actionNumber) {
        switch (actionNumber) {
            case 1: // 패스
                return player.pass();
            case 2: // 슈팅
                return player.shoot();
            case 3: // 태클
                return player.tackle();
            case 4: // 드리블 돌파
                return player.dribble();

            case 5: // 침착한 슛 (Forward)
                if (player instanceof Forward) {
                    return ((Forward) player).shootWithCalm();
                }
                break;
            case 7: // 침착한 태클 (Defender)
                if (player instanceof Defender) {
                    return ((Defender) player).tackleWithAccuracy();
                }
                break;
            case 8: // 개인기 돌파 (Winger)
                if (player instanceof Winger) {
                    return ((Winger) player).dribbleWithSkills();
                }
                break;
            case 9: // 무회전 슛 (Striker)
                if (player instanceof Striker) {
                    return ((Striker) player).shootWithNoSpin();
                }
                break;
            case 10: // 감아차기 (AM)
                if (player instanceof AttackingMidFielder) {
                    return ((AttackingMidFielder) player).shootWithCurl();
                }
                break;
            case 11: // 대지를 가르는 패스 (CM)
                if (player instanceof CentralMidFielder) {
                    return ((CentralMidFielder) player).passLikeKi();
                }
                break;
            case 12: // 볼 리커버리 (DM)
                if (player instanceof DefensiveMidFielder) {
                    return ((DefensiveMidFielder) player).recoverBall();
                }
                break;
            case 13: // 패스 앤 무브 (FullBack)
                if (player instanceof FullBack) {
                    return ((FullBack) player).passAndMove();
                }
                break;
            case 14: // 강력한 몸싸움 (CB)
                if (player instanceof CenterBack) {
                    return ((CenterBack) player).duel();
                }
                break;
            default:
                break;
        }
        return -1;
    }
}