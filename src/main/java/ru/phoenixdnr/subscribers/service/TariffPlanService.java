package ru.phoenixdnr.subscribers.service;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.repository.TariffPlanRepository;

@Service
public class TariffPlanService {

    private final TariffPlanRepository repository;

    public TariffPlanService(TariffPlanRepository repository) {
        this.repository = repository;
    }

}
