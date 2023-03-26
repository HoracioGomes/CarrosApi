package com.example.cars.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {
    @Autowired
    private CarroRepository rep;

    public List<CarroDto> getCarros() {
        return rep.findAll().stream().map(CarroDto::new).collect(Collectors.toList());
    }

    public Optional<CarroDto> getById(Long id) {
        return rep.findById(id).map(CarroDto::new);
    }

    public List<CarroDto> getByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDto::new).collect(Collectors.toList());
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
}
