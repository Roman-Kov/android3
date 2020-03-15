package com.rojer_ko.homework.lesson_4.di.components;

import androidx.appcompat.app.AppCompatActivity;

import com.rojer_ko.homework.lesson_4.di.ActivityScope;
import com.rojer_ko.homework.lesson_4.di.modules.GitHubRepoActivityModule;
import com.rojer_ko.homework.lesson_4.presentation.GitHubRepoActivity;

import dagger.BindsInstance;
import dagger.Component;

@Component(
        modules = GitHubRepoActivityModule.class,
        dependencies = AppComponent.class
)

@ActivityScope
public interface GitHubRepoActivityComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder setActivity(AppCompatActivity activity);
        Builder setDependencies(AppComponent dependencies);
        GitHubRepoActivityComponent build();
    }

    void inject(GitHubRepoActivity activity);
}
