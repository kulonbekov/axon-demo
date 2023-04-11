package ru.tproger.axondemo.domain.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

//команда возврата книги
@Data
public class ReturnBookCommand {
    @TargetAggregateIdentifier
    private final String bookId;
    private final String fullName;
}
