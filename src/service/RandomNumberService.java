package service;

public class RandomNumberService {
    // 0~1 난수
    public int oneToFour() {
        return (int) (Math.floor(4 * Math.random())) + 1;
    }
}
