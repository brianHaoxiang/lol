package com.haoxiaz.lol.model;

/**
 * Created by haoxiaz on 4/22/16.
 */
public class Champion {
    private long id;
    private String title;
    private String name;
    private ChampionImage image;
    private String blurb;
    private String key;

    public long getId() {
        return id;
    }

    public ChampionImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getBlurb() {
        return blurb;
    }

    public static class ChampionImage {
        private int w;
        private String full;
        private String sprite;
        private String group;
        private int h;
        private int y;
        private int x;

        public String getFull() {
            return full;
        }
    }
}
