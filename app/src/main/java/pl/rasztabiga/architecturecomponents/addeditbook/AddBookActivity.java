package pl.rasztabiga.architecturecomponents.addeditbook;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import pl.rasztabiga.architecturecomponents.R;
import pl.rasztabiga.architecturecomponents.ViewModelFactory;
import pl.rasztabiga.architecturecomponents.util.ActivityUtils;

/**
 * Displays an add book screen.
 */
public class AddBookActivity extends AppCompatActivity implements AddBookNavigator {

    public static final int REQUEST_CODE = 1;

    public static final int ADD_EDIT_RESULT_OK = RESULT_FIRST_USER + 1;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBookSaved() {
        setResult(ADD_EDIT_RESULT_OK);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook_act);

        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        AddBookFragment addEditTaskFragment = obtainViewFragment();

        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),
                addEditTaskFragment, R.id.contentFrame);

        subscribeToNavigationChanges();
    }

    private void subscribeToNavigationChanges() {
        AddBookViewModel viewModel = obtainViewModel(this);

        // The activity observes the navigation events in the ViewModel
        viewModel.getBookUpdatedEvent().observe(this, e -> AddBookActivity.this.onBookSaved());
    }

    public static AddBookViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(AddBookViewModel.class);
    }

    @NonNull
    private AddBookFragment obtainViewFragment() {
        // View Fragment
        AddBookFragment addBookFragment = (AddBookFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (addBookFragment == null) {
            addBookFragment = AddBookFragment.newInstance();

            // Send the task ID to the fragment
            Bundle bundle = new Bundle();
            bundle.putLong(AddBookFragment.ARGUMENT_EDIT_BOOK_ID,
                    getIntent().getLongExtra(AddBookFragment.ARGUMENT_EDIT_BOOK_ID, 0L));
            addBookFragment.setArguments(bundle);
        }
        return addBookFragment;
    }
}
