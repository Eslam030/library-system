package com.example.library_system.repository;

import com.example.library_system.model.Borrowing;
import com.example.library_system.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    @Query("SELECT b FROM Borrowing b where (b.book.BookId = :bookId) AND (b.patron.PatronId = :patronId) AND (b.returnDate IS NULL)")
    public List<Borrowing> getBorrowingRecordByBookAndPatron (@Param("bookId") Long bookId ,
                                                              @Param("patronId") Long patronId) ;
}
