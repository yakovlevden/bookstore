package ru.learnup.march.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.entity.BookStorage;

import java.util.List;

@Repository
public interface BookStorageRepository extends JpaRepository<BookStorage, Long> {

    @Query("SELECT bs.count FROM BookStorage bs WHERE bs.id = ?1")
    Integer getCountById(Long id);

    @Modifying
    @Query("UPDATE BookStorage bs SET bs.count = bs.count - ?2 where bs.id = ?1")
    void takeBook(Long id, Integer number);
}
