package com.rojer_ko.homework.lesson_4.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.EditText;
import com.rojer_ko.homework.R;
import com.rojer_ko.homework.lesson_4.data.network.IRestApiForRepos;
import com.rojer_ko.homework.lesson_4.data.network.RetrofitInit;
import com.rojer_ko.homework.lesson_4.data.repository.GitHubRepoRepositoryImpl;
import com.rojer_ko.homework.lesson_4.domain.repository.GitHubRepoRepository;
import com.rojer_ko.homework.lesson_4.domain.usecase.GitHubRepoInteractor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GitHubRepoActivity extends AppCompatActivity {

    private GitHubRepoAdapter adapter;
    private GitHubViewModel viewModel;
    private String user;
    private GitHubRepoInteractor interactor;

    @BindView(R.id.rv_git_hub_repos)
    RecyclerView rv;

    @BindView(R.id.userNameEditText)
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_repo);
        ButterKnife.bind(this);

        user = userNameEditText.getText().toString();

            initRetrofit(user);
            initViewModel(interactor);
            initRecycleView();

            viewModel.gitHubLiveData.observe(this, data -> {
                adapter.setList(data);
            });
    }

    @OnClick(R.id.getReposBtn)
    void getData(){
        user = userNameEditText.getText().toString();
        initRetrofit(user);
        //initViewModel(interactor);
        //в интерактор данные приходят, не могу понять
        //как их положить в ViewModel

    }

    private void initRetrofit(String userName){
        IRestApiForRepos api = RetrofitInit.newApiInstance();
        GitHubRepoRepository repository = new GitHubRepoRepositoryImpl(api, userName);
        interactor = new GitHubRepoInteractor(repository);
    }

    private void initViewModel(GitHubRepoInteractor interactor){
        viewModel = ViewModelProviders.of(this, new GitHubViewModelFactory(interactor)).get(GitHubViewModel.class);
        getLifecycle().addObserver(viewModel);
    }

    private void initRecycleView(){
        adapter = new GitHubRepoAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}
