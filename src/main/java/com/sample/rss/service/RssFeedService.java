package com.sample.rss.service;

import com.sample.rss.entity.RssFeed;
import com.sample.rss.repository.RssFeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RssFeedService {

    @Autowired
    private RssFeedRepository rssFeedRepository;

    public List<RssFeed> getLast10RssFeed() {
        return rssFeedRepository.findTop10ByOrderByPublishedDateDesc();
    }
}
