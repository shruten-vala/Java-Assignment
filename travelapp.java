import java.util.Scanner;

// custom exception
class ride_not_found_exception extends Exception {
    public ride_not_found_exception(String msg) {
        super(msg);
    }
}

// abstract base class
abstract class trip {
    private String driver;
    private String vehicle;
    protected double kms;

    public trip(String driver, String vehicle, double kms) {
        this.driver = driver;
        this.vehicle = vehicle;
        this.kms = kms;
    }

    public String get_driver() {
        return driver;
    }

    public String get_vehicle() {
        return vehicle;
    }

    public double get_kms() {
        return kms;
    }

    // fare to be defined by child
    public abstract double fare();
}

// bike trip
class twowheelertrip extends trip {
    private static final double rate = 10;

    public twowheelertrip(String driver, String vehicle, double kms) {
        super(driver, vehicle, kms);
    }

    @Override
    public double fare() {
        return kms * rate;
    }
}

// car trip
class fourwheelertrip extends trip {
    private static final double rate = 20;

    public fourwheelertrip(String driver, String vehicle, double kms) {
        super(driver, vehicle, kms);
    }

    @Override
    public double fare() {
        return kms * rate;
    }
}

// main app
public class travelapp {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("choose your ride (bike/car): ");
            String type = sc.nextLine().trim().toLowerCase();

            System.out.print("enter distance in km: ");
            double kms = sc.nextDouble();

            if (kms <= 0) {
                throw new IllegalArgumentException("distance must be positive!");
            }

            trip t;

            if (type.equals("bike")) {
                t = new twowheelertrip("rahul mehta", "mh09xy1234", kms);
            } else if (type.equals("car")) {
                t = new fourwheelertrip("sonia verma", "dl3cab5678", kms);
            } else {
                throw new ride_not_found_exception("ride type not available!");
            }

            System.out.println("\n--- trip details ---");
            System.out.println("driver: " + t.get_driver());
            System.out.println("vehicle: " + t.get_vehicle());
            System.out.println("distance: " + t.get_kms() + " km");
            System.out.printf("fare: ₹%.2f%n", t.fare());

        } catch (ride_not_found_exception e) {
            System.out.println("error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
