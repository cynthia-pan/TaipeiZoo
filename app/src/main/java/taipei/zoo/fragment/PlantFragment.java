package taipei.zoo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import taipei.zoo.R;
import taipei.zoo.data.PlantData;
import taipei.zoo.data.StaticData;

public class PlantFragment extends Fragment {

    PlantData mPlantData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant, container, false);

        Bundle bundle = this.getArguments();
        Integer indexZoo = bundle.getInt("indexZoo");
        Integer indexPlant = bundle.getInt("indexPlant");
        mPlantData = StaticData.get().list.get(indexZoo).zooPlants.get(indexPlant);

        ImageView ivPlantThumbnail = view.findViewById(R.id.ivPlantThumbnail);
        Glide.with(getActivity())
                .load(mPlantData.picUrl)
                .into(ivPlantThumbnail);
        TextView tvPlantNameCn = view.findViewById(R.id.tvPlantNameCn);
        tvPlantNameCn.setText(mPlantData.nameCn);
        TextView tvPlantNameEn = view.findViewById(R.id.tvPlantNameEn);
        tvPlantNameEn.setText(mPlantData.nameEn);
        TextView tvPlantAlsoKnown = view.findViewById(R.id.tvPlantAlsoKnown);
        tvPlantAlsoKnown.setText(mPlantData.alsoKnown);
        TextView tvPlantBrief = view.findViewById(R.id.tvPlantBrief);
        tvPlantBrief.setText(mPlantData.brief);
        TextView tvPlantFeature = view.findViewById(R.id.tvPlantFeature);
        tvPlantFeature.setText(mPlantData.feature);
        TextView tvPlantFunctions = view.findViewById(R.id.tvPlantFunctions);
        String strFunc = "";
        for (String s : mPlantData.functions) {
            strFunc += s + "\n";
        }
        tvPlantFunctions.setText(strFunc);
        TextView tvPlantUpdate = view.findViewById(R.id.tvPlantUpdate);
        tvPlantUpdate.setText(mPlantData.lastUpdate);

        return view;
    }

    @Override
    public void onResume() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mPlantData.nameCn);
        super.onResume();
    }
}