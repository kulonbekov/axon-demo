package ru.tproger.axondemo.domain.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookViewRepository extends JpaRepository<BookView, String> {

    List<BookView> findByTitleContaining(String title);
}
