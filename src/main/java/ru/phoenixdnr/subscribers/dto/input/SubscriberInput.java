package ru.phoenixdnr.subscribers.dto.input;

import lombok.Data;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SubscriberInput {
    Long id;
    String firstName;
    String lastName;
    String phoneNumber;
    LocalDate birthDate;
    BigDecimal balance;
    TariffPlanEntity tariffPlan;
}
