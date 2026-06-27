package org.application.usecases;

import org.application.services.MessagingService;
import org.domain.model.alert.Alert;
import org.domain.model.weather.WeatherVariable;

import java.util.List;
import java.util.Optional;

public class WeatherHandlerFacade {

    public static void handleWeatherVariables(List<WeatherVariable> weatherVariables) {
        //TODO Implement delay between alerts
        //TODO Send composite alerts in case of multiple alerts at same time

        Optional<Alert> optAlert = WeatherEvaluator.EvaluateWeather(weatherVariables);

        List<String> destinations = MessagingService.getAlertDestinations();
        if  (optAlert.isPresent()) {
            System.out.println("Sending alert");

            Alert alert = optAlert.get();
            destinations.forEach(d -> MessagingService.sendWhatsAppMessage(d, alert.generateAlertMessage()));
        }
    }

}
