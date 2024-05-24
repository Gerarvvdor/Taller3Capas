package com.nalas.pnccontrollers.services.implementations;

import com.nalas.pnccontrollers.domain.dtos.SaveBookDTO;
import com.nalas.pnccontrollers.domain.entities.Book;
import com.nalas.pnccontrollers.domain.entities.Category;
import com.nalas.pnccontrollers.repositories.BookRepository;
import com.nalas.pnccontrollers.services.BookService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void save(SaveBookDTO info, Category category) {
        Book book =  this.findByIsbn(info.getISBN());

        if (book == null) {
            book = new Book();

        }

        book.setISBN(info.getISBN());
        book.setTitle(info.getTitle());
        book.setCategory(category);

        this.bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByISBN(isbn).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteByIsbn(String isbn) {
        bookRepository.deleteByISBN(isbn);
    }

    @Override
    public List<Book> findByCategory(Category category) {
        return bookRepository.findAllByCategory(category);
    }
}
