package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.repository.ServiceRepository;

@Service
public class ServicesService {

    private final ServiceRepository repository;

    public ServicesService(ServiceRepository repository) {
        this.repository = repository;
    }

}
