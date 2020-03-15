package com.rojer_ko.homework.lesson_4.domain.repository;

import com.rojer_ko.homework.lesson_4.domain.model.GitHubRepo;
import java.util.List;
import io.reactivex.Single;

public interface GitHubRepoRepository {
    Single<List<GitHubRepo>> getRepos(String user);
}
