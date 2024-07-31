package com.example.library_system.repository;

import com.example.library_system.model.Borrowing;
import com.example.library_system.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}
