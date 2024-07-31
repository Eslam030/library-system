package com.example.library_system.controller;

import com.example.library_system.model.Book;
import com.example.library_system.model.Patron;
import com.example.library_system.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService ;
    private HashMap<String , Object> response ;

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons () {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(patronService.getAllPatrons()) ;
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String , Object>> getPatron (@PathVariable("id") Long id ) {
        response = new HashMap<>() ;
        HttpStatus status = null ;
        try {
            Patron patron = patronService.getPatron(id) ;
            response.put("patron" , patron) ;
            status = HttpStatus.OK ;
        }catch (Exception ex) {
            response.put("error" , ex.toString()) ;
            status = HttpStatus.NOT_FOUND ;
        }
        return ResponseEntity.status(status).body(response) ;
    }
    @PostMapping
    public ResponseEntity<Map<String , Object>> addPatron (@RequestBody Patron patron) {
        response = new HashMap<>() ;
        HttpStatus status = null ;
        try {
            Patron to_save_patron = patronService.save(patron) ;
            response.put("message" , "Done") ;
            response.put("patron" , to_save_patron) ;
            status = HttpStatus.CREATED ;
        }catch (Exception ex) {
            response.put("error" , ex.toString()) ;
            status = HttpStatus.BAD_REQUEST ;
        }
        return ResponseEntity.status(status).body(response) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String , Object>> deletePatron (@PathVariable("id") Long id) {
        response = new HashMap<>() ;
        HttpStatus status = null ;
        try {
            patronService.delete(id) ;
            response.put("message" , "Deleted Successfully") ;
            status = HttpStatus.OK;
        }catch (Exception ex) {
            response.put("error" , ex.toString()) ;
            status = HttpStatus.NOT_FOUND ;
        }
        return ResponseEntity.status(status).body(response) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String , Object>> updatePatron (@PathVariable("id") Long id , @RequestBody Patron updatedPatron) {
        response = new HashMap<>() ;
        HttpStatus status = null ;
        try {
            patronService.update(id , updatedPatron) ;
            response.put("message" , "Updated Successfully") ;
            status = HttpStatus.OK ;
        }catch (Exception ex) {
            response.put("error" , ex.toString()) ;
            status = HttpStatus.NOT_FOUND ;
        }
        return ResponseEntity.status(status).body(response) ;
    }
}
