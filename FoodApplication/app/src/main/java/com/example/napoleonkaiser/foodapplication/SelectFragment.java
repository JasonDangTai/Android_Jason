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
import com.example.napoleonkaiser.foodapplication.adapter.SelectAdapter;
import com.example.napoleonkaiser.foodapplication.adapter.SelectAdapterRv2;
import com.example.napoleonkaiser.foodapplication.module.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by napoleonkaiser on 18/06/2017.
 */

public class SelectFragment extends Fragment{
    private View rootView;
    private List<Data> myData = new ArrayList<Data>() {};
    private RecyclerView mRecyclerView, rv2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_select, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv1);
        fakeData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setNestedScrollingEnabled(false);
        SelectAdapter myAdapter = new SelectAdapter(getActivity().getApplicationContext(),myData);
        mRecyclerView.setAdapter(myAdapter);


        rv2 = (RecyclerView) rootView.findViewById(R.id.rv2);
        fakeData();
        rv2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv2.setNestedScrollingEnabled(false);
        SelectAdapterRv2 myAdapterRv2 = new SelectAdapterRv2(getActivity().getApplicationContext(),myData);
        rv2.setAdapter(myAdapterRv2);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv3);
        fakeData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setNestedScrollingEnabled(false);
        SelectAdapter myAdapter1 = new SelectAdapter(getActivity().getApplicationContext(),myData);
        mRecyclerView.setAdapter(myAdapter1);
    }

    private void fakeData() {
        for (int i = 0; i < 10; i++) {
            Data data = new Data("Barbecue party tips for a truly amazing event", " Apirl 26", "http://weknowyourdreams.com/images/barbecue/barbecue-07.jpg");
            myData.add(data);
        }
    }

}
