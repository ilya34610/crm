package ru.phoenixdnr.subscribers.utils;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.ServiceDto;
import ru.phoenixdnr.subscribers.dto.SubscriberDto;
import ru.phoenixdnr.subscribers.dto.TariffPlanDto;
import ru.phoenixdnr.subscribers.entity.ServiceEntity;
import ru.phoenixdnr.subscribers.entity.SubscriberEntity;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;

import java.util.Optional;

@Service
public class MappingUtils {
    //из entity в dto (services)
    public Optional<ServiceDto> mapToServiceDto(Optional<ServiceEntity> optionalEntity) {
        return optionalEntity.map(entity -> {
            ServiceDto dto = new ServiceDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCost(entity.getCost());
            return dto;
        });
    }

    //из dto в entity (services)
    public ServiceEntity mapToServiceEntity(ServiceDto dto) {
        ServiceEntity entity = new ServiceEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCost(dto.getCost());
        return entity;
    }

    //из entity в dto (tariffPlans)
    public Optional<TariffPlanDto> mapToTariffPlanDto(Optional<TariffPlanEntity> optionalEntity) {
        return optionalEntity.map(entity -> {
            TariffPlanDto dto = new TariffPlanDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCost(entity.getCost());
            return dto;
        });
    }

    //из dto в entity (tariffPlans)
    public TariffPlanEntity mapToTariffPlanEntity(TariffPlanDto dto) {
        TariffPlanEntity entity = new TariffPlanEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCost(dto.getCost());
        return entity;
    }

    //из entity в dto (subscriber)
    public Optional<SubscriberDto> mapToSubscriberDto(Optional<SubscriberEntity> optionalEntity) {
        return optionalEntity.map(entity -> {
            SubscriberDto dto = new SubscriberDto();
            dto.setId(entity.getId());
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            dto.setPhoneNumber(entity.getPhoneNumber());
            dto.setBirthDate(entity.getBirthDate());
            dto.setBalance(entity.getBalance());
            dto.setTariffPlan(entity.getTariffPlan());
            return dto;
        });
    }

    //из dto в entity (subscriber)
    public SubscriberEntity mapToSubscriberEntity(SubscriberDto optionalDto) {
        SubscriberEntity entity = new SubscriberEntity();
        entity.setId(optionalDto.getId());
        entity.setFirstName(optionalDto.getFirstName());
        entity.setLastName(optionalDto.getLastName());
        entity.setPhoneNumber(optionalDto.getPhoneNumber());
        entity.setBirthDate(optionalDto.getBirthDate());
        entity.setBalance(optionalDto.getBalance());
        entity.setTariffPlan(optionalDto.getTariffPlan());
        return entity;
    }
}
