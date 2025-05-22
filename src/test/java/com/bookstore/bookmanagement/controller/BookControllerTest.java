package com.bookstore.bookmanagement.controller;

import com.bookstore.bookmanagement.dto.BookRequest;
import com.bookstore.bookmanagement.dto.BookResponse;
import com.bookstore.bookmanagement.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateBookSuccessfully() throws Exception {
        BookRequest request = new BookRequest("Effective Java", "Joshua Bloch");
        BookResponse response = new BookResponse(1L, "Effective Java", "Joshua Bloch", "9780134685991");

        Mockito.when(service.createBook(any(BookRequest.class))).thenReturn(response);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.author").value("Joshua Bloch"));
    }

    @Test
    void shouldReturnBookById() throws Exception {
        BookResponse response = new BookResponse(1L, "Clean Code", "Robert C. Martin", "9780132350884");

        Mockito.when(service.getBook(1L)).thenReturn(response);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.author").value("Robert C. Martin"));
    }

    @Test
    void shouldUpdateBookSuccessfully() throws Exception {
        BookRequest request = new BookRequest("Updated Title", "Updated Author");
        BookResponse response = new BookResponse(1L, "Updated Title", "Updated Author", "9780132350884");

        Mockito.when(service.updateBook(Mockito.eq(1L), any(BookRequest.class))).thenReturn(response);

        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }

    @Test
    void shouldDeleteBookSuccessfully() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
    }
}
