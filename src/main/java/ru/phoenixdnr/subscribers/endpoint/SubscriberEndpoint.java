package ru.phoenixdnr.subscribers.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.phoenixdnr.subscribers.entity.SubscriberEntity;
import ru.phoenixdnr.subscribers.service.SubscriberService;

import java.util.List;

@RestController
@RequestMapping("/subscribers")
@RequiredArgsConstructor
public class SubscriberEndpoint {

    private final SubscriberService service;

    @GetMapping
    public List<SubscriberEntity> getAllEntity() {
        return service.getAllElem();
    }

    @GetMapping("/{id}")
    public SubscriberEntity getEntityById(@PathVariable long id) {
        return service.getElemById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEntityById(@PathVariable long id) {
        service.deleteElemById(id);
    }
}
