package com.rojer_ko.homework.lesson_4.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.EditText;
import com.rojer_ko.homework.R;
import com.rojer_ko.homework.lesson_4.App;
import com.rojer_ko.homework.lesson_4.di.components.AppComponent;
import com.rojer_ko.homework.lesson_4.di.components.DaggerGitHubRepoActivityComponent;
import com.rojer_ko.homework.lesson_4.di.components.GitHubRepoActivityComponent;
import com.rojer_ko.homework.lesson_4.domain.usecase.GitHubRepoInteractor;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GitHubRepoActivity extends AppCompatActivity {

    private GitHubRepoAdapter adapter;

    @Inject
    GitHubViewModel viewModel;

    @BindView(R.id.rv_git_hub_repos)
    RecyclerView rv;

    @BindView(R.id.userNameEditText)
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_repo);
        ButterKnife.bind(this);

        AppComponent dependensies = ((App)getApplication()).getAppComponent();

        DaggerGitHubRepoActivityComponent.builder()
                .setActivity(this)
                .setDependencies(dependensies)
                .build()
                .inject(this);

        initRecycleView();

            viewModel.gitHubLiveData.observe(this, data -> {
                adapter.setList(data);
            });
    }

    @OnClick(R.id.getReposBtn)
    void getData(){
        String user = userNameEditText.getText().toString();
        viewModel.getRepos(user);
    }

    private void initRecycleView(){
        adapter = new GitHubRepoAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}
