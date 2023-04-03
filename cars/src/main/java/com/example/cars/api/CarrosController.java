package com.example.cars.api;

import com.example.cars.domain.Carro;
import com.example.cars.domain.CarroDto;
import com.example.cars.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    CarroService service;

    @GetMapping
    ResponseEntity getCarros() {
        return service.getCarros().isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(service.getCarros());
//        return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDto> getById(@PathVariable("id") Long id) {
        CarroDto carro = service.getById(id);
        return ResponseEntity.ok(carro);

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
    public ResponseEntity saveCarro(@RequestBody Carro carro) {
        CarroDto carroSalvo = service.saveCarro(carro);
        URI location = getUri(carroSalvo.getId());
        return ResponseEntity.created(location).build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity updateCarro(@RequestBody Carro carro, @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.updateCarro(carro, id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteCarro(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
