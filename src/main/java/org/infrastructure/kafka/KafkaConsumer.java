package org.infrastructure.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.application.usecases.WeatherHandlerFacade;
import org.domain.model.weather.Weather;
import org.infrastructure.messaging.WeatherDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumer {

    private static final String BROKER_ADDRESS = System.getenv("BROKER_ADDRESS") != null ? System.getenv("BROKER_ADDRESS") : "localhost:9092";

    public static void startConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_ADDRESS);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, WeatherDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "climate-group-1");

        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        try(org.apache.kafka.clients.consumer.KafkaConsumer<String, Weather> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList("climate-topic"));

            while (true) {
                ConsumerRecords<String, Weather> records = consumer.poll(Duration.ofSeconds(1));

                for (ConsumerRecord<String, Weather> record : records) {
                    System.out.println("Received, offset: " + record.offset());
                    WeatherHandlerFacade.handleWeatherVariables(record.value().getWeatherVariablesList());
                }
            }
        }
    }

}
