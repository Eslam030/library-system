package com.example.library_system.controller;

import com.example.library_system.model.Book;
import com.example.library_system.model.Patron;
import com.example.library_system.service.BorrowingService;
import com.example.library_system.service.PatronService;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(BorrowingController.class)
public class BorrowingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowingService borrowingService;

    @Test
    public void borrow() throws Exception {
        Long bookId = 1L ;
        Long patronId = 1L ;

        Book book = new Book() ;
        book.setBookId(bookId);
        book.setTitle("TitleTest");

        Patron patron = new Patron();
        patron.setPatronId(patronId);
        patron.setName("PatronTest");

        when(borrowingService.borrow(bookId , patronId)).thenReturn(Pair.with(book , patron));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/1/patron/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Patron PatronTest Borrowed Book TitleTest Successfully"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/1/patron/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void returnBook () throws Exception {
        Long bookId = 1L ;
        Long patronId = 1L ;

        Book book = new Book() ;
        book.setBookId(bookId);
        book.setTitle("TitleTest");

        Patron patron = new Patron();
        patron.setPatronId(patronId);

        when(borrowingService.returnBook(bookId , patronId)).thenReturn(Pair.with(book , patron));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/return/1/patron/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Book TitleTest returned Successfully"));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/return/1/patron/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
