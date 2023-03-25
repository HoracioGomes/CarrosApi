package com.example.cars.api;

import com.example.cars.domain.Carro;
import com.example.cars.domain.CarroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    CarroService service = new CarroService();

    @GetMapping
    List<Carro> getCarros() {
        return service.getCarros();
    }
}
