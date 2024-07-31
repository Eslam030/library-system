package com.example.library_system.builder;

import com.example.library_system.model.Book;

import java.util.Date;

public class BookBuilder {
    private Long BookId;
    private Boolean availableForBorrowing = true ;
    private String title ;
    private String author ;
    private Date publication_year ;
    private Long isbn ;

    public BookBuilder () {}

    public Book build() {
        return new Book(BookId , availableForBorrowing , title , author , publication_year , isbn) ;
    }

    public BookBuilder author (String author) {
        this.author = author ;
        return this ;
    }

    public BookBuilder title (String title) {
        this.title = title ;
        return this ;
    }

    public BookBuilder publicationYear (Date publication_year) {
        this.publication_year = publication_year ;
        return this ;
    }

    public BookBuilder isbn (Long isbn) {
        this.isbn = isbn ;
        return this ;
    }

    public BookBuilder id (Long id) {
        this.BookId = id ;
        return this ;
    }

    public BookBuilder availableForBorrowing (Boolean available) {
        this.availableForBorrowing = available ;
        return this ;
    }
}
