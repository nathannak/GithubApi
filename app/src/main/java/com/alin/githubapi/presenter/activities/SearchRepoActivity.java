package com.alin.githubapi.presenter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alin.githubapi.R;
import com.alin.githubapi.util.UiUtils;

import androidx.appcompat.app.AppCompatActivity;

public class SearchRepoActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_repo);

        final EditText edtSearch = findViewById(R.id.edtSearch);

        Button btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!edtSearch.getText().toString().isEmpty())
                {
                    UiUtils.hideSoftKeyboard(SearchRepoActivity.this, v);
                    Intent intent = new Intent(SearchRepoActivity.this,MainActivity.class);
                    intent.putExtra("query",edtSearch.getText().toString());
                    startActivity(intent);
                }else
                {
                    Toast.makeText(getApplicationContext(), "Search field can't be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
