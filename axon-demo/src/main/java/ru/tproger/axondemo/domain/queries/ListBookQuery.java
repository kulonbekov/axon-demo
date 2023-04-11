package ru.tproger.axondemo.domain.queries;

import lombok.Data;

//запрос на выборку книг с фильтром по title
@Data
public class ListBookQuery {
    private final String title;
}
