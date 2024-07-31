package com.example.library_system.controller;

import com.example.library_system.model.Book;
import com.example.library_system.model.Patron;
import com.example.library_system.service.BookService;
import com.example.library_system.service.BorrowingService;
import com.example.library_system.service.PatronService;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BorrowingController {
    @Autowired
    BorrowingService borrowingService ;
    private HashMap<String , Object> response ;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<Map<String , Object>> borrow (@PathVariable("bookId") Long bookId , @PathVariable("patronId") Long patronId) {
        response = new HashMap<>() ;
        HttpStatus status = null ;
        try {
            Pair<Book , Patron> bookPatronPair = borrowingService.borrow(bookId , patronId);
            response.put("message" , String.format("Patron %s Borrowed Book %s Successfully"
                    , bookPatronPair.getValue1().getName()
                    , bookPatronPair.getValue0().getTitle())) ;
            status = HttpStatus.OK ;
        }catch (Exception ex) {
            response.put("error" , ex.toString()) ;
            status = HttpStatus.NOT_FOUND ;
        }
        return ResponseEntity.status(status).body(response) ;
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<Map<String , Object>> returnBook (@PathVariable("bookId") Long bookId , @PathVariable("patronId") Long patronId) {
        response = new HashMap<>() ;
        HttpStatus status = null ;
        try {
            Pair<Book , Patron> bookPatronPair = borrowingService.returnBook(bookId , patronId);
            response.put("message" , String.format("Book %s returned Successfully"  , bookPatronPair.getValue0().getTitle())) ;
            status = HttpStatus.OK ;
        }catch (Exception ex) {
            response.put("error" , ex.toString()) ;
            status = HttpStatus.NOT_FOUND ;
        }
        return ResponseEntity.status(status).body(response) ;
    }
}
