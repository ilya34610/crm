package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.entity.SubscriberEntity;
import ru.phoenixdnr.subscribers.repository.SubscriberRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubscriberService {

    private final SubscriberRepository repository;

    public SubscriberService(SubscriberRepository repository) {
        this.repository = repository;
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
}
