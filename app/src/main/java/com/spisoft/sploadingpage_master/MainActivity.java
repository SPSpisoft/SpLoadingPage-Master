package com.spisoft.sploadingpage_master;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.spisoft.sploadingpage.SPLoadingPage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SPLoadingPage vLoadingPage = findViewById(R.id.spLoadingPage);
        vLoadingPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vLoadingPage.SetStatus(1);
            }
        });
        vLoadingPage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                vLoadingPage.SetStatus(-1);
                return true;
            }
        });

        vLoadingPage.setOnRetryClickListener(new SPLoadingPage.OnRetryClickListener() {
            @Override
            public void onEvent() {
                vLoadingPage.SetStatus(0);
            }
        });
    }
}