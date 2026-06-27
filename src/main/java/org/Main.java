package org;

import org.infrastructure.database.Database;
import org.infrastructure.kafka.KafkaConsumer;

public class Main {
    public static void main(String[] args) {
        Database.initialize();
        KafkaConsumer.startConsumer();
    }
}
