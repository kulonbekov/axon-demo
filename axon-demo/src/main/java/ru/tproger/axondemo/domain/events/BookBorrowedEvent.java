package ru.tproger.axondemo.domain.events;

import lombok.Data;

//событие аренды книги
@Data
public class BookBorrowedEvent {
    private final String bookId;
    private final String fullName;
}
