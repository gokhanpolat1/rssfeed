package com.sample.rss.repository;

import com.sample.rss.entity.RssFeed;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RssFeedRepository extends CrudRepository<RssFeed, Long> {

    List<RssFeed> findTop10ByOrderByPublishedDateDesc();
}
