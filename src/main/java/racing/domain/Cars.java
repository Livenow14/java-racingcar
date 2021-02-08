package racing.domain;

import racing.utils.RandomUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Cars implements Cloneable {
    private static final String DELIMITER = ",";
    private static final int SPLIT_THRESHOLD = -1;
    private static final int START_NUMBER = 0;
    private static final int END_NUMBER = 9;
    private static final int MINIMUM_CAR_COUNTS = 2;

    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        List<Car> copy = new ArrayList<>(cars);
        validateCars(copy);
        this.cars = copy;
    }

    public static Cars generate(final String carNames) {
        String[] splitCarNames = splitCarNames(carNames);
        List<Car> cars = Arrays.stream(splitCarNames)
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    private static String[] splitCarNames(final String carNames) {
        return carNames.split(DELIMITER, SPLIT_THRESHOLD);
    }

    @Override
    protected Cars clone() {
        Cars copy = null;
        try {
            copy = (Cars) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }

    private void validateCars(final List<Car> cars) {
        if (cars.size() < MINIMUM_CAR_COUNTS) {
            throw new IllegalArgumentException("자동차 이름은 1개 이상이어야 합니다");
        }
    }

    public void race() {
        cars.forEach(car -> car.move(RandomUtils.getRandomNumber(START_NUMBER, END_NUMBER)));
    }

    public Winners findWinners() {
        int maxPosition = getMaxPosition();
        List<Car> winners = cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());
        return new Winners(winners);
    }

    private int getMaxPosition() {
        return cars.stream()
                .max(Comparator.comparingInt(Car::getPosition))
                .orElseThrow(IllegalStateException::new)
                .getPosition();
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public int getSize() {
        return cars.size();
    }
}
