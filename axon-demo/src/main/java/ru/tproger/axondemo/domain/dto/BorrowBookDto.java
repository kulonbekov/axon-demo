package ru.tproger.axondemo.domain.dto;

import lombok.Data;

@Data
public class BorrowBookDto {
    private final String bookId;
    private final String fullName;
}
