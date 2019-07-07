package taipei.zoo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import taipei.zoo.R;
import taipei.zoo.data.PlantData;
import taipei.zoo.fragment.PlantFragment;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {

    private Context mContext;
    private List<PlantData> mData;
    private Integer zooIndex;

    public PlantAdapter(Context context, List<PlantData> data, Integer index) {
        this.mContext = context;
        this.mData = data;
        this.zooIndex = index;
    }

    public void updateList(List<PlantData> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_plant, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.layoutTitle = view.findViewById(R.id.layoutTitle);
        holder.ivPlantThumbnail = view.findViewById(R.id.ivPlantThumbnail);
        holder.tvPlantName = view.findViewById(R.id.tvPlantName);
        holder.tvPlantAlsoKnown = view.findViewById(R.id.tvPlantAlsoKnown);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.layoutTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) mContext;
                PlantFragment fragment = new PlantFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("indexZoo", zooIndex);
                bundle.putInt("indexPlant", position);
                fragment.setArguments(bundle);
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right);
                ft.replace(R.id.content_fragment, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        PlantData plant = mData.get(position);
        holder.tvPlantName.setText(plant.nameCn);
        holder.tvPlantAlsoKnown.setText(plant.alsoKnown);
        Glide.with(mContext)
                .load(plant.picUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivPlantThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout layoutTitle;
        public ImageView ivPlantThumbnail;
        public TextView tvPlantName;
        public TextView tvPlantAlsoKnown;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}