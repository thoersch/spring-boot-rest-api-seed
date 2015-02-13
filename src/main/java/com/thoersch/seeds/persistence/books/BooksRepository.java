package com.thoersch.seeds.persistence.books;

import com.thoersch.seeds.representations.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Long> {
}
