package com.sccon.geospatial.infrastructure.config;

import com.sccon.geospatial.person.adapter.in.api.dto.CreatePersonRequest;
import com.sccon.geospatial.person.application.CreatePersonUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoaderConfig {

    private static final Logger log = LoggerFactory.getLogger(DataLoaderConfig.class);

    @Bean
    public CommandLineRunner loadData(CreatePersonUseCase createPerson) {
        return args -> {
            var person1 = new CreatePersonRequest(
                1L,
                "Bob Johnson",
                LocalDate.of(1989, 5, 15),
                LocalDate.of(2021, 8, 22)
            );
            createPerson.execute(person1);

            var person2 = new CreatePersonRequest(
                2L,
                "Charlie Brown",
                LocalDate.of(1995, 11, 2),
                LocalDate.of(2023, 1, 8)
            );
            createPerson.execute(person2);

            var person3 = new CreatePersonRequest(
                3L,
                "Alice Smith",
                LocalDate.of(1990, 12, 5),
                LocalDate.of(2024, 3, 18)
            );
            createPerson.execute(person3);

            log.info("Data loading completed.");
        };
    }

}
