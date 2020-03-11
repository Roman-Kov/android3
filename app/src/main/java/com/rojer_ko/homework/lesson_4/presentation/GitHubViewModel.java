package com.rojer_ko.homework.lesson_4.presentation;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import com.rojer_ko.homework.lesson_4.domain.model.GitHubRepo;
import com.rojer_ko.homework.lesson_4.domain.usecase.GitHubRepoInteractor;
import java.util.List;
import io.reactivex.disposables.CompositeDisposable;

public class GitHubViewModel extends ViewModel implements LifecycleObserver {

        private GitHubRepoInteractor interactor;
        private CompositeDisposable compositeDisposable = new CompositeDisposable();
        public MutableLiveData<List<GitHubRepo>> gitHubLiveData = new MutableLiveData<>();

        public GitHubViewModel(GitHubRepoInteractor interactor) {
            this.interactor = interactor;
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            compositeDisposable.add(
                    interactor.getRepos()
                            .subscribe(result -> gitHubLiveData.postValue(result),
                                    throwable -> System.out.println(throwable)
                            ));
        }

        @Override
        protected void onCleared() {
            compositeDisposable.clear();
            super.onCleared();
        }
}
