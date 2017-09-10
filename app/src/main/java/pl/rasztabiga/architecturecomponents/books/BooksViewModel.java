package pl.rasztabiga.architecturecomponents.books;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import pl.rasztabiga.architecturecomponents.R;
import pl.rasztabiga.architecturecomponents.SingleLiveEvent;
import pl.rasztabiga.architecturecomponents.SnackbarMessage;
import pl.rasztabiga.architecturecomponents.addeditbook.AddEditBookActivity;
import pl.rasztabiga.architecturecomponents.bookdetail.BookDetailActivity;

/**
 * Exposes the data to be used in the book list screen.
 * <p>
 * {@link BaseObservable} implements a listener registration mechanism which is notified when a
 * property changes. This is done by assigning a {@link Bindable} annotation to the property's
 * getter method.
 */
public class BooksViewModel extends AndroidViewModel {

    // These observable fields will update Views automatically
    public final ObservableList<Book> items = new ObservableArrayList<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    public final ObservableBoolean empty = new ObservableBoolean(false);

    public final ObservableBoolean booksAddViewVisible = new ObservableBoolean();

    private final SnackbarMessage mSnackbarText = new SnackbarMessage();

    private final BooksRepository mBooksRepository;

    private final ObservableBoolean mIsDataLoadingError = new ObservableBoolean(false);

    private final SingleLiveEvent<String> mOpenBookEvent = new SingleLiveEvent<>();

    private final Context mContext; // To avoid leaks, this must be an Application Context.

    private final SingleLiveEvent<Void> mNewBookEvent = new SingleLiveEvent<>();

    public BooksViewModel(
            Application context,
            BooksRepository repository) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
        mBooksRepository = repository;
    }

    public void start() {
        loadBooks(false);
    }

    public void loadBooks(boolean forceUpdate) {
        loadBooks(forceUpdate, true);
    }

    SnackbarMessage getSnackbarMessage() {
        return mSnackbarText;
    }

    SingleLiveEvent<String> getOpenTaskEvent() {
        return mOpenBookEvent;
    }

    SingleLiveEvent<Void> getNewTaskEvent() {
        return mNewBookEvent;
    }

    private void showSnackbarMessage(Integer message) {
        mSnackbarText.setValue(message);
    }

    /**
     * Called by the Data Binding library and the FAB's click listener.
     */
    public void addNewBook() {
        mNewBookEvent.call();
    }

    void handleActivityResult(int requestCode, int resultCode) {
        if (AddEditBookActivity.REQUEST_CODE == requestCode) {
            switch (resultCode) {
                case BookDetailActivity.EDIT_RESULT_OK:
                    mSnackbarText.setValue(R.string.successfully_saved_book_message);
                    break;
                case AddEditBookActivity.ADD_EDIT_RESULT_OK:
                    mSnackbarText.setValue(R.string.successfully_added_book_message);
                    break;
                case BookDetailActivity.DELETE_RESULT_OK:
                    mSnackbarText.setValue(R.string.successfully_deleted_book_message);
                    break;
            }
        }
    }

    /**
     * @param forceUpdate   Pass in true to refresh the data in the {@link BooksDataSource}
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    private void loadBooks(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            dataLoading.set(true);
        }
        if (forceUpdate) {

            mBooksRepository.refreshBooks();
        }

        mBooksRepository.getBooks(new BooksDataSource.LoadBooksCallback() {
            @Override
            public void onBooksLoaded(List<Book> books) {
                if (showLoadingUI) {
                    dataLoading.set(false);
                }
                mIsDataLoadingError.set(false);

                items.clear();
                items.addAll(books);
                empty.set(items.isEmpty());
            }

            @Override
            public void onDataNotAvailable() {
                mIsDataLoadingError.set(true);
            }
        });
    }
}
