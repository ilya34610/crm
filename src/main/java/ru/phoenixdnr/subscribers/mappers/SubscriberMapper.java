package ru.phoenixdnr.subscribers.mappers;

import ru.phoenixdnr.subscribers.dto.input.SubscriberInput;
import ru.phoenixdnr.subscribers.entity.SubscriberEntity;

public class SubscriberMapper {

    public static SubscriberEntity fromInput(SubscriberInput subscriberInput, SubscriberEntity entity) {
        entity.setFirstName(subscriberInput.getFirstName());
        entity.setLastName(subscriberInput.getLastName());
        entity.setPhoneNumber(subscriberInput.getPhoneNumber());
        entity.setBirthDate(subscriberInput.getBirthDate());
        entity.setBalance(subscriberInput.getBalance());
        entity.setTariffPlan(subscriberInput.getTariffPlan());
        return entity;
    }
}
