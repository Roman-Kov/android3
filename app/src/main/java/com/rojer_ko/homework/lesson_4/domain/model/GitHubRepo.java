package com.rojer_ko.homework.lesson_4.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GitHubRepo implements Comparable<GitHubRepo>{

    @SerializedName("full_name")
    @Expose
    public String fullName;

    @Override
    public int compareTo(GitHubRepo gitHubRepo) {
        return fullName.compareTo(gitHubRepo.fullName);
    }
}