package com.controledeponto;

import com.controledeponto.application.enums.DayOfWeek;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;

@SpringBootApplication
public class ControleDePontoApplication {

    public static void main(String[] args) {SpringApplication.run(ControleDePontoApplication.class, args);}
}
