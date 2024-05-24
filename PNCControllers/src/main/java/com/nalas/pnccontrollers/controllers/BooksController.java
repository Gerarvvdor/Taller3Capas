package com.nalas.pnccontrollers.controllers;

import com.nalas.pnccontrollers.domain.dtos.GeneralResponse;
import com.nalas.pnccontrollers.domain.dtos.SaveBookDTO;
import com.nalas.pnccontrollers.domain.entities.Book;
import com.nalas.pnccontrollers.domain.entities.Category;
import com.nalas.pnccontrollers.services.BookService;
import com.nalas.pnccontrollers.services.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/books")
public class BooksController {
    private final BookService bookService;

    private final CategoryService categoryService;

    public BooksController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }


    // Get all books endpoint
    @GetMapping("/")
    public ResponseEntity<GeneralResponse> findAll() {

        return GeneralResponse.builder()
                .message("List of books!")
                .data(bookService.findAll())
                .build();
    }

    @GetMapping("/{isbn}")
    // Get book by isbn endpoint
    public ResponseEntity<GeneralResponse> findById(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);

        if (book == null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return GeneralResponse.builder()
                .data(book)
                .build();
    }

    // Save book endpoint [UPSERT]
    @PostMapping("/save")
    public ResponseEntity<GeneralResponse> saveBook(@RequestBody @Valid SaveBookDTO info) {

        Category category = categoryService.findCategoryById(info.getCategory());

        if (category == null) {
            return GeneralResponse.builder()
                    .message("Category not found!")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        bookService.save(info, category);

        return GeneralResponse.builder()
                .status(HttpStatus.OK)
                .message("Book saved!")
                .build();

    }

    // Delete book by isbn endpoint
    @DeleteMapping("/{isbn}")
    public ResponseEntity<GeneralResponse> deleteByIsbn(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);

        if (book == null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        bookService.deleteByIsbn(isbn);

        return GeneralResponse.builder()
                .status(HttpStatus.OK)
                .build();
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<GeneralResponse> findCategoryById(@PathVariable String id) {
        Category category = categoryService.findCategoryById(id);

        if (category == null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        
        return GeneralResponse.builder()
                .data(bookService.findByCategory(category))
                .build();
    }
}
