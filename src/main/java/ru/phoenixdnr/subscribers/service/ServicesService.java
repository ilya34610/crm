package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.entity.ServiceEntity;
import ru.phoenixdnr.subscribers.repository.ServiceRepository;

import java.util.List;
import java.util.NoSuchElementException;

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
}
