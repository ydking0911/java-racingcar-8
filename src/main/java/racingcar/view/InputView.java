package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요. (이름은 쉼표(,) 기준으로 구분됨");
        String input = Console.readLine();
        return input;
    }

    public static int readTryCount() {
        System.out.println("시도할 횟수");
        String input = Console.readLine();
        int count = Integer.parseInt(input);
        if (count <= 0) throw new IllegalArgumentException("시도 횟수는 1 이상의 숫자여야 함");
        return count;
    }

}
