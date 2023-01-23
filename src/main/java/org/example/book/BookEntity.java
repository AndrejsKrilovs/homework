package org.example.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

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
public class BookEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Book name cannot be empty.")
    @Column(nullable = false, unique = true)
    private String name;

    private BigDecimal price;

    private LocalDateTime addedAt;

    @PrePersist
    public void beforeInsert() {
        addedAt = LocalDateTime.now();
    }

    @PostPersist
    public void afterInsert() {
        log.info("Entity {} added.", this);
    }

    @Deprecated
    public BookEntity() {
    }

    @Deprecated
    public BookEntity(Long id, String name, BigDecimal price, LocalDateTime addedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.addedAt = addedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookEntity that = (BookEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
