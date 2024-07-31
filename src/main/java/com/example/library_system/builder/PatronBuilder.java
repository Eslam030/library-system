package com.example.library_system.builder;

import com.example.library_system.model.Patron;

public class PatronBuilder {
    private Long PatronId ;

    private String name ;
    private String contactInformation ;

    public PatronBuilder () {}
    public Patron build () {
        return new Patron(PatronId , name , contactInformation) ;
    }

    public PatronBuilder id (Long id) {
        this.PatronId = id ;
        return this ;
    }
    public PatronBuilder name (String name) {
        this.name = name ;
        return this ;
    }
    public  PatronBuilder contact (String contact) {
        this.contactInformation = contact ;
        return this ;
    }
}
