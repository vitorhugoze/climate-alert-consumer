package org.domain.model.weather;

public enum WeatherVariableType {
    TEMPERATURE(1L, "Temperatura", "°C"),
    HUMIDITY(2L, "Humidade", "%"),
    PRESSURE(3L, "Pressão Atmosférica", "mb"),
    WIND_SPEED(4L, "Vento", "km/h"),
    PRECIPITATION(5L,"Chuva", "mm");

    private final Long code;
    private final String name;
    private final String unit;

    WeatherVariableType(Long code, String name, String unit) {
        this.name = name;
        this.code = code;
        this.unit = unit;
    }

    public static WeatherVariableType getWeatherVariableType(Long code) {
        for (WeatherVariableType weatherVariableType : WeatherVariableType.values()) {
            if  (weatherVariableType.code.equals(code)) {
                return weatherVariableType;
            }
        }

        throw new IllegalArgumentException("No WeatherVariableType with code " + code);
    }

    public String getName() {
        return name;
    }

    public Long getCode() {
        return code;
    }

    public String getUnit() {
        return unit;
    }
}
