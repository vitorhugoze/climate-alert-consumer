package org.domain.model.weather;

public class WeatherVariable {
    public WeatherVariableType weatherVariableType;
    public Double value;

    public WeatherVariable(WeatherVariableType weatherVariableType, Double value) {
        this.weatherVariableType = weatherVariableType;
        this.value = value;
    }
}
