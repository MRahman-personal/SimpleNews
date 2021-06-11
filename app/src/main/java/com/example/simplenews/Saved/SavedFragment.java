package com.example.simplenews.Saved;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.simplenews.Database.ArticleEntity;
import com.example.simplenews.UI.EntityRecyclerViewAdapter;
import com.example.simplenews.R;
import java.util.ArrayList;
import java.util.List;

public class SavedFragment extends Fragment {

    private SavedViewModel mViewModel;
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recyclerView;
    private EntityRecyclerViewAdapter recyclerViewAdapter;
    private final List<ArticleEntity> articles = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        recyclerView = view.findViewById(R.id.fragment_recyclerview);
        recyclerViewAdapter = new EntityRecyclerViewAdapter(getContext(), articles);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
        swipeContainer = view.findViewById(R.id.swipeContainer);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Application application = getActivity().getApplication();
        mViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(SavedViewModel.class);
        mViewModel.getStories().observe(getViewLifecycleOwner(), storyListUpdateObserver);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.loadStories();
                swipeContainer.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.loadStories();
    }

    Observer<List<ArticleEntity>> storyListUpdateObserver = new Observer<List<ArticleEntity>>() {
        @Override
        public void onChanged(List<ArticleEntity> observableArticles) {
            recyclerViewAdapter.setItems(observableArticles);
        }
    };

}