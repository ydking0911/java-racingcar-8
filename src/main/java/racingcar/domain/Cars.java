package racingcar.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> carList;

    private Cars(List<Car> carList) {
        this.carList = carList;
    }

    public static Cars of(String names) {
        List<Car> cars = Arrays.stream(names.split(","))
                .map(name -> name.trim())
                .map(name -> new Car(name))
                .collect(Collectors.toList());
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("경주할 자동차 이름을 입력하세요.");
        }
        return new Cars(cars);
    }

    public void moveAll() {
        carList.forEach(name -> name.move());
    }

    public List<Car> getCars() {
        return carList;
    }

    public List<String> findWinners() {
        int maxPosition = carList.stream()
                .mapToInt(name -> name.getPosition())
                .max()
                .orElse(0);

        List<String> winners = carList.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(name -> name.getName())
                .collect(Collectors.toList());

        return winners;
    }

}
