package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {

    private static final int MOVE_THRESHOLD = 4;
    private final String name;
    private int position;

    public Car(String name) {
        if (name == null || name.isEmpty() || name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 1~5자 이어야 합니다.");
        }
        this.name = name;
        this.position=0;
    }

    public void move() {
        int randomValue = Randoms.pickNumberInRange(0, 9);
        if (randomValue >= MOVE_THRESHOLD) position++;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

}
