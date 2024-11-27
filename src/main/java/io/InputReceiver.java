package io;

import classes.Car;
import classes.Parking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputReceiver {
    public static List<Car> readCarData(String filePath) {
        List<Car> cars = new ArrayList<>();
        Parking parkingLot = Parking.getInstance(); // Use singleton instance of Parking
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int duration = Integer.parseInt(parts[3].split(" ")[1]);
                // Pass parkingLot to each Car instance
                cars.add(new Car(carId, arrivalTime, duration, parkingLot));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cars;
    }
}
