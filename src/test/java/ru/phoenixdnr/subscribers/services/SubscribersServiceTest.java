package ru.phoenixdnr.subscribers.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.phoenixdnr.subscribers.dto.input.SubscriberInput;
import ru.phoenixdnr.subscribers.dto.output.SubscriberOutput;
import ru.phoenixdnr.subscribers.entity.SubscriberEntity;
import ru.phoenixdnr.subscribers.entity.TariffPlanEntity;
import ru.phoenixdnr.subscribers.mappers.SubscriberMapper;
import ru.phoenixdnr.subscribers.repository.SubscriberRepository;
import ru.phoenixdnr.subscribers.service.SubscriberService;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SubscribersServiceTest {

    private SubscriberService service;
    private SubscriberRepository repository;
    private SubscriberMapper mapper;

    @BeforeEach
    public void setUp() {
        repository = mock(SubscriberRepository.class);
        mapper = mock(SubscriberMapper.class);

        repository = mock(SubscriberRepository.class);
        mapper = mock(SubscriberMapper.class);
        service = new SubscriberService(repository, mapper);
    }

    @Test
    void shouldReturnOutputInGetAllElem() {

        SubscriberEntity entity = new SubscriberEntity("NewTestName",
                "NewTestLastName",
                "1234567890",
                LocalDate.of(1990, 1, 1),
                new BigDecimal("30.00"),
                new TariffPlanEntity("NewTariff", new BigDecimal("15.00")));

        SubscriberEntity entity1 = new SubscriberEntity("NewTestName1",
                "NewTestLastName1",
                "1234567891",
                LocalDate.of(1990, 1, 1),
                new BigDecimal("30.00"),
                new TariffPlanEntity("NewTariff", new BigDecimal("15.00")));


        List<SubscriberEntity> subscriberEntities = Arrays.asList(entity, entity1);

        SubscriberOutput output = new SubscriberOutput(1L,
                "asd",
                "asd",
                "12341342",
                LocalDate.of(1990, 1, 1),
                new BigDecimal("30.00"),
                new TariffPlanEntity("NewTariff", new BigDecimal("15.00"))
        );
        SubscriberOutput output1 = new SubscriberOutput(2L,
                "asd",
                "asd",
                "12341342",
                LocalDate.of(1990, 1, 1),
                new BigDecimal("30.00"),
                new TariffPlanEntity("NewTariff", new BigDecimal("15.00"))
        );

        List<SubscriberOutput> expected = Arrays.asList(output, output1);

        when(repository.findAll()).thenReturn(subscriberEntities);

        when(mapper.toOutput(entity)).thenReturn(output);
        when(mapper.toOutput(entity1)).thenReturn(output1);

        List<SubscriberOutput> actual = service.getAllElem();

        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    void shouldThrowNoSuchElementExceptionInGetElemById() {
        long id = 1L;
        when(repository.findById(id)).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> service.getElemById(id));
    }

    @Test
    void shouldReturnOutputInGetElemById() {

        long id = 1L;
        SubscriberEntity entity = new SubscriberEntity("NewTestName",
                "NewTestLastName",
                "1234567890",
                LocalDate.of(1990, 1, 1),
                new BigDecimal("30.00"),
                new TariffPlanEntity("NewTariff", new BigDecimal("15.00")));

        SubscriberOutput expected = new SubscriberOutput(1L,
                "asd",
                "asd",
                "12341342",
                LocalDate.of(1990, 1, 1),
                new BigDecimal("30.00"),
                new TariffPlanEntity("NewTariff", new BigDecimal("15.00"))
        );

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toOutput(entity)).thenReturn(expected);

        SubscriberOutput actual = service.getElemById(id);

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    void shouldDeleteElemInDeleteElemById() {

        long id = 1L;

        doNothing().when(repository).deleteById(id);

        service.deleteElemById(id);

        verify(repository, times(1)).deleteById(id);

    }

    @Test
    void shouldUpdateEntityInPutEntityById() {

        long id = 1L;
        SubscriberInput input = new SubscriberInput();
        SubscriberEntity entity = new SubscriberEntity("NewTestName",
                "NewTestLastName",
                "1234567890",
                LocalDate.of(1990, 1, 1),
                new BigDecimal("30.00"),
                new TariffPlanEntity("NewTariff", new BigDecimal("15.00")));

        SubscriberEntity expected = new SubscriberEntity("NewTestName1",
                "NewTestLastName1",
                "1234567891",
                LocalDate.of(1990, 1, 1),
                new BigDecimal("30.00"),
                new TariffPlanEntity("NewTariff", new BigDecimal("15.00")));


        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.fromInput(input, entity)).thenReturn(expected);

        service.putEntityById(id, input);

        verify(repository).findById(id);
        verify(mapper).fromInput(input, entity);
        verify(repository).save(expected);
    }

    @Test
    void shouldThrowEntityNotFoundExceptionInPutEntityById() {

        long id = 1L;
        when(repository.findById(id)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> service.getElemById(id));

    }

    @Test
    void shouldAddEntityInPostEntity() {

        SubscriberInput input = new SubscriberInput();
        SubscriberEntity expected = new SubscriberEntity("NewTestName1",
                "NewTestLastName1",
                "1234567891",
                LocalDate.of(1990, 1, 1),
                new BigDecimal("30.00"),
                new TariffPlanEntity("NewTariff", new BigDecimal("15.00")));

        when(mapper.fromInput(input)).thenReturn(expected);

        service.postEntity(input);

        verify(repository).save(expected);

    }

}
