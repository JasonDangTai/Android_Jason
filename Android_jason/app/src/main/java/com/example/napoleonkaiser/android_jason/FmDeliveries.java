package com.example.napoleonkaiser.android_jason;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by napoleonkaiser on 14/06/2017.
 */

public class FmDeliveries extends Fragment  {
    private View rootView;

    private List<String> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;

    CommunicationFragment giaotiepFragment;
    public interface CommunicationFragment{

        public void GuidulieuFramentExplore(List<String> dulieu);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        giaotiepFragment = (CommunicationFragment) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    private void setUpView() {

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

    }

    private void fakeData() {

        Random random = new Random();
        for (int i = 0; i < 10; i++)
            mList.add(String.valueOf(random.nextInt(100)));

    }
    private void setUpRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        AdapterRecyclerView listAdapter = new AdapterRecyclerView(mList);
        mRecyclerView.setAdapter(listAdapter);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

         setUpView();
         fakeData();
         setUpRecyclerView();

        MainActivity activity = (MainActivity) getActivity();
        activity.getToolbar().setTitle(R.string.app_name);

        giaotiepFragment.GuidulieuFramentExplore(mList);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView =inflater.inflate(R.layout.fm_deliveries, container, false);
        return rootView;

    }


}
