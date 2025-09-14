package domain.player.midfielder;

public class CentralMidFielder extends MidFielder {
    public CentralMidFielder(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.pac = 60;
        this.sho = 65;
        this.pas = 70;
        this.dri = 60;
        this.def = 60;
        this.phy = 60;
    }
    // 대지를 가르는 패스
    public int passLikeKi() {
        return 1;
    }
}
