package ru.phoenixdnr.subscribers.dto.input;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceInput {
    private Integer id;
    private String name;
    private BigDecimal cost;
}
