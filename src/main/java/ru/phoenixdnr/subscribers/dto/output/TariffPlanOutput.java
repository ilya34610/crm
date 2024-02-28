package ru.phoenixdnr.subscribers.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TariffPlanOutput {
    private Integer id;
    private String name;
    private BigDecimal cost;
}
