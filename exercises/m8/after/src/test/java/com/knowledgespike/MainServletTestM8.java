package com.knowledgespike;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class MainServletTestM8 {

    static String URL = "http://localhost:8081/myblog";

    @Test
    public void test_that_when_requesting_the_home_page_the_counter_is_set() throws Throwable {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/home", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

        var elem = doc.getElementById("hitcount");
        var text = elem.text();

        assertThat(text).doesNotContain("Disabled");
    }


}
