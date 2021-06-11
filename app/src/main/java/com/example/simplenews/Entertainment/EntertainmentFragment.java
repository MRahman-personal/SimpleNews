package com.example.simplenews.Entertainment;

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

import com.example.simplenews.Api.StoryResponse;
import com.example.simplenews.R;
import com.example.simplenews.UI.RecyclerViewAdapter;


public class EntertainmentFragment extends Fragment {

    private EntertainmentViewModel mViewModel;
    private SwipeRefreshLayout swipeContainer;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    StoryResponse response = new StoryResponse();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        recyclerView = view.findViewById(R.id.fragment_recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),response);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
        swipeContainer = view.findViewById(R.id.swipeContainer);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EntertainmentViewModel.class);
        mViewModel.getStories().observe(getViewLifecycleOwner(), storyListUpdateObserver);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.loadStories();
                swipeContainer.setRefreshing(false);
            }
        });
    }

    Observer<StoryResponse> storyListUpdateObserver = new Observer<StoryResponse>() {
        @Override
        public void onChanged(StoryResponse storyResponse) {
            recyclerViewAdapter.setItems(storyResponse);
        }
    };

}