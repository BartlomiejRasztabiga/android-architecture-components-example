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
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveBook(@NonNull Book book) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void completeBook(@NonNull Book book) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void completeBook(@NonNull Long bookId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void refreshBooks() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllBooks() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteBooks(@NonNull Long bookId) {
        throw new UnsupportedOperationException();
    }
}
