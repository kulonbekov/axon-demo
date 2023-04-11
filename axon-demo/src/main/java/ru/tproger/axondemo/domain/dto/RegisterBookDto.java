package ru.tproger.axondemo.domain.dto;

import lombok.Data;

@Data
public class RegisterBookDto {

    private final String id;
    private final String title;
    private final String description;
    private final Integer amount;
}
