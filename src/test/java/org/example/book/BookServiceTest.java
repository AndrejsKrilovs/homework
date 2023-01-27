package org.example.book;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceTest {
    @Autowired
    private BookService service;

    @MockBean
    private BookRepository repository;

    @Test
    public void bookCollection() {
        BookEntity book1 = BookEntity.builder().name("Book 1").build();
        BookEntity book2 = BookEntity.builder().name("Book 2").build();
        Mockito.doReturn(Set.of(book1, book2)).when(repository).findBooks();

        Set<BookEntity> result = service.bookCollection();
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void addNewBookSuccess() {
        BookEntity bookToAdd = BookEntity.builder().name("Book to add").build();
        service.addNewBook(bookToAdd);
        Mockito.verify(repository, Mockito.times(1)).save(bookToAdd);
    }

    @Test
    public void addNewBookWithBlankName() {
        BookEntity bookToAdd = service.addNewBook(BookEntity.builder().name(null).build());
        Mockito.doThrow(new NullPointerException()).when(repository).save(bookToAdd);
    }
}