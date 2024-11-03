package classes;

import java.util.List;

public class Gate extends Thread {
    private final List<Car> cars;
    private final int gateId;
    private final Parking parkingLot;

    public Gate(List<Car> cars, Parking parkingLot, int gateId) {
        this.cars = cars;
        this.parkingLot = parkingLot;
        this.gateId = gateId;
    }

    @Override
    public void run() {
        for (Car car : cars) {
            car.start();
        }
    }
}
