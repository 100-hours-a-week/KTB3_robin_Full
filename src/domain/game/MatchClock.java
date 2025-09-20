package domain.game;

public class MatchClock implements Runnable {
    // 쓰기는 오직 GameManager 에서 생성한 matchClockThread 만 단독으로 실행하기 때문에,
    // volatile 로 메모리 가시성만 확보하면 충분하다고 판단되어 volatile 을 사용하였습니다.
    private volatile int clockTime;
    private volatile boolean isRunning;

    @Override
    public void run() {
        while(isRunning) {
            clockTime += 3;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
            if(clockTime == 90) { // 현실시간 30 초가 초과하기 전까지만 입력을 받고 게임을 종료한다.
                isRunning = false;
            }
        }
    }

    public MatchClock() {
        clockTime = 0;
        isRunning = true;
    }

    public int getClockTime() {
        return clockTime;
    }
    public boolean getIsRunning() {
        return isRunning;
    }
}
