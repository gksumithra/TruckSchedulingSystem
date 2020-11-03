package SpringApplication.DcSlots.exception;

public class DcSlotNotFoundException extends Exception {

    private String message;
    public DcSlotNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
