package com.bookstore.bookmanagement.service;

import com.bookstore.bookmanagement.dto.BookRequest;
import com.bookstore.bookmanagement.dto.BookResponse;
import com.bookstore.bookmanagement.entity.Book;
import com.bookstore.bookmanagement.exception.ResourceNotFoundException;
import com.bookstore.bookmanagement.repository.BookRepository;
import com.bookstore.bookmanagement.util.IsbnGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public BookResponse createBook(BookRequest request) {
        Book book = new Book(request.getTitle(), request.getAuthor());
        book.setIsbn(IsbnGenerator.generateIsbn13());
        Book saved = repository.save(book);
        return new BookResponse(saved.getId(), saved.getTitle(), saved.getAuthor(), saved.getIsbn());
    }

    public Book getBookEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public BookResponse getBook(Long id) {
        Book book = getBookEntity(id);
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn());
    }

    public Page<Book> getAllBooks(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Book> searchBooks(String query, Pageable pageable) {
        return repository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query, pageable);
    }

    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = getBookEntity(id);
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        Book updated = repository.save(book);
        return new BookResponse(updated.getId(), updated.getTitle(), updated.getAuthor(), updated.getIsbn());
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
