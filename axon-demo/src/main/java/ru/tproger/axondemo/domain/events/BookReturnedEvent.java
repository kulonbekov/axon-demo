package ru.tproger.axondemo.domain.events;

import lombok.Data;

//событие возврата книги
@Data
public class BookReturnedEvent {
    private final String bookId;
    private final String fullName;
}