package pl.rasztabiga.architecturecomponents.books;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import pl.rasztabiga.architecturecomponents.LifecycleAppCompatActivity;
import pl.rasztabiga.architecturecomponents.R;
import pl.rasztabiga.architecturecomponents.ViewModelFactory;
import pl.rasztabiga.architecturecomponents.util.ActivityUtils;

public class BooksActivity extends LifecycleAppCompatActivity implements BooksNavigator, BookItemNavigator {

    private DrawerLayout mDrawerLayout;

    private BooksViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_act);

        setupToolbar();

        setupNavigationDrawer();

        setupViewFragment();

        mViewModel = obtainViewModel(this);

        // Subscribe to "open book" event
        mViewModel.getOpenBookEvent().observe(this, bookId -> {
            if (bookId != null) {
                openBookDetails(bookId);
            }
        });

        // Subscribe to "new task" event
        mViewModel.getNewTaskEvent().observe(this, e -> addNewBook());
    }

    public static BooksViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(BooksViewModel.class);
    }

    private void setupViewFragment() {
        BooksFragment booksFragment =
                (BooksFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (booksFragment == null) {
            // Create the fragment
            booksFragment = BooksFragment.newInstance();
            ActivityUtils.replaceFragmentInActivity(
                    getSupportFragmentManager(), booksFragment, R.id.contentFrame);
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setupNavigationDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.list_navigation_menu_item:
                            // Do nothing, we're already on that screen
                            break;
                        case R.id.statistics_navigation_menu_item:
                            //TODO change
                            Intent intent =
                                    new Intent(BooksActivity.this, BooksActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            break;
                        default:
                            break;
                    }
                    // Close the navigation drawer when an item is selected.
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mViewModel.handleActivityResult(requestCode, resultCode);
    }

    @Override
    public void openBookDetails(Long bookId) {
        //TODO openBookDetails
/*        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra(BookDetailActivity.EXTRA_BOOK_ID, bookId);
        startActivityForResult(intent, AddEditBookActivity.REQUEST_CODE);*/

    }

    @Override
    public void addNewBook() {
        //TODO addBook
/*        Intent intent = new Intent(this, AddEditBookActivity.class);
        startActivityForResult(intent, AddEditBookActivity.REQUEST_CODE);*/
    }
}
