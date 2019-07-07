package taipei.zoo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import taipei.zoo.R;
import taipei.zoo.adapter.ZooAdapter;
import taipei.zoo.data.StaticData;
import taipei.zoo.data.ZooData;
import taipei.zoo.task.ZooAsyncTask;

public class ZooFragment extends Fragment {

    public static List<ZooData> mZooData;
    ZooAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zoo, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        mAdapter = new ZooAdapter(getActivity(), StaticData.get().list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL));

        if (StaticData.get().list.size() == 0) {
            ZooAsyncTask zooTask = new ZooAsyncTask(mAdapter, mZooData);
            zooTask.execute();
        }
        return view;
    }

    @Override
    public void onResume() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.zoo_title);
        super.onResume();
    }
}