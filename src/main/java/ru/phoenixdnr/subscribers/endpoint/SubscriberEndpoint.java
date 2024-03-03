package ru.phoenixdnr.subscribers.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.phoenixdnr.subscribers.dto.input.SubscriberInput;
import ru.phoenixdnr.subscribers.dto.output.SubscriberOutput;
import ru.phoenixdnr.subscribers.service.SubscriberService;

import java.util.List;

@RestController
@RequestMapping("/subscribers")
@RequiredArgsConstructor
public class SubscriberEndpoint {

    private final SubscriberService service;

    @GetMapping
    public List<SubscriberOutput> getAllEntity() {
        return service.getAllElem();
    }

    @GetMapping("/{id}")
    public SubscriberOutput getEntityById(@PathVariable long id) {
        return service.getElemById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEntityById(@PathVariable long id) {
        service.deleteElemById(id);
    }

    @PutMapping("/{id}")
    public void putEntityById(@PathVariable Long id, @RequestBody SubscriberInput input) {
        service.putEntityById(id, input);
    }

    @PostMapping
    public void postEntity(@RequestBody SubscriberInput input) {
        service.postEntity(input);
    }
}
