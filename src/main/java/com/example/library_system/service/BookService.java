package com.example.library_system.service;

import com.example.library_system.builder.BookBuilder;
import com.example.library_system.model.Book;
import com.example.library_system.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks () {
        return bookRepository.findAll() ;
    }

    @Cacheable(value = "books", key = "#id")
    public Book getBook (Long id) {
        return bookRepository.findById(id).get() ;
    }
    @Transactional
    @CachePut(value = "books" , key = "#book.bookId")
    public Book save (Book book) {
        return bookRepository.save(book) ;
    }

    @Transactional
    @CacheEvict(value = "books" , key = "#id" )
    public void delete (Long id) {
        Book book = getBook(id) ;
        bookRepository.delete(book);
    }

    private Book updateBook (Book  currentBook , Book newBook) {
        // Return a new Book with the data of the current and new merged together
        return new BookBuilder()
                .author(newBook.getAuthor())
                .isbn(newBook.getIsbn())
                .title(newBook.getTitle())
                .publicationYear(newBook.getPublication_year())
                .id(currentBook.getBookId())
                .availableForBorrowing(currentBook.getAvailableForBorrowing())
                .build() ;
    }
    @Transactional
    @CachePut(value = "books" , key = "#id")
    public Book update (Long id , Book updatedBook) {
        Book book = getBook(id) ;
        book = updateBook(book , updatedBook) ;
        bookRepository.save(book) ;
        return book ;
    }
}
