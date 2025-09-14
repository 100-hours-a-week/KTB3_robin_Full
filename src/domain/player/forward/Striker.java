package domain.player.forward;

public class Striker extends Forward {

    public Striker(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.pac = 60;
        this.sho = 70;
        this.pas = 60;
        this.dri = 65;
        this.def = 60;
        this.phy = 60;
    }
    // 무회전 슛
    public int shootWithNoSpin() {
        return 1;
    }
}
