package ru.phoenixdnr.subscribers.mappers;

import org.springframework.stereotype.Service;
import ru.phoenixdnr.subscribers.dto.input.TariffPlanInput;
import ru.phoenixdnr.subscribers.dto.output.TariffPlanOutput;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;

@Service
public class TariffPlanMapper {

    public TariffPlanEntity fromInput(TariffPlanInput tariffPlanInput, TariffPlanEntity entity) {
        entity.setName(tariffPlanInput.getName());
        entity.setCost(tariffPlanInput.getCost());
        return entity;
    }

    public TariffPlanOutput toOutput(TariffPlanEntity entity) {
        return new TariffPlanOutput(entity.getId(), entity.getName(), entity.getCost());
    }
}