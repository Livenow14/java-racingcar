package racing.view.dto;

import racing.domain.Car;

public class CarDto {
    private String carName;
    private int position;

    private CarDto(final String carName, final int position) {
        this.carName = carName;
        this.position = position;
    }

    public static CarDto of(final Car car) {
        return new CarDto(car.getName(), car.getPositionValue());
    }

    public String getCarName() {
        return carName;
    }

    public int getPosition() {
        return position;
    }
}
