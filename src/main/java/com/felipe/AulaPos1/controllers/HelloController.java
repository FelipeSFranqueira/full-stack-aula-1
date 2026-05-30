package com.felipe.AulaPos1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello()
    {
        return "HELLO World!";
    }
    @GetMapping("/world")
    @ResponseBody
    public String world()
    {
        return "Hello WORLD!";
    }

    @PostMapping("/world/{valor}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String retornaValor(@PathVariable String valor)
    {
        return valor;
    }

    @PostMapping("/hello/{valor1}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public int retornaSoma(@PathVariable int valor1, @RequestBody int valor2)
    {
        return valor1 + valor2;
    }

    //Atividade 1

    @GetMapping("/hello/{nome}")
    @ResponseBody
    public String retornaNome(@PathVariable String nome) {
        return "Olá, " + nome;
    }

    @GetMapping("/calc/soma/{valor1}/{valor2}")
    @ResponseBody
    public int retornasoma(@PathVariable int valor1, @PathVariable int valor2) {
        if (valor1 == 0 || valor2 == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os parâmetros 'a' e 'b' são obrigatórios.");
        }
        return valor1 + valor2;
    }

    @GetMapping("/temperatura/{valor}/{de}/{para}")
    @ResponseBody
    public double retornaTemperatura(@PathVariable double valor,@PathVariable String de,@PathVariable String para) {
//        if ((de!= "C" || de!= "F" || de!="K") || (para!= "C" || para!= "F" || para!="K")) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os parâmetros 'de' e 'para' so aceitam C, F ou K.");
//        }
        if (de.equals(para)) {
            return valor;
        }

        double kelvin;

        switch (de) {
            case "C":
                kelvin = valor + 273.15;
                break;
            case "F":
                kelvin = (valor - 32) * 5 / 9 + 273.15;
                break;
            case "K":
                kelvin = valor;
                break;
            default:
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Unidade de origem inválida. Use apenas C, F ou K."
                );
        }
        switch (para) {
            case "C":
                return kelvin - 273.15;
            case "F":
                return (kelvin - 273.15) * 9 / 5 + 32;
            case "K":
                return kelvin;
            default:
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Unidade de destino inválida. Use apenas C, F ou K."
                );
        }
    }
}
