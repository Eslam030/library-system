package com.example.library_system.controller;

import com.example.library_system.model.Book;
import com.example.library_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService book_service ;
    private HashMap<String , Object> response ;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks () {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(book_service.getAllBooks()) ;
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String , Object>> getBook (@PathVariable("id") Long id ) {
        response = new HashMap<>() ;
        HttpStatus status = null ;
        try {
            Book book = book_service.getBook(id) ;
            response.put("book" , book) ;
            status = HttpStatus.OK ;
        }catch (Exception ex) {
            response.put("error" , ex.toString()) ;
            status = HttpStatus.NOT_FOUND ;
        }
        return ResponseEntity.status(status).body(response) ;
    }
    @PostMapping
    public ResponseEntity<Map<String , Object>> addBook (@RequestBody Book book) {
        response = new HashMap<>() ;
        HttpStatus status = null ;
        try {
            Book to_save_book = book_service.save(book) ;
            response.put("message" , "Done") ;
            response.put("book" , to_save_book) ;
            status = HttpStatus.CREATED ;
        }catch (Exception ex) {
            response.put("error" , ex.toString()) ;
            status = HttpStatus.BAD_REQUEST ;
        }
        return ResponseEntity.status(status).body(response) ;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String , Object>> deleteBook (@PathVariable("id") Long id) {
        response = new HashMap<>() ;
        HttpStatus status = null ;
        try {
            book_service.delete(id) ;
            response.put("message" , "Deleted Successfully") ;
            status = HttpStatus.OK;
        }catch (Exception ex) {
            response.put("error" , ex.toString()) ;
            status = HttpStatus.NOT_FOUND ;
        }
        return ResponseEntity.status(status).body(response) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String , Object>> updateBook (@PathVariable("id") Long id , @RequestBody Book updatedBook) {
        response = new HashMap<>() ;
        HttpStatus status = null ;
        try {
            book_service.update(id , updatedBook) ;
            response.put("message" , "Updated Successfully") ;
            status = HttpStatus.OK ;
        }catch (Exception ex) {
            status = HttpStatus.NOT_FOUND ;
            response.put("erorr" , ex.toString()) ;
        }

        return ResponseEntity.status(status).body(response) ;
    }
}
