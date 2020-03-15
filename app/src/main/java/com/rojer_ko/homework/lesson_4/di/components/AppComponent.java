package com.rojer_ko.homework.lesson_4.di.components;

import com.rojer_ko.homework.lesson_4.di.modules.DataModule;
import com.rojer_ko.homework.lesson_4.di.modules.NetworkModule;
import com.rojer_ko.homework.lesson_4.domain.repository.GitHubRepoRepository;
import javax.inject.Singleton;
import dagger.Component;

@Component (
        modules = {DataModule.class,
        NetworkModule.class}

)
@Singleton
public interface AppComponent {
    GitHubRepoRepository getGitHubRepoRepository();
}
