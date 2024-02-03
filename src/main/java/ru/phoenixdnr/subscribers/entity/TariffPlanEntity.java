package ru.phoenixdnr.subscribers.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tariff_plans")
public class TariffPlanEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private BigDecimal cost;
}