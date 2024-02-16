package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;
import ru.phoenixdnr.subscribers.repository.TariffPlanRepository;

import java.util.List;
import java.util.NoSuchElementException;

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
}




