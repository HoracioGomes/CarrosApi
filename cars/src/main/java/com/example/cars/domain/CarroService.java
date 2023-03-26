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
        return rep.findAll().stream().map(CarroDto::create).collect(Collectors.toList());
    }

    public Optional<CarroDto> getById(Long id) {
        return rep.findById(id).map(CarroDto::create);
    }

    public List<CarroDto> getByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDto::create).collect(Collectors.toList());
    }

    public CarroDto saveCarro(Carro carro) {
        return CarroDto.create(rep.save(carro));
    }

    public CarroDto deleteById(Long id) {
        Optional<Carro> carroOpt = rep.findById(id);
        if (carroOpt.isPresent()) {
            rep.deleteById(id);
            return CarroDto.create(carroOpt.get());
        } else {
            throw new RuntimeException("O objeto nao existe!");
        }
    }

    public CarroDto updateCarro(Carro carro, Long id) {

        Optional<Carro> carroOpt = rep.findById(id);
        if (carroOpt.isPresent()) {
            Carro carroToUpdate = carroOpt.get();
            carroToUpdate.setNome(carro.getNome());
            carroToUpdate.setTipo(carro.getTipo());
            return CarroDto.create(rep.save(carroToUpdate));
        } else {
            throw new RuntimeException("O objeto nao existe!");
        }

    }
}
