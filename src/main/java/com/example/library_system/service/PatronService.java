package com.example.library_system.service;

import com.example.library_system.builder.PatronBuilder;
import com.example.library_system.model.Patron;
import com.example.library_system.repository.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository ;
    public List<Patron> getAllPatrons () {
        return patronRepository.findAll() ;
    }
    @Cacheable(value = "patrons" , key = "#id")
    public Patron getPatron (Long id) {
        return patronRepository.findById(id).get() ;
    }
    @Transactional
    @CachePut(value = "patrons" , key = "#patron.patronId")
    public Patron save (Patron patron) {
        return patronRepository.save(patron) ;
    }
    @Transactional
    @CacheEvict(value = "patrons" , key = "#id")
    public void delete (Long id) {
        Patron patron = getPatron(id) ;
        patronRepository.delete(patron);
    }

    private Patron updatePatron (Patron  currentPatron , Patron newPatron) {
        // Return a new patron with the data of the current and new merged together
        return new PatronBuilder()
                .id(currentPatron.getPatronId())
                .name(newPatron.getName())
                .contact(newPatron.getContactInformation())
                .build() ;
    }
    @Transactional
    @Cacheable(value = "patrons" , key = "#id")
    public Patron update (Long id , Patron updatedPatron) {
        Patron patron = getPatron(id) ;
        patron = updatePatron(patron , updatedPatron) ;
        patronRepository.save(patron) ;
        return patron ;
    }
}
