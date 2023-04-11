package ru.tproger.axondemo.domain.projections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class BookView {

    @Id
    private final String bookId;
    private final String title;
    private final String description;
    private final Integer amount;

}
