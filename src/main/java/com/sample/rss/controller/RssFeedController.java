package com.sample.rss.controller;

import com.sample.rss.entity.RssFeed;
import com.sample.rss.service.RssFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
public class RssFeedController {

    @Autowired
    private RssFeedService rssFeedService;

    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<RssFeed> indexJson() {
        List<RssFeed> rssFeeds = rssFeedService.getLast10RssFeed();
        return rssFeeds;
    }
}