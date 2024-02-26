package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.SubscriberDto;
import ru.phoenixdnr.subscribers.entity.SubscriberEntity;
import ru.phoenixdnr.subscribers.repository.SubscriberRepository;
import ru.phoenixdnr.subscribers.utils.MappingUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SubscriberService {

    private final SubscriberRepository repository;
    private final MappingUtils mappingUtils;

    public SubscriberService(SubscriberRepository repository, MappingUtils mappingUtils) {
        this.repository = repository;
        this.mappingUtils = mappingUtils;
    }

    public List<SubscriberEntity> getAllElem() {
        return repository.findAll();
    }

    public SubscriberEntity getElemById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Элемент с id " + id + " не найден"));
    }

    public void deleteElemById(long id) {
        repository.deleteById(id);
    }

    public void putEntityById(Long id, SubscriberDto subscriberDto) {
        // Запрос сущности из репозитория
        Optional<SubscriberEntity> entity = repository.findById(id);

        // Преобразование сущности в DTO
        Optional<SubscriberDto> dto = mappingUtils.mapToSubscriberDto(entity);

        // Извлечение объекта ServiceDto из Optional
        SubscriberDto dtoNonOptional = dto.orElseGet(SubscriberDto::new);

        // Теперь устанавливаем значения
        dtoNonOptional.setFirstName(subscriberDto.getFirstName());
        dtoNonOptional.setLastName(subscriberDto.getLastName());
        dtoNonOptional.setPhoneNumber(subscriberDto.getPhoneNumber());
        dtoNonOptional.setBirthDate(subscriberDto.getBirthDate());
        dtoNonOptional.setBalance(subscriberDto.getBalance());
        dtoNonOptional.setTariffPlan(dtoNonOptional.getTariffPlan());

        // Обратно оборачиваем в Optional
        Optional<SubscriberDto> updatedDtoOptional = Optional.of(dtoNonOptional);

        // Маппинг и сохранение сущности
        SubscriberEntity updatedEntity = mappingUtils.mapToSubscriberEntity(updatedDtoOptional.get());
        repository.save(updatedEntity);
    }
}
