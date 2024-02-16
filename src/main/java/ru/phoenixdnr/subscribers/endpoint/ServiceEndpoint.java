package ru.phoenixdnr.subscribers.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.phoenixdnr.subscribers.entity.ServiceEntity;
import ru.phoenixdnr.subscribers.service.ServicesService;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceEndpoint {

    private final ServicesService service;

    @GetMapping
    public List<ServiceEntity> getAllEntity() {
        return service.getAllElem();
    }

    @GetMapping("/{id}")
    public ServiceEntity getEntityById(@PathVariable int id) {
        return service.getElemById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEntityById(@PathVariable int id) {
        service.deleteElemById(id);
    }
}
