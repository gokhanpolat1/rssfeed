package com.sample.rss.service;

import com.sample.rss.repository.RssFeedRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RssFeedServiceUnitTest {

    @Mock
    RssFeedRepository rssFeedRepository;

    @InjectMocks
    RssFeedService rssFeedService;

    @Test
    public void shouldCallRepositoryMethod_whenFetchingRss() {
        // when
        rssFeedService.getLast10RssFeed();

        //then
        verify(rssFeedRepository, times(1)).findTop10ByOrderByPublishedDateDesc();
    }
}