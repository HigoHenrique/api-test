package com.api.librarystudio.domain.service;

import com.api.librarystudio.domain.model.Book;
import com.api.librarystudio.domain.model.User;
import com.api.librarystudio.domain.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBook(Long id){
        return findOrFail(id);
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Book update(Long id, Book book){
        Book bookSave = findOrFail(id);
        BeanUtils.copyProperties(book, bookSave);
        return bookRepository.save(bookSave);
    }

    public void delete(Long id){
        Book book = findOrFail(id);
        bookRepository.delete(book);
    }

    private Book findOrFail(long id){
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("Livro não encontrado!")));
    }

}
