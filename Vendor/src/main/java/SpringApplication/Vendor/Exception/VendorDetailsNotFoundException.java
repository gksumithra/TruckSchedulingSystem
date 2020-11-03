package SpringApplication.Vendor.Exception;

public class VendorDetailsNotFoundException extends Exception {
    private String message;
    public VendorDetailsNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
