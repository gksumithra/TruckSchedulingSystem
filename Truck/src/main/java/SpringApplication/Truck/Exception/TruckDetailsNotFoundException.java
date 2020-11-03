package SpringApplication.Truck.Exception;

public class TruckDetailsNotFoundException extends Exception {
    private String message;
    public TruckDetailsNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
