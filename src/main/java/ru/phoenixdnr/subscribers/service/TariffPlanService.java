package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.TariffPlanDto;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;
import ru.phoenixdnr.subscribers.repository.TariffPlanRepository;
import ru.phoenixdnr.subscribers.utils.MappingUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TariffPlanService {

    private final TariffPlanRepository repository;
    private final MappingUtils mappingUtils;

    public TariffPlanService(TariffPlanRepository repository, MappingUtils mappingUtils) {
        this.repository = repository;
        this.mappingUtils = mappingUtils;
    }

    public List<TariffPlanEntity> getAllElem() {
        return repository.findAll();
    }

    public TariffPlanEntity getElemById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Элемент с id " + id + " не найден"));
    }

    public void deleteElemById(int id) {
        repository.deleteById(id);
    }

    public void putEntityById(int id, TariffPlanDto tariffPlanDto) {
        // Запрос сущности из репозитория
        Optional<TariffPlanEntity> entity = repository.findById(id);

        // Преобразование сущности в DTO
        Optional<TariffPlanDto> dto = mappingUtils.mapToTariffPlanDto(entity);

        // Извлечение объекта ServiceDto из Optional
        TariffPlanDto dtoNonOptional = dto.orElseGet(TariffPlanDto::new);

        // Теперь устанавливаем значения
        dtoNonOptional.setName(tariffPlanDto.getName());
        dtoNonOptional.setCost(tariffPlanDto.getCost());

        // Обратно оборачиваем в Optional
        Optional<TariffPlanDto> updatedDtoOptional = Optional.of(dtoNonOptional);

        // Маппинг и сохранение сущности
        TariffPlanEntity updatedEntity = mappingUtils.mapToTariffPlanEntity(updatedDtoOptional.get());
        repository.save(updatedEntity);
    }
}




