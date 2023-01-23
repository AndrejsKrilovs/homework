package org.example.book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "book_table")
@EqualsAndHashCode(of = "name")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Book name cannot be empty.")
    @Column(nullable = false, unique = true)
    private String name;
    private String author;
    private BigDecimal price;

    private LocalDateTime addedAt;

    @PrePersist
    public void beforeInsert() {
        if(author.isBlank()) {
            author = null;
        }

        addedAt = LocalDateTime.now();
    }

    @PostPersist
    public void afterInsert() {
        log.info("Entity {} added.", this);
    }
}
