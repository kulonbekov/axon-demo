package ru.tproger.axondemo.domain.events;

import lombok.Data;

//событие регистрации книги
@Data
public class BookRegisteredEvent {
    private final String bookId;
    private final String title;
    private final String description;
    private final Integer amount;
}
