package com.nalas.pnccontrollers.repositories;

import com.nalas.pnccontrollers.domain.entities.Book;
import com.nalas.pnccontrollers.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findByISBN(String isbn);

    void deleteByISBN(String isbn);

    List<Book> findAllByCategory(Category category);
}
