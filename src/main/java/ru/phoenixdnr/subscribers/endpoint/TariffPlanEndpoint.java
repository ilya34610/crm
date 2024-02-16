package ru.phoenixdnr.subscribers.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;
import ru.phoenixdnr.subscribers.service.TariffPlanService;

import java.util.List;

@RestController
@RequestMapping("/TariffPlans")
@RequiredArgsConstructor
public class TariffPlanEndpoint {

    private final TariffPlanService service;

    @GetMapping
    public List<TariffPlanEntity> getAllEntity() {
        return service.getAllElem();
    }

    @GetMapping("/{id}")
    public TariffPlanEntity getEntityById(@PathVariable int id) {
        return service.getElemById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEntityById(@PathVariable int id) {
        service.deleteElemById(id);
    }
}
