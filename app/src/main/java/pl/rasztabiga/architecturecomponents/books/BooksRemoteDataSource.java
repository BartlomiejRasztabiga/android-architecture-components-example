package pl.rasztabiga.architecturecomponents.books;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BooksRemoteDataSource implements BooksDataSource {

    private static BooksRemoteDataSource instance;

    public static BooksRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new BooksRemoteDataSource();
        }

        return instance;
    }

    @Override
    public void getBooks(@NonNull LoadBooksCallback callback) {
        // TODO Remove
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Title", 250L));
        books.add(new Book(2L, "Second title", 800L));

        callback.onBooksLoaded(books);
    }

    @Override
    public void getBook(@NonNull Long bookId, @NonNull GetBookCallback callback) {

    }

    @Override
    public void saveBook(@NonNull Book book) {

    }

    @Override
    public void completeBook(@NonNull Book book) {

    }

    @Override
    public void completeBook(@NonNull Long bookId) {

    }

    @Override
    public void refreshBooks() {

    }

    @Override
    public void deleteAllBooks() {

    }

    @Override
    public void deleteBooks(@NonNull Long bookId) {

    }
}
