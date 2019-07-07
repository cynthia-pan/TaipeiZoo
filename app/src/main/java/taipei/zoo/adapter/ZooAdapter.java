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
import taipei.zoo.data.ZooData;
import taipei.zoo.fragment.ZooDetailFragment;

public class ZooAdapter extends RecyclerView.Adapter<ZooAdapter.ViewHolder> {

    private Context mContext;
    private List<ZooData> mData;

    public ZooAdapter(Context context, List<ZooData> data) {
        this.mContext = context;
        this.mData = data;
    }

    public void updateList(List<ZooData> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_zoo, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.layoutTitle = view.findViewById(R.id.layoutTitle);
        holder.ivZooThumbnail = view.findViewById(R.id.ivZooThumbnail);
        holder.tvZooName = view.findViewById(R.id.tvZooName);
        holder.tvZooInfo = view.findViewById(R.id.tvZooInfo);
        holder.tvZooMemo = view.findViewById(R.id.tvZooMemo);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ZooData zoo = mData.get(position);

        holder.layoutTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) mContext;
                ZooDetailFragment fragment = new ZooDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("index", position);
                fragment.setArguments(bundle);
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right);
                ft.replace(R.id.content_fragment, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        holder.tvZooName.setText(zoo.zooName);
        holder.tvZooInfo.setText(zoo.zooInfo);
        if (zoo.zooMemo.equals("")) {
            holder.tvZooMemo.setText(R.string.default_memo);
        } else {
            holder.tvZooMemo.setText(zoo.zooMemo);
        }
        Glide.with(mContext)
                .load(zoo.zooPicUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivZooThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout layoutTitle;
        public ImageView ivZooThumbnail;
        public TextView tvZooName;
        public TextView tvZooInfo;
        public TextView tvZooMemo;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}