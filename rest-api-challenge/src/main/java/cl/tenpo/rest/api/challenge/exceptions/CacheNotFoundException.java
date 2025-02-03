package cl.tenpo.rest.api.challenge.exceptions;

public class CacheNotFoundException extends RuntimeException {
    public CacheNotFoundException(String msg) {
        super(msg);
    }

    public CacheNotFoundException(){
        super();
    }
}
