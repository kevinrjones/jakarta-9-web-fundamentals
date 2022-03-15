package com.knowledgespike;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class MainServletTestM12 {

    static String URL = "http://localhost:8081/myblog";

    @Test
    public void test_that_the_default_cookie_color_is_white() throws Throwable {
        var client = HttpClient.newBuilder()
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/simple", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

        assertThat(doc.body().text()).contains("Processing task in thread id:[");
    }

}
