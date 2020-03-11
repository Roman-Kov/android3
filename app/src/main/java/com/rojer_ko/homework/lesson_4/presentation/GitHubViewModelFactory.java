package com.rojer_ko.homework.lesson_4.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rojer_ko.homework.lesson_4.domain.usecase.GitHubRepoInteractor;

public class GitHubViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private GitHubRepoInteractor interactor;

    public GitHubViewModelFactory(GitHubRepoInteractor interactor) {
        this.interactor = interactor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == GitHubViewModel.class) {
            return (T) new GitHubViewModel(interactor);
        }
        return null;
    }
}
