package com.veontomo.indexer;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {
    private static final long serialVersionUID = 675841823968848523L;
    private String title;
    private List<SubArticle> subarticles;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubArticle> getSubarticles() {
        return subarticles;
    }

    public void setSubarticles(List<SubArticle> subarticles) {
        this.subarticles = subarticles;
    }

    @Override
    public String toString() {
        return "Article [title=" + title + ", subarticles=" + subarticles + ", url=" + url + "]";
    }

}
