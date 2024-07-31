package com.example.library_system.service;

import com.example.library_system.builder.PatronBuilder;
import com.example.library_system.model.Patron;
import com.example.library_system.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository ;

    public List<Patron> getAllPatrons () {
        return patronRepository.findAll() ;
    }

    public Patron getPatron (Long id) {
        return patronRepository.findById(id).get() ;
    }
    public Patron save (Patron patron) {
        return patronRepository.save(patron) ;
    }

    public void delete (Long id) {
        Patron patron = getPatron(id) ;
        patronRepository.delete(patron);
    }
    private Patron updatePatron (Patron  currentPatron , Patron newPatron) {
        return new PatronBuilder()
                .id(currentPatron.getPatronId())
                .name(newPatron.getName())
                .contact(newPatron.getContactInformation())
                .build() ;
    }

    public Patron update (Long id , Patron updatedPatron) {
        Patron patron = getPatron(id) ;
        patron = updatePatron(patron , updatedPatron) ;
        patronRepository.save(patron) ;
        return patron ;
    }
}
