package ru.phoenixdnr.subscribers.mappers;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.input.SubscriberInput;
import ru.phoenixdnr.subscribers.dto.output.SubscriberOutput;
import ru.phoenixdnr.subscribers.entity.SubscriberEntity;

@Service
public class SubscriberMapper {


    public SubscriberEntity fromInput(SubscriberInput subscriberInput, SubscriberEntity entity) {
        entity.setFirstName(subscriberInput.getFirstName());
        entity.setLastName(subscriberInput.getLastName());
        entity.setPhoneNumber(subscriberInput.getPhoneNumber());
        entity.setBirthDate(subscriberInput.getBirthDate());
        entity.setBalance(subscriberInput.getBalance());
        entity.setTariffPlan(subscriberInput.getTariffPlan());
        return entity;
    }

    public SubscriberOutput toOutput(SubscriberEntity entity) {
        return new SubscriberOutput(entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhoneNumber(),
                entity.getBirthDate(),
                entity.getBalance(),
                entity.getTariffPlan());
    }
}