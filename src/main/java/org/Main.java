package org;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.application.usecases.WeatherHandlerFacade;
import org.domain.model.weather.Weather;
import org.domain.model.weather.WeatherVariableType;
import org.infrastructure.database.Database;
import org.infrastructure.kafka.KafkaConsumer;
import org.infrastructure.messaging.WeatherDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Main {
    static void main() {
        Database.initialize();
        KafkaConsumer.startConsumer();
    }
}
