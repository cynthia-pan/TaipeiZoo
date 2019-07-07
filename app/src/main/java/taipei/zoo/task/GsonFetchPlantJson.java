package taipei.zoo.task;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import taipei.zoo.data.PlantData;

public class GsonFetchPlantJson {

    static final String Plant_URL = "https://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=f18de02f-b6c9-47c0-8cda-50efad621c14";

    static class Item {
        String F_Name_Latin;
        String F_pdf02_ALT;
        String F_Location;
        String F_pdf01_ALT;
        String F_Summary;
        String F_Pic01_URL;
        String F_pdf02_URL;
        String F_Pic02_URL;
        String F_Keywords;
        String F_Code;
        String F_Geo;
        String F_Pic03_URL;
        String F_Voice01_ALT;
        String F_AlsoKnown;
        String F_Voice02_ALT;
        String F_Name_Ch;
        String F_Pic04_ALT;
        String F_Name_En;
        String F_Brief;
        String F_Pic04_URL;
        String F_Voice01_URL;
        String F_Feature;
        String F_Pic02_ALT;
        String F_Family;
        String F_Voice03_ALT;
        String F_Voice02_URL;
        String F_Pic03_ALT;
        String F_Pic01_ALT;
        String F_CID;
        String F_pdf01_URL;
        String F_Vedio_URL;
        String F_Genus;
        @SerializedName("F_Function＆Application")
        String F_Function_Application;
        String F_Voice03_URL;
        String F_Update;
        Integer _id;
    }

    public static class Result {
        Integer limit;
        Integer offset;
        Integer count;
        String sort;
        List<Item> results;
    }

    public static class PlantJson {
        Result result;
    }

    public static List<PlantData> fetch() {
        List<PlantData> list = new ArrayList<>();

        try {
            URL url = new URL(Plant_URL);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            PlantJson plantJson = new Gson().fromJson(reader, PlantJson.class);

            int size = plantJson.result.results.size();
            for (int i = 0; i < size; ) {
                PlantData plant = new PlantData();
                plant.nameCn = plantJson.result.results.get(i).F_Name_Ch;
                plant.nameEn = plantJson.result.results.get(i).F_Name_En;
                plant.alsoKnown = plantJson.result.results.get(i).F_AlsoKnown;
                plant.brief = plantJson.result.results.get(i).F_Brief;
                plant.feature = plantJson.result.results.get(i).F_Feature;
                String location = plantJson.result.results.get(i).F_Location;
                for (String loc : location.split("；")) {
                    plant.locations.add(loc);
                }
                plant.functions.add(plantJson.result.results.get(i).F_Function_Application);
                plant.picUrl = plantJson.result.results.get(i).F_Pic01_URL;
                plant.lastUpdate = plantJson.result.results.get(i).F_Update;

                i++;
                for (; i < size; i++) {
                    String brief = plantJson.result.results.get(i).F_Brief;
                    if (brief != null && !brief.equals("")) {
                        break;
                    }
                    plant.functions.add(plantJson.result.results.get(i).F_Name_Ch);
                    plant.picUrl = plantJson.result.results.get(i).F_AlsoKnown;
                    plant.lastUpdate = plantJson.result.results.get(i).F_Pic04_URL;
                }
                list.add(plant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}