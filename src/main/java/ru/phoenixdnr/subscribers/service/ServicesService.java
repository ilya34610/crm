package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.input.ServiceInput;
import ru.phoenixdnr.subscribers.dto.output.ServiceOutput;
import ru.phoenixdnr.subscribers.entity.ServiceEntity;
import ru.phoenixdnr.subscribers.mappers.ServiceMapper;
import ru.phoenixdnr.subscribers.repository.ServiceRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    private final ServiceRepository repository;
    private final ServiceMapper mapper;

    public ServicesService(ServiceRepository repository, ServiceMapper mapper) {
        this.repository = repository; //внедрили зависимость через конструктор
        this.mapper = mapper;
    }

    public List<ServiceOutput> getAllElem() {
        return repository.findAll().stream()
                .map(mapper::toOutput)
                .collect(Collectors.toList());
    }

    public ServiceOutput getElemById(int id) {
        return repository.findById(id)
                .map(mapper::toOutput)
                .orElseThrow(() -> new NoSuchElementException("Элемент с id " + id + " не найден"));
    }

    public void deleteElemById(int id) {
        repository.deleteById(id);
    }

    public void putEntityById(int id, ServiceInput serviceInput) {
        ServiceEntity entity = repository.findById(id)
                .map(existingEntity -> mapper.fromInput(serviceInput, existingEntity))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
        repository.save(entity);
    }

    public void postEntity(ServiceInput serviceInput) {
        repository.save(mapper.fromInput(serviceInput));
    }
}
