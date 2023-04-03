package com.example.cars;

import com.example.cars.api.exception.MyObjectNotFoundException;
import com.example.cars.domain.Carro;
import com.example.cars.domain.CarroDto;
import com.example.cars.domain.CarroService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
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

        CarroDto carroCriado = service.getById(id);
        assertTrue(carroCriado != null);

        assertEquals("nomeTeste1", carroCriado.getNome());
        assertEquals("tipoTeste1", carroCriado.getTipo());

        service.deleteById(id);
        try {
            assertNull(service.getById(id));
            fail("O objeto não foi excluído!");
        } catch (MyObjectNotFoundException exception) {
        }
    }

    @Test
    void test2() {
        List<CarroDto> carroDtos = service.getCarros();
        assertEquals(30, carroDtos.size());
    }

}
