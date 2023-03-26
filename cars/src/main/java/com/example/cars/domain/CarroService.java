package com.example.cars.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    @Autowired
    private CarroRepository rep;

    public Iterable<Carro> getCarros() {
        return rep.findAll();
    }

    public Optional<Carro> getById(Long id) {
        return rep.findById(id);
    }

    public Iterable<Carro> getByTipo(String tipo) {
        return rep.findByTipo(tipo);
    }

    public Carro saveCarro(Carro carro) {
        return rep.save(carro);
    }

    public Carro deleteById(Long id) {
        Optional<Carro> carroOpt = rep.findById(id);
        if (carroOpt.isPresent()) {
            rep.deleteById(id);
            return carroOpt.get();
        } else {
            throw new RuntimeException("O objeto nao existe!");
        }
    }

    public Carro updateCarro(Carro carro, Long id) {

        Optional<Carro> carroOpt = rep.findById(id);
        if (carroOpt.isPresent()) {
            Carro carroToUpdate = carroOpt.get();
            carroToUpdate.setNome(carro.getNome());
            carroToUpdate.setTipo(carro.getTipo());
            return rep.save(carroToUpdate);
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro!");
        }

    }

    public List<Carro> getCarrosfake() {
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro(1L, "Fusca", "classico"));
        carros.add(new Carro(2L, "Chevet", "classico"));
        carros.add(new Carro(3L, "Gol", "classico"));
        return carros;
    }
}
