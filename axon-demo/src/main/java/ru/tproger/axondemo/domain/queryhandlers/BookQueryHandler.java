package ru.tproger.axondemo.domain.queryhandlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import ru.tproger.axondemo.domain.projections.BookView;
import ru.tproger.axondemo.domain.projections.BookViewRepository;
import ru.tproger.axondemo.domain.queries.BookQuery;
import ru.tproger.axondemo.domain.queries.ListBookQuery;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Component
public class BookQueryHandler {

    private final BookViewRepository bookViewRepository;
    @QueryHandler
    public List<BookView> handle(ListBookQuery query){
        log.info("Handling ListBookQuery: {}", query);

        return bookViewRepository.findByTitleContaining(query.getTitle());
    }
    @QueryHandler
    public BookView handle(BookQuery query) {
        log.info("Handling BookQuery: {}", query);

        Optional<BookView> book = bookViewRepository.findById(query.getBookId());
        if (book.isEmpty())
            throw new IllegalArgumentException("Book not found");

        return book.get();
    }
}
