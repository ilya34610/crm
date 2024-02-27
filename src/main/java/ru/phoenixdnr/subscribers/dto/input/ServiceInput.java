package ru.phoenixdnr.subscribers.dto.input;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceInput {
    Integer id;
    String name;
    BigDecimal cost;
}
