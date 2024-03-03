package ru.phoenixdnr.subscribers.mappers;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.input.ServiceInput;
import ru.phoenixdnr.subscribers.dto.output.ServiceOutput;
import ru.phoenixdnr.subscribers.entity.ServiceEntity;

@Service
public class ServiceMapper {
    public ServiceEntity fromInput(ServiceInput serviceInput, ServiceEntity entity) {
        entity.setName(serviceInput.getName());
        entity.setCost(serviceInput.getCost());
        return entity;
    }

    public ServiceOutput toOutput(ServiceEntity entity) {
        return new ServiceOutput(entity.getId(), entity.getName(), entity.getCost());
    }

    public ServiceEntity fromInput(ServiceInput serviceInput) {
        return new ServiceEntity(serviceInput.getName(), serviceInput.getCost());
    }
}