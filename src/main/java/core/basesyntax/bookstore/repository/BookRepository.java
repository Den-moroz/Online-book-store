package core.basesyntax.bookstore.repository;

import core.basesyntax.bookstore.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    Book getBookById(Long id);

    List<Book> findAll();
}