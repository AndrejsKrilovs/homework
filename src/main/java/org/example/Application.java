package org.example;

import lombok.RequiredArgsConstructor;
import org.example.book.BookEntity;
import org.example.book.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    private final BookRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        if(repository.count() == 0) {
            IntStream.range(0, 125).mapToObj(index -> BookEntity.builder().name("Some Book " + index).build())
                    .forEach(repository::save);
        }
    }
}
