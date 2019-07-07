package taipei.zoo.task;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import taipei.zoo.data.ZooData;

public class GsonFetchZooJson {

    static final String ZOO_URL = "https://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a";

    static class Item {
        String E_Pic_URL;
        String E_Geo;
        String E_Info;
        String E_no;
        String E_Category;
        String E_Name;
        String E_Memo;
        Integer _id;
        String E_URL;
    }

    public static class Result {
        Integer limit;
        Integer offset;
        Integer count;
        String sort;
        List<Item> results;
    }

    public static class ZooJson {
        Result result;
    }

    public static List<ZooData> fetch() {
        List<ZooData> list = new ArrayList<>();

        try {
            URL url = new URL(ZOO_URL);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            ZooJson zoo = new Gson().fromJson(reader, ZooJson.class);

            for (Item item : zoo.result.results) {
                list.add(new ZooData(
                        item.E_Pic_URL,
                        item.E_Name,
                        item.E_Info,
                        item.E_Memo,
                        item.E_URL));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}