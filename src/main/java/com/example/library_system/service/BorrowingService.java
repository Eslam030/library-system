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
    public Pair<Book , Patron> borrow (Long bookId , Long patronId) throws Exception {
        // First Check if the book is available
        Book book = bookRepository.getReferenceById(bookId) ;
        Patron patron = patronRepository.getReferenceById(patronId) ;
        if (book.getAvailableForBorrowing()) {
            Borrowing borrowing = new BorrowingBuilder()
                    .book(book)
                    .patron(patron)
                    .borrowDate(new Date())
                    .build();

            book.setAvailableForBorrowing(false);

            bookRepository.save(book) ;
            borrowingRepository.save(borrowing) ;
            return Pair.with(book , patron) ;
        }else {
            throw new NotAvailableBook() ;
        }
    }

    @Transactional
    public Pair<Book , Patron> returnBook (Long bookId , Long patronId) throws  Exception {
        List<Borrowing> borrowings = borrowingRepository.getBorrowingRecordByBookAndPatron(bookId , patronId) ;
        Book book = bookRepository.getReferenceById(bookId) ;
        Patron patron = patronRepository.getReferenceById(patronId) ;
        if (borrowings.isEmpty()) {
            throw new NoSuchBorrowingRecord() ;
        }else {
            Borrowing borrowing = borrowings.get(0) ;
            borrowing.setReturnDate(new Date());
            book.setAvailableForBorrowing(true);
            borrowingRepository.save(borrowing) ;
            bookRepository.save(book) ;
            return Pair.with(book , patron) ;
        }
    }
}