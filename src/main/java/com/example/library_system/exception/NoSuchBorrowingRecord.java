package com.example.library_system.exception;

public class NoSuchBorrowingRecord extends Exception {
    @Override
    public String toString() {
        return "No Such Borrowing Record For the book and this patron" ;
    }
}
