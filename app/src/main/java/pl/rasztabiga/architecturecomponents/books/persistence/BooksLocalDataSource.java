package pl.rasztabiga.architecturecomponents.books.persistence;


import android.os.AsyncTask;
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
        new AsyncTask<Void, Void, List<Book>>() {
            @Override
            protected List<Book> doInBackground(Void... voids) {
                return mBooksDao.getBooks();
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                if (books.isEmpty()) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onBooksLoaded(books);
                }
            }
        }.execute();
    }

    @Override
    public void getBook(@NonNull Long bookId, @NonNull GetBookCallback callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveBook(@NonNull Book book) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mBooksDao.insertBook(book);
                return null;
            }
        }.execute();
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
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mBooksDao.deleteBooks();
                return null;
            }
        }.execute();
    }

    @Override
    public void deleteBooks(@NonNull Long bookId) {
        throw new UnsupportedOperationException();
    }
}
