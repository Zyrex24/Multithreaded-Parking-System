import io.InputReceiver;
import classes.Car;
import classes.Gate;
import classes.Parking;
import io.Statistics;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize ParkingLot with a fixed number of spots (e.g., 4)
        Parking parkingLot = Parking.getInstance();
        // Read car data from input file
        List<Car> cars = InputReceiver.readCarData("src/main/resources/input.txt");

        // Split cars into separate lists for each gate
        List<Car> gate1Cars = new ArrayList<>();
        List<Car> gate2Cars = new ArrayList<>();
        List<Car> gate3Cars = new ArrayList<>();
    }
}
