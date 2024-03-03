package ru.phoenixdnr.subscribers.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.phoenixdnr.subscribers.dto.input.ServiceInput;
import ru.phoenixdnr.subscribers.dto.output.ServiceOutput;
import ru.phoenixdnr.subscribers.service.ServicesService;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceEndpoint {

    private final ServicesService service;

    @GetMapping
    public List<ServiceOutput> getAllEntity() {
        return service.getAllElem();
    }

    @GetMapping("/{id}")
    public ServiceOutput getEntityById(@PathVariable int id) {
        return service.getElemById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEntityById(@PathVariable int id) {
        service.deleteElemById(id);
    }

    @PutMapping("/{id}")
    public void putEntityById(@PathVariable Integer id, @RequestBody ServiceInput input) {
        service.putEntityById(id, input);
    }

    @PostMapping
    public void postEntity(@RequestBody ServiceInput input) {
        service.postEntity(input);
    }
}
