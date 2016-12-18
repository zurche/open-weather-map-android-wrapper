package az.openweatherapi.utils;

/**
 * Created by az on 13/10/16.
 */

public enum OWSupportedUnits {
    METRIC("metric"),
    FAHRENHEIT("imperial");

    String unit;

    OWSupportedUnits(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
