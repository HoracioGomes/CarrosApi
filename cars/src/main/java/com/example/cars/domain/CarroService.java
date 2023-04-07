package com.example.cars.domain;

import com.example.cars.api.exception.MyObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CarroDto getById(Long id) {
        return rep.findById(id).map(CarroDto::create).orElseThrow(() -> new MyObjectNotFoundException("O objeto não existe!"));
    }

    public List<CarroDto> getByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDto::create).collect(Collectors.toList());
    }

    public CarroDto saveCarro(Carro carro) {
        return CarroDto.create(rep.save(carro));
    }

    public void deleteById(Long id) {
        rep.deleteById(id);
    }

    public CarroDto updateCarro(Carro carro, Long id) {

        Optional<Carro> carroOpt = rep.findById(id);
            Carro carroToUpdate = carroOpt.get();
            carroToUpdate.setNome(carro.getNome());
            carroToUpdate.setTipo(carro.getTipo());
            return CarroDto.create(rep.save(carroToUpdate));


    }
}
