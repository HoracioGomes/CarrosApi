package com.example.cars.domain;

import lombok.Data;

@Data
public class CarroDto {
    private Long id;
    private String nome;
    private String tipo;

    public CarroDto(Carro carro) {
        this.id = carro.getId();
        this.nome = carro.getNome();
        this.tipo = carro.getTipo();
    }
}
