package com.nalas.pnccontrollers.services;

import com.nalas.pnccontrollers.domain.dtos.SaveBookDTO;
import com.nalas.pnccontrollers.domain.entities.Book;
import com.nalas.pnccontrollers.domain.entities.Category;

import java.util.List;

public interface BookService {
    void save(SaveBookDTO info, Category category);
    List<Book> findAll();
    Book findByIsbn(String isbn);
    void deleteByIsbn(String isbn);
    List<Book> findByCategory(Category category);
}
