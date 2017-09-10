package pl.rasztabiga.architecturecomponents;

import android.content.Context;
import android.support.annotation.NonNull;

import pl.rasztabiga.architecturecomponents.books.BooksLocalDataSource;
import pl.rasztabiga.architecturecomponents.books.BooksRemoteDataSource;
import pl.rasztabiga.architecturecomponents.books.BooksRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static BooksRepository provideBooksRepository(@NonNull Context context) {
        checkNotNull(context);
        return BooksRepository.getInstance(BooksRemoteDataSource.getInstance(),
                BooksLocalDataSource.getInstance());
    }
}
