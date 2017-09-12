package pl.rasztabiga.architecturecomponents.books.persistence;


import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        Observable.fromCallable(() -> mBooksDao.getBooks())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(books -> {
                    if (books.isEmpty()) {
                        callback.onDataNotAvailable();
                    } else {
                        callback.onBooksLoaded(books);
                    }
                });
    }

    @Override
    public void getBook(@NonNull Long bookId, @NonNull GetBookCallback callback) {
        Observable.fromCallable(() -> mBooksDao.getBookById(bookId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(book -> {
                    if (book == null) {
                        callback.onDataNotAvailable();
                    } else {
                        callback.onBookLoaded(book);
                    }
                });
    }

    @Override
    public void saveBook(@NonNull Book book) {
        Observable.fromCallable(() -> {
            mBooksDao.insertBook(book);
            return null;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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
        Observable.fromCallable(() -> {
            mBooksDao.deleteBooks();
            return null;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void deleteBooks(@NonNull Long bookId) {
        throw new UnsupportedOperationException();
    }
}
