package com.veontomo.indexer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Downloader {
    private static final Logger LOGGER = Logger.getLogger("Downloader");

    public Downloader() {
    }

    public void download() {
        Document doc = this.retrieve("https://www.mit.gov.it/mit/site.php?p=normativa&o=vd&id=1&id_cat=&id_dett=0");
        final Elements newsHeadlines = doc.select("div.voceNormativa ul li a");
        final List<String> urls = new ArrayList<>(300);
        for (Element headline : newsHeadlines) {
            urls.add(headline.absUrl("href"));
        }
        final List<Article> articles = urls.stream()
            .limit(5)
            .map(this::buildArticle)
            .collect(Collectors.toList());
        articles.forEach(a -> LOGGER.info(a.toString()));

    }

    private Article buildArticle(String url) {
        final Article result = new Article();
        result.setUrl(url);
        final Document doc = this.retrieve(url);
        final Element title = doc.selectFirst("div.indiceNormativa");
        result.setTitle(title.text());
        final Element content = doc.selectFirst("div.testoNormativa");
        final List<SubArticle> subarticles = this.buildSubarticles(content.text());
        result.setSubarticles(subarticles);
        return result;

    }

    private List<SubArticle> buildSubarticles(String content) {
        String[] split = content.split("<br>");
        final List<String> parts = Arrays.asList(split);
        return parts.stream()
            .filter(s -> !Objects.isNull(s))
            .map(this::buildSubarticle)
            .collect(Collectors.toList());

    }

    private Document retrieve(String url) {
        try {
            return Jsoup.connect(url)
                .get();
        } catch (IOException e) {
            LOGGER.warning("Failed to retreive " + url);
        }
        return null;

    }

    private SubArticle buildSubarticle(String text) {
        final SubArticle result = new SubArticle();
        result.setContent(text);
        return result;
    }

}
