package com.example.napoleonkaiser.foodapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.napoleonkaiser.foodapplication.adapter.MyAdapter;
import com.example.napoleonkaiser.foodapplication.module.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by napoleonkaiser on 18/06/2017.
 */

public class StoreFragment extends Fragment {

    private View rootView;
    private List<Data> myData = new ArrayList<Data>() {};
    private RecyclerView mRecyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_store, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        fakeData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setNestedScrollingEnabled(false);
        MyAdapter myAdapter = new MyAdapter(getActivity().getApplicationContext(),myData);
        mRecyclerView.setAdapter(myAdapter);
    }

    private void fakeData() {
        for (int i = 0; i < 10; i++) {
            Data   data = new Data("Barbecue party tips for a truly amazing event"," Apirl 26", "http://weknowyourdreams.com/images/barbecue/barbecue-07.jpg");
            myData.add(data);
        }
    }
}
