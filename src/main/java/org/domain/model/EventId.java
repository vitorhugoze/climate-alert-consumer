package org.domain.model;

import java.util.UUID;

public class EventId {
    UUID id;

    public EventId(UUID id) {
        this.id = id;
    }

    public static EventId generate() {
        return new EventId(UUID.randomUUID());
    }
}
