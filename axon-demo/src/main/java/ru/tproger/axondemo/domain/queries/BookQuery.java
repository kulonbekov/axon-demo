package ru.tproger.axondemo.domain.queries;

import lombok.Data;

//запрос на выборку книги по bookId
@Data
public class BookQuery {

    private final String bookId;
}
