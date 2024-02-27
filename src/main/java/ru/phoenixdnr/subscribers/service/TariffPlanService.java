package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.input.TariffPlanInput;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;
import ru.phoenixdnr.subscribers.repository.TariffPlanRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ru.phoenixdnr.subscribers.mappers.TariffPlanMapper.fromInput;

@Service
public class TariffPlanService {

    private final TariffPlanRepository repository;

    public TariffPlanService(TariffPlanRepository repository) {
        this.repository = repository;
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

    public void putEntityById(int id, TariffPlanInput tariffPlanDto) {
        // Запрос сущности из репозитория
        Optional<TariffPlanEntity> entity = repository.findById(id);

        if (entity.isPresent()) {
            TariffPlanEntity updateEntity = fromInput(tariffPlanDto, entity.get());
            repository.save(updateEntity);
        } else {
            throw new EntityNotFoundException("Entity not found with id: " + id);
        }
    }

}




