package com.rojer_ko.homework.lesson_4.domain.usecase;

import com.rojer_ko.homework.lesson_4.domain.model.GitHubRepo;
import com.rojer_ko.homework.lesson_4.domain.repository.GitHubRepoRepository;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

public class GitHubRepoInteractor {

    private GitHubRepoRepository repository;

    public GitHubRepoInteractor(GitHubRepoRepository repository) {
        this.repository = repository;
    }

    public Single<List<GitHubRepo>> getRepos(){
        return  repository.getRepos()
                .map(list -> {
                    Collections.sort(list);
                    return list;
                });
    }
}
