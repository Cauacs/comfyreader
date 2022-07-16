package xyz.comfyreader.models;

import java.util.Date;
import java.util.List;


public record Feed(
        String title,
        String link,
        String description,
        String copyright,
        Date pubDate,
        List<FeedItem> items
) {}
