package SpringApplication.DcSlots.exception;

public class DcSlotAlreadyExistsException extends Exception{
  private  String message;
 public DcSlotAlreadyExistsException(String message)
  {
      super(message);
      this.message=message;

  }

}
