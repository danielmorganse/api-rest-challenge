package cl.tenpo.rest.api.challenge.exceptions;


public class ApiException extends Exception {
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
