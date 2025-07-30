package es.cic.curso25.proyecto009.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    ObjectMapper objectMapper;

    @ExceptionHandler(SecureNoAllowNewID.class)
    public ResponseEntity<String> handleSecureNoAllowNewID(SecureNoAllowNewID e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(generateBodyNegativeResponses(e.getMessage()));

    }

    private String generateBodyNegativeResponses(String mesage) {
        try {
            MessageResponse mesaggeException = new MessageResponse(mesage);
            String JsonBody = objectMapper.writeValueAsString(mesaggeException);
            return JsonBody;
        } catch (Exception e) {
            return "";
        }
    }
}
