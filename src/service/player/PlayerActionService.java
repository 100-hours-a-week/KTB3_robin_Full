package service.player;

import domain.player.Player;
import domain.player.Position;
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
import service.MappingDataService;

import java.util.ArrayList;
import java.util.HashSet;

public class PlayerActionService {

    private final MappingDataService mappingDataService;

    public PlayerActionService(MappingDataService mappingDataService) {
        this.mappingDataService = mappingDataService;
    }

    // 포지션과 상황을 모두 고려했을 때 취할 수 있는 행동 번호 집합 생성
    public ArrayList<Integer> getAvailableActionNumbers(Position position, int situationNumber) {
        // 상황만 따졌을 때 취할 수 있는 행동 번호 집합
        HashSet<Integer> setBySituation = mappingDataService.getAvailableActionNumbersBySituation(situationNumber);
        // 포지션만 따졌을 때 취할 수 있는 행동 번호 집합
        HashSet<Integer> setByPosition = mappingDataService.getAvailableActionNumbersByPosition(position);
        setBySituation.retainAll(setByPosition);  // setBySituation 을 두 집합의 교집합으로 만들기
        ArrayList<Integer> actionNumberSet = new ArrayList<>(setBySituation);
        return new ArrayList<>(actionNumberSet);
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
            case 6: // 정확한 패스 (MidFielder)
                if (player instanceof MidFielder) {
                    return ((MidFielder) player).shortPassWithAccuracy();
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
        return -1; // 할 수 없는 행동 입력 시 -1 리턴
    }
}