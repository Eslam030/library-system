package com.example.library_system.builder;

import com.example.library_system.model.Book;
import com.example.library_system.model.Borrowing;
import com.example.library_system.model.Patron;

import java.util.Date;

public class BorrowingBuilder {
    private Book book ;
    private Patron patron ;
    private Date borrowDate ;
    private Date returnDate ;

    public BorrowingBuilder () {

    }
    public Borrowing build () {
        return new Borrowing(book , patron , borrowDate , returnDate) ;
    }

    public BorrowingBuilder book (Book book) {
        this.book = book ;
        return this ;
    }

    public BorrowingBuilder patron (Patron patron) {
        this.patron = patron ;
        return this ;
    }

    public BorrowingBuilder returnDate (Date returnDate) {
        this.returnDate = returnDate ;
        return this ;
    }

    public BorrowingBuilder borrowDate (Date borrowDate) {
        this.borrowDate = borrowDate ;
        return this ;
    }
}
