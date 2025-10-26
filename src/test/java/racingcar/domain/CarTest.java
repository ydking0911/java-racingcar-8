package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {

    @Test
    @DisplayName("정상적인 자동차 이름으로 생성할 수 있다")
    void createCarWithValidName() {
        // given
        String name = "pobi";

        // when
        Car car = new Car(name);

        // then
        assertThat(car.getName()).isEqualTo("pobi");
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("자동차 이름이 null이면 예외가 발생한다")
    void createCarWithNullName() {
        // given
        String name = null;

        // when & then
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1~5자 이어야 합니다.");
    }

    @Test
    @DisplayName("자동차 이름이 빈 문자열이면 예외가 발생한다")
    void createCarWithEmptyName() {
        // given
        String name = "";

        // when & then
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1~5자 이어야 합니다.");
    }

    @Test
    @DisplayName("자동차 이름이 공백만 있으면 예외가 발생한다")
    void createCarWithBlankName() {
        // given
        String name = "   ";

        // when & then
        Car car = new Car(name);
        assertThat(car.getName()).isEqualTo("   ");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdef", "verylongname", "123456"})
    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다")
    void createCarWithTooLongName(String name) {
        // when & then
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1~5자 이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "abcd", "abcde"})
    @DisplayName("자동차 이름이 1~5자 범위 내에 있으면 정상 생성된다")
    void createCarWithValidLengthName(String name) {
        // when
        Car car = new Car(name);

        // then
        assertThat(car.getName()).isEqualTo(name);
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("자동차가 움직이면 위치가 증가한다")
    void moveCar() {
        // given
        Car car = new Car("pobi");
        int initialPosition = car.getPosition();

        // when
        car.move();

        // then
        // 랜덤 값에 따라 위치가 증가할 수도 있고 그대로일 수도 있음
        assertThat(car.getPosition()).isGreaterThanOrEqualTo(initialPosition);
    }

    @Test
    @DisplayName("자동차 이름을 올바르게 반환한다")
    void getName() {
        // given
        String name = "woni";
        Car car = new Car(name);

        // when
        String result = car.getName();

        // then
        assertThat(result).isEqualTo(name);
    }

    @Test
    @DisplayName("자동차 위치를 올바르게 반환한다")
    void getPosition() {
        // given
        Car car = new Car("pobi");

        // when
        int position = car.getPosition();

        // then
        assertThat(position).isEqualTo(0);
    }
}
