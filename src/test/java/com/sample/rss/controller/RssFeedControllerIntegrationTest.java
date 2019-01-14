package com.sample.rss.controller;

import com.sample.rss.entity.RssFeed;
import com.sample.rss.service.RssFeedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RssFeedController.class)
public class RssFeedControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RssFeedService rssFeedService;

    @Test
    public void givenRssFeeds_whenGetRssFeeds_thenReturnJsonArray()
            throws Exception {
        // given
        RssFeed rssFeed = new RssFeed();
        rssFeed.setDescription("A Sample Description");
        List<RssFeed> rssFeeds = Arrays.asList(rssFeed);
        Mockito.when(rssFeedService.getLast10RssFeed()).thenReturn(rssFeeds);

        // when
        mockMvc.perform(get("/index")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].description", is(rssFeed.getDescription())));
    }
}