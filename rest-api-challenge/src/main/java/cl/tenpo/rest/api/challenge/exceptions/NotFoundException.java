package cl.tenpo.rest.api.challenge.exceptions;


public class NotFoundException extends ApiException {
    public NotFoundException (int code, String msg) {
        super(code, msg);
    }
}
