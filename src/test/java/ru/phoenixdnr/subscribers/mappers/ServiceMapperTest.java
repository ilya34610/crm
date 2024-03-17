package ru.phoenixdnr.subscribers.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.phoenixdnr.subscribers.dto.input.ServiceInput;
import ru.phoenixdnr.subscribers.dto.output.ServiceOutput;
import ru.phoenixdnr.subscribers.entity.ServiceEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ServiceMapperTest {

    private ServiceMapper serviceMapper;

    private ServiceInput input;

    @BeforeEach
    public void setUp() {
        serviceMapper = new ServiceMapper();
        input = new ServiceInput();
        input.setId(1);
        input.setName("TestService");
        input.setCost(new BigDecimal("10.00"));
    }

    @Test
    public void shouldReturnEntityInFromInput2arg() {
        ServiceEntity expected = new ServiceEntity(input.getName(), input.getCost());

        ServiceEntity actual = serviceMapper.fromInput(input, expected);

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void shouldReturnOutputInToOutput() {
        ServiceEntity expected = new ServiceEntity("TestService", new BigDecimal("10.00"));

        ServiceOutput actual = serviceMapper.toOutput(expected);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCost(), actual.getCost());
    }

    @Test
    public void shouldReturnEntityInFromInput1arg() {

        ServiceEntity result = serviceMapper.fromInput(input);

        assertEquals(input.getName(), result.getName());
        assertEquals(input.getCost(), result.getCost());
    }
}
