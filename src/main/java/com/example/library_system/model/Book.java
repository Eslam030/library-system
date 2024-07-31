package com.example.library_system.model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity

public class Book implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long BookId;
    @Column(nullable = false)
    private String title ;
    @Column(nullable = false)
    private String author ;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date publication_year ;

    private Boolean availableForBorrowing = true ;
    @Column(unique = true , nullable = false)
    private Long isbn ;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "book" , orphanRemoval = true)
    private List<Borrowing> borrowings = new ArrayList<>();

    public Book () {

    }

    public Book (Long id , Boolean availableForBorrowing , String title , String author ,Date publication_year , Long isbn) {
        this.title = title ;
        this.author = author ;
        this.publication_year = publication_year ;
        this.isbn = isbn ;
        this.BookId = id ;
        this.availableForBorrowing = availableForBorrowing ;
    }


    public Boolean getAvailableForBorrowing() {
        return availableForBorrowing;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublication_year() {
        return publication_year;
    }

    public Long getBookId() {
        return BookId;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setAvailableForBorrowing(Boolean availableForBorrowing) {
        this.availableForBorrowing = availableForBorrowing;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublication_year(Date publication_year) {
        this.publication_year = publication_year;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookId(Long bookId) {
        BookId = bookId;
    }

    @Override
    public String toString() {
        return String.format(
                "Id %s\nTitle %s\nAuthor %s\nPublication Year %s\nISBN %s\nAvailable %s\n" ,
                this.BookId , this.title , this.author , this.publication_year , this.isbn , this.availableForBorrowing
        ) ;
    }
}

