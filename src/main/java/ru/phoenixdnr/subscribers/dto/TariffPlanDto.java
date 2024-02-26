package ru.phoenixdnr.subscribers.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TariffPlanDto {
    Integer id;
    String name;
    BigDecimal cost;
}
