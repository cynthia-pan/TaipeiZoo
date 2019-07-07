package taipei.zoo.task;

import android.os.AsyncTask;

import java.util.List;

import taipei.zoo.adapter.ZooAdapter;
import taipei.zoo.data.PlantData;
import taipei.zoo.data.StaticData;
import taipei.zoo.data.ZooData;

public class ZooAsyncTask extends AsyncTask<Void, List<ZooData>, List<ZooData>> {

    ZooAdapter mAdapter;
    List<ZooData> mZooData;

    public ZooAsyncTask(ZooAdapter adapter, List<ZooData> zooData) {
        mAdapter = adapter;
        mZooData = zooData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<ZooData> doInBackground(Void... v) {
        List<ZooData> zoo = GsonFetchZooJson.fetch();
        List<PlantData> plant = GsonFetchPlantJson.fetch();
        for (PlantData data : plant) {
            for (String loc : data.locations) {
                for (ZooData zz : zoo) {
                    if (zz.zooName.contains(loc) || loc.contains(zz.zooName)) {
                        zz.zooPlants.add(data);
                    }
                }
            }
        }
        StaticData.set(zoo);
        return zoo;
    }

    @Override
    protected void onPostExecute(List<ZooData> data) {
        mZooData = data;
        mAdapter.updateList(mZooData);
        mAdapter.notifyDataSetChanged();
        super.onPostExecute(data);
    }
}