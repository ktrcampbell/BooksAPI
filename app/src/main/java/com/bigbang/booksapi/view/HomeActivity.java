package com.bigbang.booksapi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bigbang.booksapi.R;

import io.reactivex.disposables.CompositeDisposable;

public class HomeActivity extends AppCompatActivity {

    private CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
