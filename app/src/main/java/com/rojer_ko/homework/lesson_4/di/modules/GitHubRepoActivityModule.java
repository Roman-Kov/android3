package com.rojer_ko.homework.lesson_4.di.modules;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.rojer_ko.homework.lesson_4.domain.usecase.GitHubRepoInteractor;
import com.rojer_ko.homework.lesson_4.presentation.GitHubViewModel;
import com.rojer_ko.homework.lesson_4.presentation.GitHubViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class GitHubRepoActivityModule {

    @Provides
    GitHubViewModel provideGitHubViewModel(AppCompatActivity activity, GitHubViewModelFactory factory){
        return ViewModelProviders.of(activity, factory).get(GitHubViewModel.class);
    }

    @Provides
    GitHubViewModelFactory provideGitHubViewModelFactory(GitHubRepoInteractor interactor){
        return new GitHubViewModelFactory(interactor);
    }
}
