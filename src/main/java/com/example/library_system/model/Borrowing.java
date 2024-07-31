package com.example.library_system.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;




@Entity
public class Borrowing implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id ;

    @ManyToOne
    @JoinColumn(name = "BookId", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patronId" , nullable = false)
    private Patron patron ;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date borrowDate ;
    @Temporal(TemporalType.DATE)
    private Date returnDate ;


    public Borrowing () {}
    public Borrowing (Book book , Patron patron , Date borrowDate , Date returnDate) {
        this.book = book ;
        this.patron = patron ;
        this.borrowDate = borrowDate ;
        this.returnDate = returnDate ;
    }


    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Patron getPatron() {
        return patron;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return String.format(
                "Id %s\nBook %s\nPatron %s\nBorrow Date %s\nReturn Date %s" ,
                this.id , this.book , this.patron , this.borrowDate , this.returnDate
        ) ;
    }
}
