package com.thoersch.seeds.representations.books;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoersch.seeds.persistence.converters.LocalDateConverter;
import com.thoersch.seeds.persistence.converters.LocalDateTimeConverter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@javax.persistence.Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 255)
    @NotNull
    private String title;

    @Length(max = 100)
    @NotNull
    private String author;

    @Length(max = 100)
    @NotNull
    private String publisher;

    @Length(max = 20)
    @NotNull
    private String isbn;

    @NotNull
    @Convert(converter = LocalDateConverter.class)
    private LocalDate publicationDate;

    @JsonIgnore
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updated;

    public Book() {
    }

    public Book(String title, String author, String publisher, String isbn, LocalDate publicationDate) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
    }

    @PreUpdate
    public void preUpdate() {
        setUpdated(LocalDateTime.now(ZoneOffset.UTC));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (publicationDate != null ? !publicationDate.equals(book.publicationDate) : book.publicationDate != null)
            return false;
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", isbn=" + isbn +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
