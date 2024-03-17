package ru.phoenixdnr.subscribers.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.phoenixdnr.subscribers.dto.input.TariffPlanInput;
import ru.phoenixdnr.subscribers.dto.output.TariffPlanOutput;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;
import ru.phoenixdnr.subscribers.mappers.TariffPlanMapper;
import ru.phoenixdnr.subscribers.repository.TariffPlanRepository;
import ru.phoenixdnr.subscribers.service.TariffPlanService;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TariffPlanServiceTest {

    private TariffPlanService service;
    private TariffPlanRepository repository;
    private TariffPlanMapper mapper;

    @BeforeEach
    public void setUp() {

        repository = mock(TariffPlanRepository.class);
        mapper = mock(TariffPlanMapper.class);

        repository = mock(TariffPlanRepository.class);
        mapper = mock(TariffPlanMapper.class);
        service = new TariffPlanService(repository, mapper);

    }

    @Test
    void shouldReturnOutputInGetAllElem() {

        TariffPlanEntity entity = new TariffPlanEntity("ased", BigDecimal.ONE);
        TariffPlanEntity entity1 = new TariffPlanEntity("ased", BigDecimal.valueOf(5));
        List<TariffPlanEntity> tariffPlanEntities = Arrays.asList(entity, entity1);

        TariffPlanOutput output = new TariffPlanOutput(1, "asd", BigDecimal.valueOf(5));
        TariffPlanOutput output1 = new TariffPlanOutput(2, "asdsd", BigDecimal.valueOf(6));
        List<TariffPlanOutput> expected = Arrays.asList(output, output1);

        when(repository.findAll()).thenReturn(tariffPlanEntities);

        when(mapper.toOutput(entity)).thenReturn(output);
        when(mapper.toOutput(entity1)).thenReturn(output1);

        List<TariffPlanOutput> actual = service.getAllElem();

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void shouldThrowNoSuchElementExceptionInGetElemById() {
        int id = 1;
        when(repository.findById(id)).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> service.getElemById(id));
    }

    @Test
    void shouldReturnOutputInGetElemById() {

        int id = 1;
        TariffPlanEntity entity = new TariffPlanEntity("ased", BigDecimal.ONE);
        TariffPlanOutput expected = new TariffPlanOutput(1, "asd", BigDecimal.ONE);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toOutput(entity)).thenReturn(expected);

        TariffPlanOutput actual = service.getElemById(id);

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    void shouldDeleteElemInDeleteElemById() {

        int id = 1;

        doNothing().when(repository).deleteById(id);

        service.deleteElemById(id);

        verify(repository, times(1)).deleteById(id);

    }

    @Test
    void shouldUpdateEntityInPutEntityById() {

        int id = 1;
        TariffPlanInput input = new TariffPlanInput();
        TariffPlanEntity entity = new TariffPlanEntity("asfff", BigDecimal.valueOf(5));
        TariffPlanEntity expected = new TariffPlanEntity("ased", BigDecimal.ONE);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.fromInput(input, entity)).thenReturn(expected);

        service.putEntityById(id, input);

        verify(repository).findById(id);
        verify(mapper).fromInput(input, entity);
        verify(repository).save(expected);
    }

    @Test
    void shouldThrowEntityNotFoundExceptionInPutEntityById() {

        int id = 1;
        when(repository.findById(id)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> service.getElemById(id));

    }

    @Test
    void shouldAddEntityInPostEntity() {

        TariffPlanInput input = new TariffPlanInput();
        TariffPlanEntity expected = new TariffPlanEntity("ased", BigDecimal.valueOf(5));

        when(mapper.fromInput(input)).thenReturn(expected);

        service.postEntity(input);

        verify(repository).save(expected);
    }
}
