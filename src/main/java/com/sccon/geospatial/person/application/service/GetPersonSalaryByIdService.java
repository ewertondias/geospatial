package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.infrastructure.util.CurrencyUtils;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonSalaryResponse;
import com.sccon.geospatial.person.application.GetPersonSalaryByIdUseCase;
import com.sccon.geospatial.person.domain.exception.PersonNotFoundException;
import com.sccon.geospatial.person.domain.model.Person;
import com.sccon.geospatial.person.domain.model.PersonId;
import com.sccon.geospatial.person.domain.model.PersonSalaryOutputEnum;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

@Service
public class GetPersonSalaryByIdService implements GetPersonSalaryByIdUseCase {

    private final Map<PersonSalaryOutputEnum, Function<Person, String>> outputStrategies = Map.of(
        PersonSalaryOutputEnum.MIN, this::calculateMinSalary,
        PersonSalaryOutputEnum.FULL, this::calculateFullSalary
    );

    private final BigDecimal minSalary;
    private final BigDecimal initialSalary;
    private final BigDecimal yearlyIncreasePercentage;
    private final BigDecimal yearlyIncreaseValue;

    private final PersonRepository repository;

    public GetPersonSalaryByIdService(@Value("${salary.min}") BigDecimal minSalary,
                                      @Value("${salary.initial}") BigDecimal initialSalary,
                                      @Value("${salary.yearly-increase.percentage}") BigDecimal yearlyIncreasePercentage,
                                      @Value("${salary.yearly-increase.value}") BigDecimal yearlyIncreaseValue,
                                      PersonRepository repository) {
        this.repository = repository;
        this.minSalary = minSalary;
        this.initialSalary = initialSalary;
        this.yearlyIncreasePercentage = yearlyIncreasePercentage;
        this.yearlyIncreaseValue = yearlyIncreaseValue;
    }

    @Override
    public PersonSalaryResponse execute(Long id, String output) {
        var personId = PersonId.of(id);
        var person = repository.findById(personId)
            .orElseThrow(() -> new PersonNotFoundException(personId.value().toString()));

        var salaryOutputEnum = PersonSalaryOutputEnum.fromString(output);
        var strategy = outputStrategies.get(salaryOutputEnum);

        var value = strategy.apply(person);

        return new PersonSalaryResponse(value);
    }

    private String calculateMinSalary(Person person) {
        var fullSalary = fullSalary(person);

        var qtdMinSalary = fullSalary.divide(minSalary, 2, RoundingMode.CEILING);

        return String.format(Locale.of("pt", "BR"), "%.2f", qtdMinSalary);
    }

    private String calculateFullSalary(Person person) {
        var fullSalary = fullSalary(person);

        return CurrencyUtils.toBRL(fullSalary);
    }

    private BigDecimal fullSalary(Person person) {
        var actualDate = LocalDate.now();
        var yearsInCompany = ChronoUnit.YEARS.between(person.hireDate().value(), actualDate);

        var currentSalary = initialSalary;
        for (int i = 0; i < yearsInCompany; i++) {
            currentSalary = currentSalary.multiply(yearlyIncreasePercentage).add(yearlyIncreaseValue);
        }

        return currentSalary.setScale(2, RoundingMode.CEILING);
    }

}

