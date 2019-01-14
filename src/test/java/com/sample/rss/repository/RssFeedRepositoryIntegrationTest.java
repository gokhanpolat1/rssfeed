package com.sample.rss.repository;

import com.sample.rss.entity.RssFeed;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RssFeedRepositoryIntegrationTest {

    @Autowired
    private RssFeedRepository rssFeedRepository;

    @Test
    public void givenRssFeeds_whenGetLast10RssFeeds_thenReturn10RssFeeds() {
        // given
        saveRssFeedsToDB(15);

        // when
        List<RssFeed> rssFeeds = rssFeedRepository.findTop10ByOrderByPublishedDateDesc();

        // then
        Assert.assertEquals(10, rssFeeds.size());
    }

    private void saveRssFeedsToDB(int count) {
        List<RssFeed> rssFeeds = new LinkedList<>();
        for (int i = 1; i <= count; i++) {
            RssFeed rssFeed = new RssFeed();
            rssFeed.setLink("Link" + i);
            rssFeed.setPublishedDate(new Date());
            rssFeed.setDescription("Description" + i);
            rssFeed.setTitle("Title" + i);
            rssFeeds.add(rssFeed);
        }
        rssFeedRepository.saveAll(rssFeeds);
    }
}