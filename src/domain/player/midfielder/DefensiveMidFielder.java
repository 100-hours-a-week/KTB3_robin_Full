package domain.player.midfielder;

public class DefensiveMidFielder extends MidFielder {
    public DefensiveMidFielder(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.pac = 60;
        this.sho = 65;
        this.pas = 65;
        this.dri = 60;
        this.def = 65;
        this.phy = 60;
    }
    // 볼 리커버리
    public int recoverBall() {
        return 1;
    }
}
