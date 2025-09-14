package domain.player.defender;

public class FullBack extends Defender {
    public FullBack(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.pac = 70;
        this.sho = 60;
        this.pas = 60;
        this.dri = 60;
        this.def = 65;
        this.phy = 60;
    }
    // 팀에게 패스 후 이동
    public int passAndMove() {
        return 1;
    }
}
