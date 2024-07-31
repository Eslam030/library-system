package com.example.library_system.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patron implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long PatronId ;

    @Column(nullable = false)
    private String name ;
    @Column(nullable = false)
    private String contactInformation ;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "patron" , orphanRemoval = true)
    private List<Borrowing> borrowings = new ArrayList<>();

    public Patron(Long id , String name , String contactInformation) {
        this.PatronId = id ;
        this.name = name ;
        this.contactInformation = contactInformation ;
    }

    public String getName() {
        return name;
    }
    public Patron () {

    }


    public Long getPatronId() {
        return PatronId;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronId(Long patronId) {
        PatronId = patronId;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return String.format(
                "Id %s\nName %s\nContact %s\n" ,
                this.PatronId , this.name , this.contactInformation
        ) ;
    }

}
