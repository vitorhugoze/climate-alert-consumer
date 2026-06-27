package org.application.usecases;

import org.domain.model.alert.Alert;
import org.domain.model.weather.WeatherVariable;
import org.infrastructure.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class WeatherEvaluator {

    public static Optional<Alert> EvaluateWeather(List<WeatherVariable> weatherVariables) {
        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement()) {

            StringBuilder query = new StringBuilder();
            query.append("SELECT ");
            query.append("\tID, ");
            query.append("\tWEATHERVARIABLE, ");
            query.append("\tTHREASHOLD_VALUE, ");
            query.append("\tTHREASHOLD_TYPE ");
            query.append("FROM ");
            query.append("\tTB_THREASHOLDS ");
            query.append("\tWHERE WEATHERVARIABLE IN (%s); ");

            List<String> variableCodes = weatherVariables.
                    stream().
                    map(w -> w.weatherVariableType.getCode().toString()).
                    toList();

            ResultSet rs = stmt.executeQuery(String.format(query.toString(), String.join(", ", variableCodes)));
            while (rs.next()) {
                Long weatherVariableCode = rs.getLong("WEATHERVARIABLE");
                Long threasholdType = rs.getLong("THREASHOLD_TYPE");
                Double threasholdValue = rs.getDouble("THREASHOLD_VALUE");

                Optional<WeatherVariable> optAlertWeather = weatherVariables.
                        stream().
                        filter(w -> w.weatherVariableType.getCode().equals(weatherVariableCode))
                        .filter(w -> {
                            if (threasholdType.equals(0L) && threasholdValue.equals(w.value)) {
                                return true;
                            } else if  (threasholdType.equals(1L) && w.value > threasholdValue) {
                                return true;
                            } else if (threasholdType.equals(-1L) && w.value < threasholdValue) {
                                return true;
                            }

                            return false;
                        }).findFirst();

                if(optAlertWeather.isPresent()) {
                    WeatherVariable alertWeather = optAlertWeather.get();
                    return Optional.of(new Alert(weatherVariableCode, alertWeather.value));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
