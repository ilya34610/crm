package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.input.SubscriberInput;
import ru.phoenixdnr.subscribers.dto.output.SubscriberOutput;
import ru.phoenixdnr.subscribers.entity.SubscriberEntity;
import ru.phoenixdnr.subscribers.mappers.SubscriberMapper;
import ru.phoenixdnr.subscribers.repository.SubscriberRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SubscriberService {

    private final SubscriberRepository repository;
    private final SubscriberMapper mapper;

    public SubscriberService(SubscriberRepository repository, SubscriberMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<SubscriberOutput> getAllElem() {
        return repository.findAll().stream()
                .map(mapper::toOutput)
                .collect(Collectors.toList());
    }

    public SubscriberOutput getElemById(long id) {
        return repository.findById(id)
                .map(mapper::toOutput)
                .orElseThrow(() -> new NoSuchElementException("Элемент с id " + id + " не найден"));
    }

    public void deleteElemById(long id) {
        repository.deleteById(id);
    }

    public void putEntityById(Long id, SubscriberInput subscriberInput) {
        SubscriberEntity entity = repository.findById(id)
                .map(existingEntity -> mapper.fromInput(subscriberInput, existingEntity))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
        repository.save(entity);
    }

    public void postEntity(SubscriberInput subscriberInput) {
        repository.save(mapper.fromInput(subscriberInput));
    }
}
