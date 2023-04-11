package ru.tproger.axondemo.domain.commands;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

//команда аренды книги
@Data
public class BorrowBookCommand {
    @TargetAggregateIdentifier
    private final String bookId;
    private final String fullName;

}
