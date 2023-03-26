package com.example.cars.api;

import com.example.cars.domain.Carro;
import com.example.cars.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    CarroService service;

    @GetMapping
    Iterable<Carro> getCarros() {
        return service.getCarros();
    }

    @GetMapping("/{id}")
    public Optional<Carro> getById(@PathVariable("id") Long id){
        return  service.getById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> getByTipo(@PathVariable("tipo") String tipo){
        return service.getByTipo(tipo);
    }

    @PostMapping
    public String saveCarro(@RequestBody Carro carro){
        Carro carroSalvo = service.saveCarro(carro);
        return "Carro "+carroSalvo.getNome()+" Salvo!";
    }

    @PutMapping("/id/{id}")
    public String updateCarro(@RequestBody Carro carro, @PathVariable("id") Long id){
        Carro updated =  service.updateCarro(carro, id);
        return "Carro "+updated.getNome()+" Updated!";
    }
    @DeleteMapping("/id/{id}")
    public String deleteCarro(@PathVariable("id") Long id){
        Carro deleted = service.deleteById(id);
        return "Carro "+deleted.getNome()+ " Deletado!";
    }
}
