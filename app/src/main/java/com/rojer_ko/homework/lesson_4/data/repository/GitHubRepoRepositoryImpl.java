package com.rojer_ko.homework.lesson_4.data.repository;

import com.rojer_ko.homework.lesson_4.data.network.GitHubRepoApi;
import com.rojer_ko.homework.lesson_4.domain.model.GitHubRepo;
import com.rojer_ko.homework.lesson_4.domain.repository.GitHubRepoRepository;
import java.util.List;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GitHubRepoRepositoryImpl implements GitHubRepoRepository {

    private GitHubRepoApi api;

    public GitHubRepoRepositoryImpl(GitHubRepoApi api) {
        this.api = api;
    }

    @Override
    public Single<List<GitHubRepo>> getRepos(String user){
        return  api.loadRepositories(user)
                .subscribeOn(Schedulers.io());
    }
}
