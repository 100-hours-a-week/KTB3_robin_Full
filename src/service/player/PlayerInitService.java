package service.player;

import domain.player.Player;
import domain.player.Position;
import domain.player.Team;
import domain.player.defender.CenterBack;
import domain.player.defender.FullBack;
import domain.player.forward.Striker;
import domain.player.forward.Winger;
import domain.player.midfielder.AttackingMidFielder;
import domain.player.midfielder.CentralMidFielder;
import domain.player.midfielder.DefensiveMidFielder;

public class PlayerInitService {
    public Player playerInit(String name, Team team, Position position) {
        String teamName;

        switch (team) {
            case ARSENAL:
                teamName = "아스날";
                break;
            case LIVERPOOL:
                teamName = "리버풀";
                break;
            case MANCITY:
                teamName = "맨시티";
                break;
            case CHELSEA:
                teamName = "첼시";
                break;
            case TOTTENHAM:
                teamName = "토트넘";
                break;
            default: // MANUNITED
                teamName = "맨유";
        }

        switch (position) {
            case STIKER:
                return new Striker(name, teamName);
            case WINGER:
                return new Winger(name, teamName);
            case ATTACKING_MIDFIELDER:
                return new AttackingMidFielder(name, teamName);
            case CENTRAL_MIDFIELDER:
                return new CentralMidFielder(name, teamName);
            case DEFENSIVE_MIDFIELDER:
                return new DefensiveMidFielder(name, teamName);
            case FULLBACK:
                return new FullBack(name, teamName);
            default: // CENTERBACK
                return new CenterBack(name, teamName);
        }
    }
}
