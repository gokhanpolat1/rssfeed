package com.sample.rss.service;

import com.sample.rss.entity.RssFeed;
import com.sample.rss.repository.RssFeedRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RssFeedConsumerServiceUnitTest {

    @Mock
    RssFeedRepository rssFeedRepository;

    @InjectMocks
    RssFeedConsumerService rssFeedConsumerService;

    private static String URL = "http://feeds.bbci.co.uk/news/world/rss.xml";

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(rssFeedConsumerService, "url", URL);
    }

    @Test
    public void shouldCallRepositoryMethods_whenConsumingRss() {
        // given
        List<RssFeed> rssFeedList = new LinkedList<>();

        // when
        rssFeedConsumerService.consumeRss();

        //then
        verify(rssFeedRepository, times(1)).deleteAll();
        verify(rssFeedRepository, times(1)).saveAll(refEq(rssFeedList));
    }
}