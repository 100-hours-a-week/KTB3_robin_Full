import domain.game.GameManager;
import domain.game.MatchClock;
import domain.validation.InputValidator;
import service.MappingDataService;
import service.RandomNumberService;
import service.player.PlayerActionService;
import service.player.PlayerInitService;
import view.InputView;
import view.OutputView;

public class FootBallGame {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        InputValidator inputValidator = new InputValidator();
        MappingDataService mappingDataService = new MappingDataService();
        PlayerInitService playerInitService = new PlayerInitService();
        PlayerActionService playerActionService = new PlayerActionService(mappingDataService);
        RandomNumberService randomNumberService = new RandomNumberService();
        MatchClock matchClock = new MatchClock();

        GameManager gm = new GameManager(inputView, outputView, inputValidator, mappingDataService, playerInitService, playerActionService, randomNumberService, matchClock);
        gm.startGame();
    }
}
