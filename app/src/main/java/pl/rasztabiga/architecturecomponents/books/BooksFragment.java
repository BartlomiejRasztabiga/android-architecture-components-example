package pl.rasztabiga.architecturecomponents.books;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import pl.rasztabiga.architecturecomponents.R;
import pl.rasztabiga.architecturecomponents.ScrollChildSwipeRefreshLayout;
import pl.rasztabiga.architecturecomponents.databinding.BooksFragBinding;

public class BooksFragment extends LifecycleFragment {

    private BooksViewModel mBooksViewModel;

    private BooksFragBinding mBooksFragBinding;

    private BooksAdapter mListAdapter;

    public BooksFragment() {
        // Requires empty public constructor
    }

    public static BooksFragment newInstance() {
        return new BooksFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBooksViewModel.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBooksFragBinding = BooksFragBinding.inflate(inflater, container, false);

        mBooksViewModel = BooksActivity.obtainViewModel(getActivity());

        mBooksFragBinding.setViewmodel(mBooksViewModel);

        setHasOptionsMenu(true);

        return mBooksFragBinding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.books_fragment_menu, menu);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //setupSnackbar();

        setupFab();

        setupListAdapter();

        setupRefreshLayout();
    }

    private void setupFab() {
        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_task);

        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(v -> mBooksViewModel.addNewBook());
    }

    private void setupListAdapter() {
        ListView listView = mBooksFragBinding.booksList;

        mListAdapter = new BooksAdapter(
                new ArrayList<>(0),
                mBooksViewModel
        );
        listView.setAdapter(mListAdapter);
    }

    private void setupRefreshLayout() {
        ListView listView = mBooksFragBinding.booksList;
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = mBooksFragBinding.refreshLayout;
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(listView);
    }
}