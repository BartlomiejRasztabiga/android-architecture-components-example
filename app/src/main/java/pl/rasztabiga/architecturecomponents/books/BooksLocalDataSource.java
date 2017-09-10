package pl.rasztabiga.architecturecomponents.books;


import android.support.annotation.NonNull;

public class BooksLocalDataSource implements BooksDataSource {

    private static BooksLocalDataSource instance;

    public static BooksLocalDataSource getInstance() {
        if (instance == null) {
            instance = new BooksLocalDataSource();
        }

        return instance;
    }

    @Override
    public void getBooks(@NonNull LoadBooksCallback callback) {
        callback.onDataNotAvailable();
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
