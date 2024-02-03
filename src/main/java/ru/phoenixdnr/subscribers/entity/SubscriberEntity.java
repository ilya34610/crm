package ru.phoenixdnr.subscribers.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "subscribers")
public class SubscriberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToMany
    private TariffPlanEntity tariffPlan;

    @ManyToMany
    @JoinTable(name = "subscribers_services",
            joinColumns = @JoinColumn(name = "id_subscriber"),
            inverseJoinColumns = @JoinColumn(name = "id_service")
    )
    private List<ServiceEntity> services;

}
