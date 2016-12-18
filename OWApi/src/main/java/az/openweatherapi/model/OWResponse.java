package az.openweatherapi.model;

/**
 * Created by az on 15/10/16.
 */

public class OWResponse<T> {
    private final T body;

    public OWResponse(T body) {
        this.body = body;
    }

    public T body() {
        return body;
    }
}
