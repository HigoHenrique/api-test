package com.api.librarystudio.domain.repository;

import com.api.librarystudio.domain.model.Book;
import com.api.librarystudio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByUser(User user);
}
