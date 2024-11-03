package classes;

import io.Logger;
import io.Statistics;

public class Car extends Thread {
    private final int carId;
    private final int arrivalTime;
    private final int parkingDuration;
    private final Parking parkingLot;
    private int gateId;

    public Car(int carId, int arrivalTime, int parkingDuration, Parking parkingLot) {
        this.carId = carId;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.parkingLot = parkingLot;
    }

    public int getCarId() {
        return carId;
    }

    public int getGateId() {
        return gateId;
    }

    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public void run() {

    }
}
