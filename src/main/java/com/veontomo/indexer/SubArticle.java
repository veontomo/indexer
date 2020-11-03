package com.veontomo.indexer;

import java.io.Serializable;

public class SubArticle implements Serializable {

    private static final long serialVersionUID = 8400112299349747834L;
    
    private String title;
    
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SubArticle [title=" + title + ", content=" + content + "]";
    }
    

}
