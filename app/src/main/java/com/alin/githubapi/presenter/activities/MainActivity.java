package com.alin.githubapi.presenter.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alin.githubapi.R;
import com.alin.githubapi.interfaces.HTTPRequest;
import com.alin.githubapi.models.Item;
import com.alin.githubapi.models.dto.response.ReposResponse;
import com.alin.githubapi.presenter.adapters.MyRepoAdapter;
import com.alin.githubapi.service.ApiManager;
import com.alin.githubapi.util.UiUtils;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements HTTPRequest
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchRepos(getIntent().getStringExtra("query"));
    }

    private void fetchRepos(String query)
    {
        if (UiUtils.isNetworkAvailable(getApplicationContext()))
        {
            ApiManager.getInstance().fetchRepos("user:"+query,"stars","desc", MainActivity.this);

        } else
        {

            Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequest()
    {
        UiUtils.showDialog(this, getString(R.string.fetching_data_msg), getString(R.string.please_wait_msg));
    }

    @Override
    public void onResponse(Object response)
    {
        UiUtils.hideProgress();

        RecyclerView repos = findViewById(R.id.rvRepos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        repos.setLayoutManager(linearLayoutManager);

        List<Item> item = ((ReposResponse)response).getItems();

        if(item != null && item.size() >0)
        {
            if(item.size() > 3)
            {
                item = item.subList(0,3);
            }

            MyRepoAdapter adapter = new MyRepoAdapter(item, MainActivity.this);
            repos.setAdapter(adapter);
            repos.setVisibility(View.VISIBLE);
            findViewById(R.id.tvEmptyView).setVisibility(View.GONE);
        }else
        {
           findViewById(R.id.tvEmptyView).setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onError(String errorMessage)
    {
        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
        UiUtils.hideProgress();
    }
}
