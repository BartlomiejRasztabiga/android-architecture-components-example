package pl.rasztabiga.architecturecomponents.books.persistence;


import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksRemoteDataSource implements BooksDataSource {

    private static BooksRemoteDataSource instance;

    private BooksRestApi restApi;

    public static BooksRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new BooksRemoteDataSource();
        }
        return instance;
    }

    private BooksRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://59bd098b5037eb00117b4b17.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.restApi = retrofit.create(BooksRestApi.class);
    }

    @Override
    public void getBooks(@NonNull LoadBooksCallback callback) {
        Call<List<Book>> booksCall = restApi.getBooks();

        Observable.fromCallable(() -> booksCall.execute().body())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onBooksLoaded);
    }

    @Override
    public void getBook(@NonNull Long bookId, @NonNull GetBookCallback callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveBook(@NonNull Book book) {
        Call<Book> createBookCall = restApi.createBook(book);

        Observable.fromCallable(() -> createBookCall.execute().body())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
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
