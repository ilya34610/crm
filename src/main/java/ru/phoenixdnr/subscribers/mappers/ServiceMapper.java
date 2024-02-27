package ru.phoenixdnr.subscribers.mappers;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.input.ServiceInput;
import ru.phoenixdnr.subscribers.entity.ServiceEntity;

@Service
public class ServiceMapper {

    public static ServiceEntity fromInput(ServiceInput serviceInput, ServiceEntity entity) {
        entity.setName(serviceInput.getName());
        entity.setCost(serviceInput.getCost());
        return entity;
    }
}
