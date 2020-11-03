package SpringApplication.AppointmentScheduling.exception;

public class AppointmentDoesNotExists extends Exception{
    private String message;
    public AppointmentDoesNotExists(String message)
    {
        super(message);
        this.message=message;

    }
}
