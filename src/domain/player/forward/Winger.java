package domain.player.forward;

public class Winger extends Forward {
    public Winger(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.pac = 60;
        this.sho = 65;
        this.pas = 60;
        this.dri = 70;
        this.def = 60;
        this.phy = 60;
    }
    // 개인기 돌파
    public int dribbleWithSkills() {
        return 1;
    }
}
