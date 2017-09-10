package pl.rasztabiga.architecturecomponents.books;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;

//TODO Add lombok
public final class Book {

    @NonNull
    private final Long mId;

    @Nullable
    private final String mTitle;

    @Nullable
    private final Long mPages;

    private boolean mCompleted;

    public Book(@NonNull Long id, @Nullable String title, @Nullable Long pages) {
        this(id, title, pages, false);
    }

    public Book(@NonNull Long id, @Nullable String title, @Nullable Long pages,
                boolean completed) {
        mId = id;
        mTitle = title;
        mPages = pages;
        mCompleted = completed;
    }

    @NonNull
    public Long getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public Long getPages() {
        return mPages;
    }

    @NonNull
    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public boolean isActive() {
        return !mCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book task = (Book) o;
        return Objects.equal(mId, task.mId) &&
                Objects.equal(mTitle, task.mTitle) &&
                Objects.equal(mPages, task.mPages);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mTitle, mPages);
    }

    @Override
    public String toString() {
        return "Book with title " + mTitle;
    }
}
