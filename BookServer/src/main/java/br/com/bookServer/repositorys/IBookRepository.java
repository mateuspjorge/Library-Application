package br.com.bookServer.repositorys;

import br.com.bookServer.entitys.Book;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface IBookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT book FROM Book book WHERE UPPER(book.title) LIKE %:title% ")
    Book findByTitle(@Param("title") String title);

}