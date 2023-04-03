package com.example.cars;

import com.example.cars.domain.Carro;
import com.example.cars.domain.CarroDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITest {
    @Autowired
    protected TestRestTemplate rest;

    private ResponseEntity<CarroDto> getCarro(String url) {
        return rest.getForEntity(url, CarroDto.class);
    }

    private ResponseEntity<List<CarroDto>> getCarros(String url) {
        return rest.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
    }

    @Test
    public void testList() {
        List<CarroDto> lista = getCarros("/api/v1/carros").getBody();
        assertNotNull(lista);
        assertEquals(lista.size(), 30);
    }

    @Test
    public void testTipoDesconhecido() {
        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/unknown").getStatusCode());
    }

    @Test
    public void testNotFound() {
        assertEquals(HttpStatus.NOT_FOUND, getCarro("/api/v1/carro/999").getStatusCode());
    }

    @Test
    public void testSave() {
        Carro carro = new Carro();
        carro.setNome("nomeTeste");
        carro.setTipo("testeTipo");
        carro.setDescricao("testeDescricao");
        assertEquals(HttpStatus.CREATED, rest.postForEntity("/api/v1/carros", carro, null).getStatusCode());
    }
}
