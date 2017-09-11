package pl.rasztabiga.architecturecomponents.books;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;

//TODO Add lombok
@Entity(tableName = "books")
public final class Book {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "book_id")
    private final Long mId;

    @Nullable
    @ColumnInfo(name = "title")
    private final String mTitle;

    @Nullable
    @ColumnInfo(name = "pages")
    private final Long mPages;

    @ColumnInfo(name = "completed")
    private boolean mCompleted;

    @Ignore
    public Book(@NonNull Long id, @Nullable String title, @Nullable Long pages) {
        mId = id;
        mTitle = title;
        mPages = pages;
        mCompleted = false;
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
