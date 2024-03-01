package ru.phoenixdnr.subscribers.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SubscriberOutput {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDate;
    private BigDecimal balance;
    private TariffPlanEntity tariffPlan;
}
