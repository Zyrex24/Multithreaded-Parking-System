
import io.InputReceiver;
import classes.Car;
import classes.Gate;
import classes.Parking;
import io.Statistics;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Initialize parking lot
        Parking parkingLot = Parking.getInstance();

        // Create lists for each gate's cars
        List<Car> gate1Cars = new ArrayList<>();
        List<Car> gate2Cars = new ArrayList<>();
        List<Car> gate3Cars = new ArrayList<>();

        // Read data from file
        String inputFilePath = "src/main/resources/input.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse each line (e.g., "Gate 1, Car 0, Arrive 0, Parks 3")
                String[] parts = line.split(",\\s*");
                int gateId = Integer.parseInt(parts[0].split("\\s+")[1]); // Extract gate ID
                int carId = Integer.parseInt(parts[1].split("\\s+")[1]); // Extract car ID
                int arrivalTime = Integer.parseInt(parts[2].split("\\s+")[1]); // Extract arrival time
                int parkingDuration = Integer.parseInt(parts[3].split("\\s+")[1]); // Extract parking duration

                // Create car object and assign it to the corresponding gate list
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

        // Create and start gates
        Gate gate1 = new Gate(gate1Cars, parkingLot, 1);
        Gate gate2 = new Gate(gate2Cars, parkingLot, 2);
        Gate gate3 = new Gate(gate3Cars, parkingLot, 3);

        gate1.start();
        gate2.start();
        gate3.start();

        // Wait for all gates to finish processing
        try {
            gate1.join();
            gate2.join();
            gate3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--- Parking simulation completed ---");
        Statistics.generateReport();
    }
}