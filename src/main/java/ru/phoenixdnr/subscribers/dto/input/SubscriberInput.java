package ru.phoenixdnr.subscribers.dto.input;

import lombok.Data;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SubscriberInput {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDate;
    private BigDecimal balance;
    private TariffPlanEntity tariffPlan;
}
