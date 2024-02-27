package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.input.SubscriberInput;
import ru.phoenixdnr.subscribers.entity.SubscriberEntity;
import ru.phoenixdnr.subscribers.mappers.SubscriberMapper;
import ru.phoenixdnr.subscribers.repository.SubscriberRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public void putEntityById(Long id, SubscriberInput subscriberInput) {
        // Запрос сущности из репозитория
        Optional<SubscriberEntity> entity = repository.findById(id);

        if (entity.isPresent()) {
            SubscriberEntity updateEntity = SubscriberMapper.fromInput(subscriberInput, entity.get());
            repository.save(updateEntity);
        } else {
            throw new EntityNotFoundException("Entity not found with id: " + id);
        }
    }

}
