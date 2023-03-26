package com.example.cars.api;

import com.example.cars.domain.Carro;
import com.example.cars.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    CarroService service;

    @GetMapping
    ResponseEntity<Iterable<Carro>> getCarros() {
        return ResponseEntity.ok(service.getCarros());
//        return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getById(@PathVariable("id") Long id) {

        Optional<Carro> carro = service.getById(id);

        return carro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        // if ternário
//      return   service.getById(id).isPresent() ?
//                ResponseEntity.ok(service.getById(id).get()) :
//                ResponseEntity.notFound().build();
        // if comum
//        if (service.getById(id).isPresent()) {
//            return ResponseEntity.ok(service.getById(id).get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getByTipo(@PathVariable("tipo") String tipo) {
        return service.getByTipo(tipo).isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(service.getByTipo(tipo));
    }

    @PostMapping
    public String saveCarro(@RequestBody Carro carro) {
        Carro carroSalvo = service.saveCarro(carro);
        return "Carro " + carroSalvo.getNome() + " Salvo!";
    }

    @PutMapping("/id/{id}")
    public String updateCarro(@RequestBody Carro carro, @PathVariable("id") Long id) {
        Carro updated = service.updateCarro(carro, id);
        return "Carro " + updated.getNome() + " Updated!";
    }

    @DeleteMapping("/id/{id}")
    public String deleteCarro(@PathVariable("id") Long id) {
        Carro deleted = service.deleteById(id);
        return "Carro " + deleted.getNome() + " Deletado!";
    }
}
