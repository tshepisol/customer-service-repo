package za.co.investec.cis.error;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {


    private static  final Logger log = LoggerFactory.getLogger(EntityExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("General Exception interceptor**", ex);
        return new ResponseEntity( new CustomerErrorType( ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomerErrorType errorType = new CustomerErrorType(ex.getBindingResult().toString());

        return  new ResponseEntity(errorType, HttpStatus.BAD_REQUEST);
    }




}
