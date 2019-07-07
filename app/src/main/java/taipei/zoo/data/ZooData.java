package taipei.zoo.data;

import java.util.ArrayList;
import java.util.List;

public class ZooData {
    public String zooPicUrl;
    public String zooName;
    public String zooInfo;
    public String zooMemo;
    public String zooUrl;
    public List<PlantData> zooPlants = new ArrayList<>();

    public ZooData(String picUrl, String name, String content, String memo, String url) {
        this.zooPicUrl = picUrl;
        this.zooName = name;
        this.zooInfo = content;
        this.zooMemo = memo;
        this.zooUrl = url;
    }
}