package pl.rasztabiga.architecturecomponents.books.persistence;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BooksRestApi {
    @GET("books/")
    Call<List<Book>> getBooks();
}
