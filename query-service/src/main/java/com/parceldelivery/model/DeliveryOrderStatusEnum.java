package com.parceldelivery.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DeliveryOrderStatusEnum {
    @JsonProperty("ORDER")
    ORDER("ORDER"),
    @JsonProperty("CANCELLED")
    CANCELLED("CANCELLED"),
    @JsonProperty("ASSIGNED")
    ASSIGNED("ASSIGNED"),
    @JsonProperty("ON_THE_WAY")
    ON_THE_WAY("ON_THE_WAY"),
    @JsonProperty("DELIVERED")
    DELIVERED("DELIVERED");

    private static Map<String, DeliveryOrderStatusEnum> FORMAT_MAP = Stream
            .of(DeliveryOrderStatusEnum.values())
            .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

    private final String formatted;

    DeliveryOrderStatusEnum(String formatted) {
        this.formatted = formatted;
    }

    @JsonCreator // This is the factory method and must be static
    public static DeliveryOrderStatusEnum fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }
}