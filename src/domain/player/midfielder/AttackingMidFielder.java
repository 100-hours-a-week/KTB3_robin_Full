package domain.player.midfielder;

public class AttackingMidFielder extends MidFielder {
    public AttackingMidFielder(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.pac = 60;
        this.sho = 70;
        this.pas = 65;
        this.dri = 60;
        this.def = 60;
        this.phy = 60;
    }
    // 감아차기
    public int shootWithCurl() {
        return 1;
    }
}
