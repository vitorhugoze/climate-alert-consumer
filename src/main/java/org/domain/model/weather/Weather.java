package org.domain.model.weather;

import org.domain.model.EventId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Weather {
    private final EventId eventId;
    private final List<WeatherVariable> weatherVariablesList;

    private final LocalDateTime timestamp;

    private final String latitude;
    private final String longitude;

    private Weather(EventId eventId, List<WeatherVariable> weatherVariablesList, LocalDateTime timestamp, String latitude, String longitude) {
        this.eventId = eventId;
        this.weatherVariablesList = weatherVariablesList;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static WeatherBuilder newBuilder() {
        return new WeatherBuilder();
    }

    public EventId getEventId() {
        return eventId;
    }

    public List<WeatherVariable> getWeatherVariablesList() {
        return weatherVariablesList;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public static class WeatherBuilder {
        private EventId eventId;
        private final List<WeatherVariable> weatherVariablesList = new ArrayList<>();

        private LocalDateTime timestamp;

        private String latitude;
        private String longitude;

        private WeatherBuilder() {
            this.eventId = EventId.generate();
            this.timestamp = LocalDateTime.now();
        }

        public WeatherBuilder addClimateData(WeatherVariable weatherVariable) {
            this.weatherVariablesList.add(Objects.requireNonNull(weatherVariable,  "weatherVariable cannot be null"));

            return this;
        }

        public WeatherBuilder withEventId(EventId eventId) {
            this.eventId = Objects.requireNonNull(eventId, "eventId cannot be null");

            return this;
        }

        public WeatherBuilder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = Objects.requireNonNull(timestamp, "timestamp cannot be null");
            return this;
        }

        public WeatherBuilder withLatitude(String latitude) {
            this.latitude = Objects.requireNonNull(latitude, "latitude cannot be null");
            return this;
        }

        public WeatherBuilder withLongitude(String longitude) {
            this.longitude = Objects.requireNonNull(longitude, "longitude cannot be null");
            return this;
        }

        public Weather build() {
            if (weatherVariablesList.isEmpty()) {
                throw new IllegalStateException("climate data cannot be empty");
            }

            if (latitude == null || longitude == null) {
                throw new IllegalStateException("latitude or longitude cannot be null");
            }

            return new Weather(eventId, weatherVariablesList, timestamp, latitude, longitude);
        }
    }
}
