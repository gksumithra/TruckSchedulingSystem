package spring.application.DistributionCenter.exception;

public class DcAlreadyExistsException  extends Exception{
    private String message;
    public DcAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
