package ru.phoenixdnr.subscribers.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.phoenixdnr.subscribers.dto.input.TariffPlanInput;
import ru.phoenixdnr.subscribers.dto.output.TariffPlanOutput;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TariffPlanMapperTest {

    private TariffPlanMapper tariffPlanMapper;

    private TariffPlanInput input;

    @BeforeEach
    public void setUp() {
        tariffPlanMapper = new TariffPlanMapper();
        input = new TariffPlanInput();
        input.setId(1);
        input.setName("TestTariffPlan");
        input.setCost(new BigDecimal("20.00"));
    }

    @Test
    public void shouldReturnEntityInFromInput2arg() {
        TariffPlanEntity expected = new TariffPlanEntity(input.getName(), input.getCost());

        TariffPlanEntity actual = tariffPlanMapper.fromInput(input, expected);

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void shouldReturnOutputInToOutput() {
        TariffPlanEntity expected = new TariffPlanEntity("TestTariffPlan", new BigDecimal("20.00"));

        TariffPlanOutput actual = tariffPlanMapper.toOutput(expected);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCost(), actual.getCost());
    }

    @Test
    public void shouldReturnEntityInFromInput1arg() {

        TariffPlanEntity result = tariffPlanMapper.fromInput(input);

        assertEquals(input.getName(), result.getName());
        assertEquals(input.getCost(), result.getCost());
    }
}
