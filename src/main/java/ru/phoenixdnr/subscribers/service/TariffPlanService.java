package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.input.TariffPlanInput;
import ru.phoenixdnr.subscribers.dto.output.TariffPlanOutput;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;
import ru.phoenixdnr.subscribers.mappers.TariffPlanMapper;
import ru.phoenixdnr.subscribers.repository.TariffPlanRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TariffPlanService {

    private final TariffPlanRepository repository;
    private final TariffPlanMapper mapper;

    public TariffPlanService(TariffPlanRepository repository, TariffPlanMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TariffPlanOutput> getAllElem() {
        return repository.findAll().stream()
                .map(mapper::toOutput)
                .collect(Collectors.toList());
    }

    public TariffPlanOutput getElemById(int id) {
        return repository.findById(id)
                .map(mapper::toOutput)
                .orElseThrow(() -> new NoSuchElementException("Элемент с id " + id + " не найден"));
    }

    public void deleteElemById(int id) {
        repository.deleteById(id);
    }

    public void putEntityById(int id, TariffPlanInput tariffPlanInput) {
        TariffPlanEntity entity = repository.findById(id)
                .map(existingEntity -> mapper.fromInput(tariffPlanInput, existingEntity))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
        repository.save(entity);
    }
}




