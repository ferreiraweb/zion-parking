package com.zion.parking.controllers;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class usuarioControllerTest {


    @Test
    public void test() {

        LocalDate dataNascimento = LocalDate.of(1971, 9, 5);
        LocalDate current = LocalDate.now();

        Period periodo = Period.between(dataNascimento, current);

        int idade = periodo.getYears();

        System.out.println("idade: " + idade);



    }

}
