package pl.rasztabiga.architecturecomponents.books.persistence;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BooksRestApi {

    @GET("books/")
    Call<List<Book>> getBooks();

    @POST("books/")
    Call<Book> createBook(@Body Book book);
}
