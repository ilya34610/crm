package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.input.ServiceInput;
import ru.phoenixdnr.subscribers.entity.ServiceEntity;
import ru.phoenixdnr.subscribers.mappers.ServiceMapper;
import ru.phoenixdnr.subscribers.repository.ServiceRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServicesService {

    private final ServiceRepository repository;

    public ServicesService(ServiceRepository repository) {
        this.repository = repository; //внедрили зависимость через конструктор
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

    public void putEntityById(int id, ServiceInput input) {
        // Запрос сущности из репозитория
        Optional<ServiceEntity> entity = repository.findById(id);

        if (entity.isPresent()) {
            ServiceEntity updateEntity = ServiceMapper.fromInput(input, entity.get());
            repository.save(updateEntity);
        } else {
            throw new EntityNotFoundException("Entity not found with id: " + id);
        }
    }

}
