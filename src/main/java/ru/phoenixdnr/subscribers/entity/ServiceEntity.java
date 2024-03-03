package ru.phoenixdnr.subscribers.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "services")
@Data
public class ServiceEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private BigDecimal cost;

    public ServiceEntity(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
    }
}
