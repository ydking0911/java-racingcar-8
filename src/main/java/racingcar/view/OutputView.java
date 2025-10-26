package racingcar.view;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Cars;

public class OutputView {

    public static void printExecutionResult() {
        System.out.println("\n실행 결과");
    }

    public static void printRoundResult(Cars cars) {
        for (Car car : cars.getCars()) {
            StringBuilder sb = new StringBuilder();
            sb.append(car.getName());
            sb.append(" : ");
            sb.append("-".repeat(car.getPosition()));
            System.out.println(sb);
        }
        System.out.println();
    }

    public static void printWinners(List<String> winners) {
        System.out.print("최종 우승자 : ");
        System.out.println(String.join(", ", winners));
    }

}
