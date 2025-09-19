package domain.player;

public class Player {
    protected String name; // 선수 이름
    protected String teamName; // 팀 이름
    protected Position position;

    protected int pac = 60; // 속도
    protected int sho = 60; // 슈팅
    protected int pas = 60; // 패스
    protected int dri = 60; // 드리블
    protected int def = 60; // 수비력
    protected int phy = 60; // 피지컬

    public String getName() {
        return name;
    }
    public String getTeamName() {
        return teamName;
    }
    public Position getPosition() {
        return position;
    }
    public int getPac() {
        return pac;
    }
    public int getSho() {
        return sho;
    }
    public int getPas() {
        return pas;
    }
    public int getDri() {
        return dri;
    }
    public int getDef() {
        return def;
    }
    public int getPhy() {
        return phy;
    }

    // 패스
    public int pass() {
        return 1;
    }
    // 슈팅
    public int shoot() {
        return 1;
    }
    // 태클
    public int tackle() {
        return 1;
    }
    // 드리블 돌파
    public int dribble() {
        return 1;
    }
}
