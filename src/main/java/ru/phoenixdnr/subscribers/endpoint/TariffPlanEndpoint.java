package ru.phoenixdnr.subscribers.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.phoenixdnr.subscribers.dto.input.TariffPlanInput;
import ru.phoenixdnr.subscribers.dto.output.TariffPlanOutput;
import ru.phoenixdnr.subscribers.service.TariffPlanService;

import java.util.List;

@RestController
@RequestMapping("/tariffPlans")
@RequiredArgsConstructor
public class TariffPlanEndpoint {

    private final TariffPlanService service;

    @GetMapping
    public List<TariffPlanOutput> getAllEntity() {
        return service.getAllElem();
    }

    @GetMapping("/{id}")
    public TariffPlanOutput getEntityById(@PathVariable int id) {
        return service.getElemById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEntityById(@PathVariable int id) {
        service.deleteElemById(id);
    }

    @PutMapping("/{id}")
    public void putEntityById(@PathVariable Integer id, @RequestBody TariffPlanInput input) {
        service.putEntityById(id, input);
    }

    @PostMapping
    public void postEntity(@RequestBody TariffPlanInput input) {
        service.postEntity(input);
    }
}
