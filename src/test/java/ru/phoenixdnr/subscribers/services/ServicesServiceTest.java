package ru.phoenixdnr.subscribers.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.phoenixdnr.subscribers.dto.input.ServiceInput;
import ru.phoenixdnr.subscribers.dto.output.ServiceOutput;
import ru.phoenixdnr.subscribers.entity.ServiceEntity;
import ru.phoenixdnr.subscribers.mappers.ServiceMapper;
import ru.phoenixdnr.subscribers.repository.ServiceRepository;
import ru.phoenixdnr.subscribers.service.ServicesService;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicesServiceTest {

    private ServicesService service;
    private ServiceRepository repository;
    private ServiceMapper mapper;

    @BeforeEach
    public void setUp() {

        repository = mock(ServiceRepository.class);
        mapper = mock(ServiceMapper.class);

        repository = mock(ServiceRepository.class);
        mapper = mock(ServiceMapper.class);
        service = new ServicesService(repository, mapper);


    }

    @Test
    void shouldReturnOutputInGetAllElem() {

        ServiceEntity entity = new ServiceEntity("ased", BigDecimal.ONE);
        ServiceEntity entity1 = new ServiceEntity("ased", BigDecimal.valueOf(5));
        List<ServiceEntity> serviceEntities = Arrays.asList(entity, entity1);

        ServiceOutput output = new ServiceOutput(1, "asd", BigDecimal.valueOf(5));
        ServiceOutput output1 = new ServiceOutput(2, "asdsd", BigDecimal.valueOf(6));
        List<ServiceOutput> expected = Arrays.asList(output, output1);

        when(repository.findAll()).thenReturn(serviceEntities);

        when(mapper.toOutput(entity)).thenReturn(output);
        when(mapper.toOutput(entity1)).thenReturn(output1);

        List<ServiceOutput> actual = service.getAllElem();

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
        ServiceEntity entity = new ServiceEntity("ased", BigDecimal.ONE);
        ServiceOutput expected = new ServiceOutput(1, "asd", BigDecimal.ONE);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toOutput(entity)).thenReturn(expected);

        ServiceOutput actual = service.getElemById(id);

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
        ServiceInput input = new ServiceInput();
        ServiceEntity entity = new ServiceEntity("asfff", BigDecimal.valueOf(5));
        ServiceEntity expected = new ServiceEntity("ased", BigDecimal.ONE);

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

        ServiceInput input = new ServiceInput();
        ServiceEntity expected = new ServiceEntity("ased", BigDecimal.valueOf(5));

        when(mapper.fromInput(input)).thenReturn(expected);

        service.postEntity(input);

        verify(repository).save(expected);

    }

}
