package com.example.cars;

import com.example.cars.domain.Carro;
import com.example.cars.domain.CarroDto;
import com.example.cars.domain.CarroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class CarroServiceTest {

    @Autowired
    CarroService service;

    @Test
    void contextLoads() {
    }

    @Test
    void test1() {
        Carro carro = new Carro();
        carro.setNome("nomeTeste1");
        carro.setTipo("tipoTeste1");

        CarroDto carroDto = service.saveCarro(carro);
        assertNotNull(carroDto);

        Long id = carroDto.getId();
        assertNotNull(id);

        Optional<CarroDto> carroCriado = service.getById(id);
        assertTrue(carroCriado.isPresent());

       assertEquals("nomeTeste1", carroCriado.get().getNome());
       assertEquals("tipoTeste1", carroCriado.get().getTipo());

       service.deleteById(id);

       assertFalse(service.getById(id).isPresent());
    }

    @Test
    void test2() {
        List<CarroDto> carroDtos = service.getCarros();
        assertEquals(30, carroDtos.size() );
    }

}
