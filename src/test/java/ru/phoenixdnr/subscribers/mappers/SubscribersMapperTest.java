package ru.phoenixdnr.subscribers.mappers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.phoenixdnr.subscribers.dto.input.SubscriberInput;
import ru.phoenixdnr.subscribers.dto.output.SubscriberOutput;
import ru.phoenixdnr.subscribers.entity.SubscriberEntity;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscribersMapperTest {

    private SubscriberMapper subscriberMapper;

    private SubscriberInput input;

    @BeforeEach
    public void setUp() {
        subscriberMapper = new SubscriberMapper();
        input = new SubscriberInput();
        input.setFirstName("testName");
        input.setLastName("testLastName");
        input.setPhoneNumber("0123456789");
        input.setBirthDate(LocalDate.ofEpochDay(1990 - 01 - 01));
        input.setBalance(BigDecimal.valueOf(30.00));
        input.setTariffPlan(new TariffPlanEntity("NameTest", BigDecimal.valueOf(10.00)));
    }

    @Test
    public void shouldReturnEntityInFromInput2arg() {

        SubscriberEntity expected = new SubscriberEntity(input.getFirstName(), input.getLastName(), input.getPhoneNumber(), input.getBirthDate(), input.getBalance(), input.getTariffPlan());

        SubscriberEntity actual = subscriberMapper.fromInput(input, expected);

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void shouldReturnOutputInToOutput() {
        SubscriberEntity expected = new SubscriberEntity("testName", "testLastName", "0123456789", LocalDate.ofEpochDay(1990 - 01 - 01), BigDecimal.valueOf(30.00), new TariffPlanEntity("NameTest", BigDecimal.valueOf(10.00)));

        SubscriberOutput actual = subscriberMapper.toOutput(expected);

        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        assertEquals(expected.getBirthDate(), actual.getBirthDate());
        assertEquals(expected.getBalance(), actual.getBalance());
        assertEquals(expected.getTariffPlan(), actual.getTariffPlan());
    }

    @Test
    public void shouldReturnEntityInFromInput1arg() {

        SubscriberEntity result = subscriberMapper.fromInput(input);

        assertEquals(input.getFirstName(), result.getFirstName());
        assertEquals(input.getLastName(), result.getLastName());
        assertEquals(input.getFirstName(), result.getFirstName());
        assertEquals(input.getLastName(), result.getLastName());
        assertEquals(input.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(input.getBirthDate(), result.getBirthDate());
        assertEquals(input.getBalance(), result.getBalance());
        assertEquals(input.getTariffPlan(), result.getTariffPlan());
    }


}
