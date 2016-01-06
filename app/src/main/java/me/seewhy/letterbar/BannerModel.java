package me.seewhy.letterbar;

/**
 * Created by BG204119 on 2015/12/30.
 */
public class BannerModel implements Comparable<BannerModel>{
    String name;
    String url;

    public BannerModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public int compareTo(BannerModel another) {
        return HanziToPinyin.getFirstPinYinChar(name).compareTo(HanziToPinyin.getFirstPinYinChar(another.name));
    }
}
