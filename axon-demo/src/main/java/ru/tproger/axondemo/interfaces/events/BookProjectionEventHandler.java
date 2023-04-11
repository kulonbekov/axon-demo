package ru.tproger.axondemo.interfaces.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import ru.tproger.axondemo.domain.events.BookBorrowedEvent;
import ru.tproger.axondemo.domain.events.BookRegisteredEvent;
import ru.tproger.axondemo.domain.events.BookReturnedEvent;
import ru.tproger.axondemo.domain.projections.BookView;
import ru.tproger.axondemo.domain.projections.BookViewRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookProjectionEventHandler {

    private final BookViewRepository bookViewRepository;

    @EventHandler
    public void bookRegisteredEventHandler(BookRegisteredEvent event){
        log.info("Applying BookRegisteredEvent: {}", event);

        BookView bookView = new BookView(event.getBookId(), event.getTitle(), event.getDescription(), event.getAmount());
        bookViewRepository.save(bookView);
    }

    @EventHandler
    public void bookBorrowedEventHandler(BookBorrowedEvent event) {
        log.info("Applying BookBorrowedEvent: {}", event);

        BookView bookView = getBookView(event.getBookId());
        //bookView.setAmount(bookView.getAmount() - 1);
    }

    @EventHandler
    public void bookReturnedEventHandler(BookReturnedEvent event) {
        log.info("Applying BookReturnedEvent: {}", event);

        BookView bookView = getBookView(event.getBookId());
        //bookView.setAmount(bookView.getAmount() + 1);
    }



    private BookView getBookView(String bookId){
        return bookViewRepository.findById(bookId)
                .orElseThrow(()->new IllegalArgumentException("Book not found"));
    }

}
