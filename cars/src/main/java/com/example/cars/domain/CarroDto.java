package com.example.cars.domain;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarroDto {
    private Long id;
    private String nome;
    private String tipo;

    public static CarroDto create(Carro carro) {
        ModelMapper mm = new ModelMapper();
       return mm.map(carro, CarroDto.class);
    }
}
