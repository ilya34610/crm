package ru.phoenixdnr.subscribers.mappers;

import ru.phoenixdnr.subscribers.dto.input.TariffPlanInput;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;

public class TariffPlanMapper {

    public static TariffPlanEntity fromInput(TariffPlanInput tariffPlanInput, TariffPlanEntity entity) {
        entity.setName(tariffPlanInput.getName());
        entity.setCost(tariffPlanInput.getCost());
        return entity;
    }

}
