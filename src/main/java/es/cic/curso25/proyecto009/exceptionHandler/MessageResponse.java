package es.cic.curso25.proyecto009.exceptionHandler;

public class MessageResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageResponse [message=" + message + "]";
    }

    public MessageResponse(String message) {
        this.message = message;
    }

}
