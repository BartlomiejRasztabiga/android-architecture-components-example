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
