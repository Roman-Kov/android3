package com.rojer_ko.homework.lesson_4.di.modules;

import com.rojer_ko.homework.lesson_4.data.network.GitHubRepoApi;
import com.rojer_ko.homework.lesson_4.data.repository.GitHubRepoRepositoryImpl;
import com.rojer_ko.homework.lesson_4.domain.repository.GitHubRepoRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    GitHubRepoRepository provideGitHubRepoRepository(GitHubRepoApi api){
        return new GitHubRepoRepositoryImpl(api);
    }
}
