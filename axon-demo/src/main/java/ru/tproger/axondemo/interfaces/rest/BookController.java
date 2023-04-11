package ru.tproger.axondemo.interfaces.rest;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.tproger.axondemo.domain.commands.BorrowBookCommand;
import ru.tproger.axondemo.domain.commands.RegisterBookCommand;
import ru.tproger.axondemo.domain.commands.ReturnBookCommand;
import ru.tproger.axondemo.domain.dto.RegisterBookDto;
import ru.tproger.axondemo.domain.projections.BookView;
import ru.tproger.axondemo.domain.queries.BookQuery;
import ru.tproger.axondemo.domain.queries.ListBookQuery;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerBook(@RequestBody RegisterBookDto dto){
        commandGateway.sendAndWait(new RegisterBookCommand(dto.getId(), dto.getTitle(), dto.getDescription(), dto.getAmount())
        );
    }

    @PutMapping("/borrow")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrowBook(@RequestParam String bookId, @RequestParam String fullName){
        commandGateway.sendAndWait(new BorrowBookCommand(bookId, fullName)
        );
    }
    @PutMapping("/return")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnBook(@RequestParam String bookId,
                           @RequestParam String fullName) {
        commandGateway.sendAndWait(
                new ReturnBookCommand(bookId, fullName)
        );
    }

    @GetMapping("/{bookId}")
    public BookView getBook(@PathVariable String bookId) {
        return queryGateway.query(new BookQuery(bookId), BookView.class).join();
    }
    @GetMapping
    public List<BookView> getBookList(@RequestParam(required = false, defaultValue = "") String title) {
        return queryGateway
                .query(new ListBookQuery(title), ResponseTypes.multipleInstancesOf(BookView.class))
                .join();
    }


}
