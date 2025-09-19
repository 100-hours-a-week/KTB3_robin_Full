import domain.game.GameManager;
import domain.validation.InputValidator;
import service.MappingDataService;
import service.RandomNumberService;
import service.player.PlayerActionService;
import service.player.PlayerInitService;
import view.InputView;
import view.OutputView;

public class FootBallGame {
    public static void main(String[] args) {
        GameManager gm = new GameManager(
            new InputView(),
            new OutputView(),
            new InputValidator(),
            new MappingDataService(),
            new PlayerInitService(),
            new PlayerActionService(),
            new RandomNumberService()
        );
        gm.startGame();
    }
}
