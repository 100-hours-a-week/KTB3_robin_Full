package domain.player.defender;

import domain.player.Position;

public class CenterBack extends Defender {
    public CenterBack(String name, String teamName, Position position) {
        this.name = name;
        this.teamName = teamName;
        this.position = position;
        this.pac = 60;
        this.sho = 60;
        this.pas = 60;
        this.dri = 60;
        this.def = 70;
        this.phy = 65;
    }
    // 강력한 몸싸움
    public int duel() {
        return 1;
    }
}
