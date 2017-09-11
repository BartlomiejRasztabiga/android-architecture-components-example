package pl.rasztabiga.architecturecomponents.books;


import android.support.annotation.NonNull;

import java.util.List;

public class BooksLocalDataSource implements BooksDataSource {

    private static BooksLocalDataSource instance;

    private BooksDao mBooksDao;

    private BooksLocalDataSource(@NonNull BooksDao booksDao) {
        mBooksDao = booksDao;
    }

    public static BooksLocalDataSource getInstance(@NonNull BooksDao booksDao) {
        if (instance == null) {
            instance = new BooksLocalDataSource(booksDao);
        }
        return instance;
    }

    @Override
    public void getBooks(@NonNull LoadBooksCallback callback) {
        final List<Book> books = mBooksDao.getBooks();
        if (books.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onBooksLoaded(books);
        }
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
