package com.example.library_system.controller;


import com.example.library_system.model.Book;
import com.example.library_system.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetBookById() throws Exception {
        Long bookId = 1L;
        Book book = new Book();
        book.setBookId(bookId);
        when(bookService.getBook(bookId)).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("book.bookId").value(bookId));

        when(bookService.getBook(bookId)).thenThrow() ;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()) ;
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book( );
        book.setBookId(1L);
        when(bookService.save(book)).thenThrow() ;
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());

        when(bookService.save(book)).thenReturn(book);
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateBook() throws Exception {
        Long bookId = 1L;
        Book book = new Book();
        book.setBookId(bookId);



        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Updated Successfully"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Long bookId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Deleted Successfully"));
    }
}