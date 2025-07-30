package es.cic.curso25.proyecto009.exceptionHandler;

public class SecureNoAllowNewID extends RuntimeException {

    public SecureNoAllowNewID(String message, Throwable e) {
        super(message, e);
    }

    public SecureNoAllowNewID(String message) {
        super(message);
    }

    public SecureNoAllowNewID(Throwable e) {
        super(e);
    }

    public SecureNoAllowNewID() {
    }

}
