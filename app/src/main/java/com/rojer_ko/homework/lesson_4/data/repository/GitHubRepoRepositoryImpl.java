package com.rojer_ko.homework.lesson_4.data.repository;

import com.rojer_ko.homework.lesson_4.data.network.IRestApiForRepos;
import com.rojer_ko.homework.lesson_4.domain.model.GitHubRepo;
import com.rojer_ko.homework.lesson_4.domain.repository.GitHubRepoRepository;
import java.util.List;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GitHubRepoRepositoryImpl implements GitHubRepoRepository {

    private IRestApiForRepos api;
    private String user;

    public GitHubRepoRepositoryImpl(IRestApiForRepos api, String user) {
        this.api = api;
        this.user = user;
    }

    @Override
    public Single<List<GitHubRepo>> getRepos(){
        return  api.loadRepositories(user)
                .subscribeOn(Schedulers.io());
    }
}
