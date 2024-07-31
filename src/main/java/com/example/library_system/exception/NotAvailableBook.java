package com.example.library_system.exception;

public class NotAvailableBook extends Exception {
    @Override
    public String toString() {
        return "This Book is not available for borrowing" ;
    }
}