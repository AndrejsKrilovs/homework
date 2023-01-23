package org.example.book;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Set<BookEntity> findByNameLike(String name, Sort sort);
}
