package com.rojer_ko.homework.lesson_4.data.network;

import com.rojer_ko.homework.lesson_4.domain.model.GitHubRepo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubRepoApi {
    @GET("users/{user}/repos")
    Single<List<GitHubRepo>> loadRepositories(@Path("user") String user);
}
