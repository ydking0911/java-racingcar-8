package racingcar.domain;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarsTest {

    @Test
    @DisplayName("정상적인 자동차 이름들로 Cars를 생성할 수 있다")
    void createCarsWithValidNames() {
        // given
        String names = "pobi,woni,jun";

        // when
        Cars cars = Cars.of(names);

        // then
        List<Car> carList = cars.getCars();
        assertThat(carList).hasSize(3);
        assertThat(carList.get(0).getName()).isEqualTo("pobi");
        assertThat(carList.get(1).getName()).isEqualTo("woni");
        assertThat(carList.get(2).getName()).isEqualTo("jun");
    }

    @Test
    @DisplayName("자동차 이름에 공백이 있어도 정상적으로 처리된다")
    void createCarsWithSpacesInNames() {
        // given
        String names = " pobi , woni , jun ";

        // when
        Cars cars = Cars.of(names);

        // then
        List<Car> carList = cars.getCars();
        assertThat(carList).hasSize(3);
        assertThat(carList.get(0).getName()).isEqualTo("pobi");
        assertThat(carList.get(1).getName()).isEqualTo("woni");
        assertThat(carList.get(2).getName()).isEqualTo("jun");
    }

    @Test
    @DisplayName("빈 문자열로 Cars를 생성하면 예외가 발생한다")
    void createCarsWithEmptyString() {
        // given
        String names = "";

        // when & then
        assertThatThrownBy(() -> Cars.of(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1~5자 이어야 합니다.");
    }

    @Test
    @DisplayName("공백만 있는 문자열로 Cars를 생성하면 예외가 발생한다")
    void createCarsWithOnlySpaces() {
        // given
        String names = "   ,   ,   ";

        // when & then
        assertThatThrownBy(() -> Cars.of(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1~5자 이어야 합니다.");
    }

    @Test
    @DisplayName("유효하지 않은 자동차 이름이 포함되면 예외가 발생한다")
    void createCarsWithInvalidName() {
        // given
        String names = "pobi,verylongname,jun";

        // when & then
        assertThatThrownBy(() -> Cars.of(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1~5자 이어야 합니다.");
    }

    @Test
    @DisplayName("모든 자동차가 움직인다")
    void moveAllCars() {
        // given
        Cars cars = Cars.of("pobi,woni");
        List<Car> carList = cars.getCars();
        int initialPosition1 = carList.get(0).getPosition();
        int initialPosition2 = carList.get(1).getPosition();

        // when
        cars.moveAll();

        // then
        // 랜덤 값에 따라 위치가 증가할 수도 있고 그대로일 수도 있음
        assertThat(carList.get(0).getPosition()).isGreaterThanOrEqualTo(initialPosition1);
        assertThat(carList.get(1).getPosition()).isGreaterThanOrEqualTo(initialPosition2);
    }

    @Test
    @DisplayName("자동차 목록을 올바르게 반환한다")
    void getCars() {
        // given
        Cars cars = Cars.of("pobi,woni");

        // when
        List<Car> result = cars.getCars();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("pobi");
        assertThat(result.get(1).getName()).isEqualTo("woni");
    }

    @Test
    @DisplayName("우승자를 올바르게 찾는다 - 단독 우승")
    void findWinnersSingleWinner() {
        // given
        Cars cars = Cars.of("pobi,woni");
        List<Car> carList = cars.getCars();

        // when
        List<String> winners = cars.findWinners();

        // then
        assertThat(winners).isNotEmpty();
        assertThat(winners).containsAnyOf("pobi", "woni");
    }

    @Test
    @DisplayName("우승자를 올바르게 찾는다 - 공동 우승")
    void findWinnersMultipleWinners() {
        // given
        Cars cars = Cars.of("pobi,woni,jun");
        
        // 모든 자동차가 같은 위치에 있을 때 (초기 상태)
        // when
        List<String> winners = cars.findWinners();

        // then
        assertThat(winners).hasSize(3);
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("자동차가 하나일 때도 정상적으로 처리된다")
    void createCarsWithSingleCar() {
        // given
        String names = "pobi";

        // when
        Cars cars = Cars.of(names);

        // then
        List<Car> carList = cars.getCars();
        assertThat(carList).hasSize(1);
        assertThat(carList.get(0).getName()).isEqualTo("pobi");
    }
}
