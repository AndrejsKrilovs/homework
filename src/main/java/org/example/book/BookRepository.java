package org.example.book;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Set<BookEntity> findByNameLike(String name, Sort sort);

    @Query("select b.name, b.price from BookEntity b")
    Set<BookEntity> findBooks();
}
