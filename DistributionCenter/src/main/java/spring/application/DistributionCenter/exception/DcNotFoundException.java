package spring.application.DistributionCenter.exception;

public class DcNotFoundException extends Exception {

    private String message;
    public DcNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
