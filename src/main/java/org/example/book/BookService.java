package org.example.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Set<BookEntity> bookCollection() {
        return repository.findBooks();
    }

    public BookEntity addNewBook(@Valid BookEntity book) {
        return repository.save(book);
    }
}
