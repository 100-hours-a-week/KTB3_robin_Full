package service.player;

import domain.player.Player;
import domain.player.defender.CenterBack;
import domain.player.defender.FullBack;
import domain.player.forward.Striker;
import domain.player.forward.Winger;
import domain.player.midfielder.AttackingMidFielder;
import domain.player.midfielder.CentralMidFielder;
import domain.player.midfielder.DefensiveMidFielder;

public class PlayerInitService {
    public Player playerInit(String name, int teamNumber, int positionNumber) {
        String teamName;

        switch (teamNumber) {
            case 1:
                teamName = "아스날";
                break;
            case 2:
                teamName = "리버풀";
                break;
            case 3:
                teamName = "맨시티";
                break;
            case 4:
                teamName = "첼시";
                break;
            case 5:
                teamName = "토트넘";
                break;
            default:
                teamName = "맨유";
        }

        switch (positionNumber) {
            case 1:
                return new Striker(name, teamName);
            case 2:
                return new Winger(name, teamName);
            case 3:
                return new AttackingMidFielder(name, teamName);
            case 4:
                return new CentralMidFielder(name, teamName);
            case 5:
                return new DefensiveMidFielder(name, teamName);
            case 6:
                return new FullBack(name, teamName);
            default:
                return new CenterBack(name, teamName);
        }
    }
}
