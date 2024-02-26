package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.ServiceDto;
import ru.phoenixdnr.subscribers.entity.ServiceEntity;
import ru.phoenixdnr.subscribers.repository.ServiceRepository;
import ru.phoenixdnr.subscribers.utils.MappingUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServicesService {

    private final ServiceRepository repository;
    private final MappingUtils mappingUtils;

    public ServicesService(ServiceRepository repository, MappingUtils mappingUtils) {
        this.repository = repository; //внедрили зависимость через конструктор
        this.mappingUtils = mappingUtils;
    }

    public List<ServiceEntity> getAllElem() {
        return repository.findAll();
    }

    public ServiceEntity getElemById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Элемент с id " + id + " не найден"));
    }

    public void deleteElemById(int id) {
        repository.deleteById(id);
    }

    public void putEntityById(int id, ServiceDto serviceDto) {
        // Запрос сущности из репозитория
        Optional<ServiceEntity> entity = repository.findById(id);

        // Преобразование сущности в DTO
        Optional<ServiceDto> dto = mappingUtils.mapToServiceDto(entity);

        // Извлечение объекта ServiceDto из Optional
        ServiceDto dtoNonOptional = dto.orElseGet(ServiceDto::new);

        // Теперь устанавливаем значения
        dtoNonOptional.setName(serviceDto.getName());
        dtoNonOptional.setCost(serviceDto.getCost());

        // Обратно оборачиваем в Optional
        Optional<ServiceDto> updatedDtoOptional = Optional.of(dtoNonOptional);

        // Маппинг и сохранение сущности
        ServiceEntity updatedEntity = mappingUtils.mapToServiceEntity(updatedDtoOptional.get());
        repository.save(updatedEntity);
    }

}
