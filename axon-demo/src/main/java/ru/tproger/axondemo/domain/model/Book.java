package ru.tproger.axondemo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import ru.tproger.axondemo.domain.commands.BorrowBookCommand;
import ru.tproger.axondemo.domain.commands.RegisterBookCommand;
import ru.tproger.axondemo.domain.commands.ReturnBookCommand;
import ru.tproger.axondemo.domain.events.BookBorrowedEvent;
import ru.tproger.axondemo.domain.events.BookRegisteredEvent;
import ru.tproger.axondemo.domain.events.BookReturnedEvent;

import java.util.HashSet;
import java.util.Set;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    @AggregateIdentifier
    private String bookId;
    private Integer amount;
    private Set<String> tenants;

    @CommandHandler
    public Book(RegisterBookCommand command){
        log.info("Handling RegisterBookCommand: {}", command); //Обработка команды RegisterBookCommand

        if(command.getAmount()<=0)
            throw new IllegalArgumentException("Amount must be positive"); //Количество должна быть положительной

        apply(new BookRegisteredEvent(command.getBookId(), command.getTitle(), command.getDescription(), command.getAmount()));
    }

    @CommandHandler
    public void handle(BorrowBookCommand command){
        log.info("Handling BorrowBookCommand: {}", command);

        if (amount < 0)
            throw new IllegalArgumentException("Book out of stock"); // Книга отсуствует в наличие
        if (tenants.contains(command.getFullName()))
            throw new IllegalArgumentException("Book already borrowed by this person"); //Книга уже была взята этим человеком взаймы

        apply(new BookBorrowedEvent(command.getBookId(), command.getFullName()));
    }
    @CommandHandler
    public void handle(ReturnBookCommand command){
        log.info("Handling ReturnBookCommand: {}", command);

        if (!tenants.contains(command.getFullName()))
            throw new IllegalArgumentException("Book must be returned by person who has borrowed it"); //Книгу должен вернуть человек, который ее занимал

        apply(new BookReturnedEvent(command.getBookId(), command.getFullName()));
    }

    @EventSourcingHandler
    public void on(BookRegisteredEvent event){
        log.info("Applying BookRegisteredEvent: {}", event);

        bookId = event.getBookId();
        amount = event.getAmount();
        tenants = new HashSet<>();

    }

    @EventSourcingHandler
    public void on(BookBorrowedEvent event) {
        log.info("Applying BookBorrowedEvent: {}", event);

        amount--;
        tenants.add(event.getFullName());
    }

    @EventSourcingHandler
    public void on(BookReturnedEvent event) {
        log.info("Applying BookReturnedEvent: {}", event);

        amount++;
        tenants.remove(event.getFullName());
    }


}
