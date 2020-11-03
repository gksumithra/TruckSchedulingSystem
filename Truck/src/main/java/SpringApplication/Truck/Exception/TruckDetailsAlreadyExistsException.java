package SpringApplication.Truck.Exception;

public class TruckDetailsAlreadyExistsException extends Exception{
    private String message;
    public TruckDetailsAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}

