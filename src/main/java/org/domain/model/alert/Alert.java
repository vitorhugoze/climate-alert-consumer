package org.domain.model.alert;

import org.domain.model.EventId;
import org.domain.model.weather.WeatherVariableType;

public class Alert {
    private final EventId eventId;
    private final WeatherVariableType weatherVariableType;
    private final Double threasholdValue;

    public Alert(Long weatherVariable, Double threasholdValue) {
        this.eventId = EventId.generate();
        this.weatherVariableType = WeatherVariableType.getWeatherVariableType(weatherVariable);
        this.threasholdValue = threasholdValue;
    }

    public String generateAlertMessage() {
        StringBuilder query = new StringBuilder();
        query.append("⚠️ ALERTA CLIMÁTICO ⚠️ \n");
        query.append("Atenção para a alteração nas condições do tempo na sua região. \n\n");
        query.append("Condição: %s %.0f%s \n\n");
        query.append("Seja prudente e tome as precauções necessárias! ");

        return String.format(query.toString(), weatherVariableType.getName(), threasholdValue, weatherVariableType.getUnit());
    }

    public EventId getEventId() {
        return eventId;
    }

    public WeatherVariableType getWeatherVariableType() {
        return weatherVariableType;
    }

    public Double getThreasholdValue() {
        return threasholdValue;
    }
}
