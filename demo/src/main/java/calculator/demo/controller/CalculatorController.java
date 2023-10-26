package calculator.demo.controller;//cs2new

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
//import javax.validation.ConstraintViolationException;

@RestController //In a Spring Boot or Spring MVC application, the @RestController annotation is used to define a class as a controller that handles HTTP
// Annotations that define the controller class
// and specify the base request mapping for this controller. The controller handles requests under the /calculator path.
@RequestMapping("/calculator")//REQUEST
public class CalculatorController {

    @PostMapping("/sum")
    public ResponseEntity sum(@RequestBody CalculationRequest req) {//postman se json
        //The sum method takes a CalculationRequest object as the request body.
        try {
            int num1 = req.getNum1();
            int num2 = req.getNum2();
            // Check if inputs are valid
            if (num1 < 0 || num2 < 0) {
                throw new IllegalArgumentException("Validation failed");
            }
//illarg
            // Perform the sum if validation passes
            int result = num1 + num2;

            // Return the result as a response
            String responseMessage = "Sum of " + num1 + " and " + num2 + " is: " + result;

            SimpleDateFormat istDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
            istDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
            String istTime = istDateFormat.format(new Date()) + " IST";

            // Get current system time in US time zone (EDT)
            SimpleDateFormat edtDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss ");
            edtDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
            String edtTime = edtDateFormat.format(new Date()) + " EDT";

            return new ResponseEntity(responseMessage + "\n" + istTime + "\n" + edtTime, HttpStatus.OK);


        } catch (IllegalArgumentException ex) {//ex indentifier
            // Handle ConstraintViolationException
            String errorMessage = "Validation failed. Please provide valid input values.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
            // return errorMessage;
        } catch (Exception ex) {
            // Handle other exceptions (if any)
            String errorMessage = "An error occurred while processing your request.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}

     class CalculationRequest {
        private int num1;
        private int num2;

        public int getNum1() {
            return num1;
        }

        public void setNum1(int num1) {
            this.num1 = num1;
        }

        public int getNum2() {
            return num2;
        }

        public void setNum2(int num2) {
            this.num2 = num2;
        }
    }


