package taipei.zoo.data;

import java.util.ArrayList;
import java.util.List;

public class StaticData {
    static private StaticData staticData = null;
    public List<ZooData> list = new ArrayList<>();

    public static StaticData get() {
        if (staticData == null) {
            staticData = new StaticData();
        }
        return staticData;
    }

    public static void set(List<ZooData> zoo) {
        staticData.get().list = zoo;
    }
}
