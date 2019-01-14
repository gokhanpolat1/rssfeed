package com.sample.rss.service;

import com.sample.rss.entity.RssFeed;
import com.sample.rss.repository.RssFeedRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@Service
public class RssFeedConsumerService {

    @Autowired
    private RssFeedRepository rssFeedRepository;

    @Value("${rss.feed.url}")
    private String url;

    @Transactional
    public void consumeRss() {
        List<SyndEntry> syndEntryList = readRss(url);
        List<RssFeed> rssFeedList = convertSyndEntryToRssFeed(syndEntryList);
        rssFeedRepository.deleteAll();
        rssFeedRepository.saveAll(rssFeedList);
    }

    private List<SyndEntry> readRss(String url) {
        try {
            XmlReader reader = new XmlReader(new URL(url));
            SyndFeed feed = new SyndFeedInput().build(reader);
            return feed.getEntries();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<RssFeed> convertSyndEntryToRssFeed(List<SyndEntry> syndEntryList) {
        List<RssFeed> rssFeedList = new LinkedList<>();
        syndEntryList.stream().forEach(syndEntry -> {
            RssFeed rssFeed = new RssFeed();
            rssFeed.setLink(syndEntry.getLink());
            rssFeed.setTitle(syndEntry.getTitle().toUpperCase());
            rssFeed.setDescription(syndEntry.getDescription().getValue());
            rssFeed.setPublishedDate(syndEntry.getPublishedDate());
            rssFeedList.add(rssFeed);
        });
        return rssFeedList;
    }
}
