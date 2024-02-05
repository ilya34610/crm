package ru.phoenixdnr.subscribers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;

@Repository
public interface TariffPlanRepository extends JpaRepository<TariffPlanEntity, Integer> {
}
