package com.example.cars.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String hello() {
        return "Hello Word!";
    }

    @GetMapping("/login/{l}/senha/{s}")
    public String login(@PathVariable("l") String login, @PathVariable("s") String senha){
        return "Login: "+login+" Senha: "+senha;
    }

    @PostMapping("/login/post")
    public String loginPost(@RequestParam("l") String login, @RequestParam("s") String senha){
        return "Login: "+login+" Senha: "+senha;
    }
}
