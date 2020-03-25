package com.bigbang.booksapi.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigbang.booksapi.R;
import com.bigbang.booksapi.adapter.BookResultAdapter;
import com.bigbang.booksapi.model.BookResult;
import com.bigbang.booksapi.model.Item;
import com.bigbang.booksapi.util.DebugLogger;
import com.bigbang.booksapi.viewmodel.BookViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import static com.bigbang.booksapi.util.DebugLogger.*;

public class SearchFragment extends Fragment {

    private BookViewModel viewModel;
    private BookResultAdapter bookAdapter;
    private CompositeDisposable disposable = new CompositeDisposable();

    @BindView(R.id.search_results_recyclerview)
    RecyclerView searchRecyclerView;

    @BindView(R.id.input_keyword_textinput)
    EditText keywordEditText;

    @BindView(R.id.input_author_textinput)
    EditText authorEditText;

    @BindView(R.id.search_button)
    Button searchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_search_frag_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        disposable.add(viewModel.getList().subscribe(bookResult -> {
            displayBooks(bookResult);
        }, throwable-> {

                    DebugLogger.logError(throwable);
        }));
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchRecyclerView.setAdapter(bookAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
    }

    private void displayBooks(BookResult bookResult) {
            bookAdapter.setResults(bookResult.getItems());
        }


    private void performSearch() {
        String keyword = keywordEditText.getEditableText().toString();
        String author = authorEditText.getEditableText().toString();
        viewModel.searchVolumes(keyword, author);
    }
}
