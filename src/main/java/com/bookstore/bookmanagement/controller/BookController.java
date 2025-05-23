package com.bookstore.bookmanagement.controller;

import com.bookstore.bookmanagement.dto.BookRequest;
import com.bookstore.bookmanagement.dto.BookResponse;
import com.bookstore.bookmanagement.entity.Book;
import com.bookstore.bookmanagement.service.BookService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest request) {
        return new ResponseEntity<>(service.createBook(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> read(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBook(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable Long id, @RequestBody @Valid BookRequest request) {
        return ResponseEntity.ok(service.updateBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping
//    public ResponseEntity<Page<Book>> listAll(Pageable pageable) {
//        return ResponseEntity.ok(service.getAllBooks(pageable));
//    }

    @GetMapping
    public ResponseEntity<Page<Book>> listAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.getAllBooks(pageable));
    }

//    @GetMapping("/search")
//    public ResponseEntity<Page<Book>> search(@RequestParam String q, Pageable pageable) {
//        return ResponseEntity.ok(service.searchBooks(q, pageable));
//    }

    @GetMapping("/search")
    public ResponseEntity<Page<Book>> search(
            @RequestParam String q,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.searchBooks(q, pageable));
    }

}
