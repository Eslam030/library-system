package com.example.library_system.service;

import org.javatuples.Pair;
import com.example.library_system.builder.BorrowingBuilder;
import com.example.library_system.exception.NoSuchBorrowingRecord;
import com.example.library_system.model.Book;
import com.example.library_system.model.Borrowing;
import com.example.library_system.model.Patron;
import com.example.library_system.repository.BookRepository;
import com.example.library_system.repository.BorrowingRepository;
import com.example.library_system.repository.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import com.example.library_system.exception.NotAvailableBook ;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

@Service
public class BorrowingService {
    @Autowired
    BorrowingRepository borrowingRepository ;
    @Autowired
    BookRepository bookRepository ;
    @Autowired
    PatronRepository patronRepository ;
    @Transactional
    @CachePut(value = "borrowings" , key = "#bookId + '-' + #patronId")
    public Pair<Book , Patron> borrow (Long bookId , Long patronId) throws Exception {
        // Get entity of Book and patron that correspond to book id
        Book book = bookRepository.getReferenceById(bookId) ;
        // Get entity of Patron and patron that correspond to patron id
        Patron patron = patronRepository.getReferenceById(patronId) ;

        // Check First if the book is available
        if (book.getAvailableForBorrowing()) {
            // Make the Borrowing Record
            Borrowing borrowing = new BorrowingBuilder()
                    .book(book)
                    .patron(patron)
                    .borrowDate(new Date())
                    .build();
            // Mark the book as unavailable for borrowing
            book.setAvailableForBorrowing(false);

            bookRepository.save(book) ;
            borrowingRepository.save(borrowing) ;
            return Pair.with(book , patron) ;
        }else {
            throw new NotAvailableBook() ;
        }
    }

    @Transactional
    @CachePut(value = "borrowings", key = "#bookId + '-' + #patronId")
    public Pair<Book , Patron> returnBook (Long bookId , Long patronId) throws  Exception {
        // get the borrowing record with the bookId and patronId
        List<Borrowing> borrowings = borrowingRepository.getBorrowingRecordByBookAndPatron(bookId , patronId) ;
        // Get entity of Book and patron that correspond to book id
        Book book = bookRepository.getReferenceById(bookId) ;
        // Get entity of Patron and patron that correspond to patron id
        Patron patron = patronRepository.getReferenceById(patronId) ;
        if (borrowings.isEmpty()) {
            // the is no borrowing record
            throw new NoSuchBorrowingRecord() ;
        }else {
            // update the return date
            Borrowing borrowing = borrowings.get(0) ;
            borrowing.setReturnDate(new Date());
            // Mark the book as available for borrowing
            book.setAvailableForBorrowing(true);
            borrowingRepository.save(borrowing) ;
            bookRepository.save(book) ;
            return Pair.with(book , patron) ;
        }
    }
}