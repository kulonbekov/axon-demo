package ru.tproger.axondemo.domain.commands;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

//команда регистрации книги
@Data
public class RegisterBookCommand {
    @TargetAggregateIdentifier
    private final String bookId;
    private final String title;
    private final String description;
    private final Integer amount;
}
