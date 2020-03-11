package com.rojer_ko.homework.lesson_4.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rojer_ko.homework.R;
import com.rojer_ko.homework.lesson_4.domain.model.GitHubRepo;
import java.util.ArrayList;
import java.util.List;

public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoAdapter.ViewHolder> {

    private ArrayList<GitHubRepo> gitHubReposList = new ArrayList<>();

    void setList(List<GitHubRepo> data) {
        gitHubReposList.clear();
        gitHubReposList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_git_hub_repo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(gitHubReposList.get(position));
    }

    @Override
    public int getItemCount() {
        return gitHubReposList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView gitHubRepo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gitHubRepo = itemView.findViewById(R.id.repoTextView);
        }

        void bind(GitHubRepo item) {
            gitHubRepo.setText(item.fullName);
        }
    }
}
