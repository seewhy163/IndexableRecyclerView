package me.seewhy.letterbar;

/**
 * Created by BG204119 on 2015/12/30.
 */
public class ImageModel implements Comparable<ImageModel>{
    String name;
    String url;
    int resourceId;

    public ImageModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public ImageModel(String name, int resourceId) {
        this.name = name;
        this.resourceId = resourceId;
    }
    @Override
    public int compareTo(ImageModel another) {
        return HanziToPinyin.getFirstPinYinChar(name).compareTo(HanziToPinyin.getFirstPinYinChar(another.name));
    }
}
