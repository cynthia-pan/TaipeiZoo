package taipei.zoo.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import taipei.zoo.R;
import taipei.zoo.adapter.PlantAdapter;
import taipei.zoo.data.StaticData;
import taipei.zoo.data.ZooData;

public class ZooDetailFragment extends Fragment {

    PlantAdapter mAdapter;
    ZooData mZooData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zoo_detail, container, false);
        RecyclerView rvDetail = view.findViewById(R.id.rvDetail);

        Bundle bundle = this.getArguments();
        Integer index = bundle.getInt("index");
        mZooData = StaticData.get().list.get(index);

        ImageView ivZooThumbnail = view.findViewById(R.id.ivZooThumbnail);
        Glide.with(getActivity())
                .load(mZooData.zooPicUrl)
                .into(ivZooThumbnail);
        TextView tvZooInfo = view.findViewById(R.id.tvZooInfo);
        tvZooInfo.setText(mZooData.zooInfo);
        TextView tvZooMemo = view.findViewById(R.id.tvZooMemo);

        String memo =
                mZooData.zooMemo.equals("") ? getString(R.string.default_memo) : mZooData.zooMemo;
        tvZooMemo.setText(memo);
        TextView tvZooName = view.findViewById(R.id.tvZooName);
        tvZooName.setText(mZooData.zooName);
        TextView tvZooLink = view.findViewById(R.id.tvZooLink);
        tvZooLink.setText(Html.fromHtml(
                "<a href=" + mZooData.zooUrl + ">" +
                        getString(R.string.open_browser) + "</a>"));
        tvZooLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mZooData.zooUrl));
                startActivity(i);
            }
        });

        mAdapter = new PlantAdapter(getActivity(), mZooData.zooPlants, index);

        rvDetail.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvDetail.setAdapter(mAdapter);
        rvDetail.addItemDecoration(
                new DividerItemDecoration(rvDetail.getContext(),
                        DividerItemDecoration.VERTICAL));

        return view;
    }

    @Override
    public void onResume() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mZooData.zooName);
        super.onResume();
    }
}