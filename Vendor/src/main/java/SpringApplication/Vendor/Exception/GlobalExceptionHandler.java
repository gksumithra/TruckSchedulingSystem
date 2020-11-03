package SpringApplication.Vendor.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = VendorDetailsAlreadyExistsException.class)
    public ResponseEntity<?> VendorDetailsAlreadyExistsException(VendorDetailsAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(value = VendorDetailsNotFoundException.class)
    public ResponseEntity<?> VendorDetailsNotFoundException(VendorDetailsNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> globalException(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getLocalizedMessage());
    }
}
