package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    public void runGame() {
        String carNamesInput = InputView.readCarNames();
        Cars cars = Cars.of(carNamesInput);
        int tryCount = InputView.readTryCount();

        OutputView.printExecutionResult();

        for (int i=0; i< tryCount; i++) {
            cars.moveAll();
            OutputView.printRoundResult(cars);
        }

        OutputView.printWinners(cars.findWinners());
    }

}
