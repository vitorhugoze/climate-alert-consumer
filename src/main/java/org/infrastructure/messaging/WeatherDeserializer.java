package org.infrastructure.messaging;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;
import org.domain.model.weather.Weather;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class WeatherDeserializer implements Deserializer<Weather> {
    private final Gson gson = new Gson();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Weather deserialize(String topic, byte[] data) {
        Objects.requireNonNull(data);

        String json = new String(data, StandardCharsets.UTF_8);
        return gson.fromJson(json, Weather.class);
    }
}
