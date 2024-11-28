package classes;

import java.util.List;

public class Gate extends Thread {
    private final List<Car> cars;
    private final Parking parkingLot;
    private final int gateId;

    public Gate(List<Car> cars, Parking parkingLot, int gateId) {
        this.cars = cars;
        this.parkingLot = parkingLot;
        this.gateId = gateId;
    }

    @Override
    public void run() {
        for (Car car : cars) {
            car.setGateId(gateId);
            car.start(); // Start the car thread
            try {
                car.join(); // Wait for the car to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
