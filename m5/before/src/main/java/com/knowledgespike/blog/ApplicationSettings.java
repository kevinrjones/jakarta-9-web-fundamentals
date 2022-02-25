package com.knowledgespike.blog;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationSettings {
    private final TopicList topicList;
    private final NewsItems newsItems;

    public static final String topic = "topic";
    public static final String newsitem = "newsitem";
    public static final String all = "all";


    public ApplicationSettings() {
        topicList = new TopicList();
        newsItems = new NewsItems();
    }

    public TopicList getTopicList() {
        return topicList;
    }

    public NewsItems getNewsItems() {
        return newsItems;
    }

    public List<NewsItem> getNewsForTopic(int type) {

        var itemStream = getNewsItems()
                .getItems()
                .stream()
                .filter(t -> ((t.getTopic() == type) || type == 0));

        var items = itemStream.collect(Collectors.toList());
        return items;
    }

    public List<NewsItem> setupData(String type, String detail) {


        if (type.equals(topic)) {
            var topic = getTopicList()
                    .getTopics().stream().filter(t -> t.getTitle().equalsIgnoreCase(detail))
                    .findFirst()
                    .orElseGet(() -> new Topic(all, "", 0));


            return getNewsForTopic(topic.getId());
        } else {
            return getNewsForTopic(0);
        }
    }
}
