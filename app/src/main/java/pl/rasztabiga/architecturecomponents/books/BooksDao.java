package pl.rasztabiga.architecturecomponents.books;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BooksDao {

    @Query("SELECT * FROM Books")
    List<Book> getBooks();

    @Query("SELECT * FROM Books WHERE book_id = :bookId")
    Book getBookById(Long bookId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBook(Book book);

    @Update
    int updateBook(Book book);

    @Query("DELETE FROM Books WHERE book_id = :bookId")
    int deleteBookById(Long bookId);

    @Query("DELETE FROM Books")
    void deleteBooks();
}
