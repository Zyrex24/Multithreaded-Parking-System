import classes.Car;
import classes.Gate;
import classes.Parking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Parking parkingLot = Parking.getInstance();
        List<Car> gate1Cars = new ArrayList<>();
        List<Car> gate2Cars = new ArrayList<>();
        List<Car> gate3Cars = new ArrayList<>();

        String inputFilePath = "src/main/resources/input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                int gateId = Integer.parseInt(parts[0].split("\\s+")[1]);
                int carId = Integer.parseInt(parts[1].split("\\s+")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split("\\s+")[1]);
                int parkingDuration = Integer.parseInt(parts[3].split("\\s+")[1]);
                Car car = new Car(carId, arrivalTime, parkingDuration, parkingLot);
                car.setGateId(gateId);
                switch (gateId) {
                    case 1 -> gate1Cars.add(car);
                    case 2 -> gate2Cars.add(car);
                    case 3 -> gate3Cars.add(car);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return;
        }

        Gate gate1 = new Gate(gate1Cars, parkingLot, 1);
        Gate gate2 = new Gate(gate2Cars, parkingLot, 2);
        Gate gate3 = new Gate(gate3Cars, parkingLot, 3);

        gate1.start();
        gate2.start();
        gate3.start();

        try {
            gate1.join();
            gate2.join();
            gate3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        parkingLot.printSimulationDetails();
        System.out.println("Simulation Complete.");
    }
}
