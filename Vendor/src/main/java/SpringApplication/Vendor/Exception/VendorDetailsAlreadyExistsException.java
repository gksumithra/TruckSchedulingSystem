package SpringApplication.Vendor.Exception;

public class VendorDetailsAlreadyExistsException extends Exception{
    private String message;
    public VendorDetailsAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}

