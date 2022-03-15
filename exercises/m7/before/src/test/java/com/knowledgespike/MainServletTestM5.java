package com.knowledgespike;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class MainServletTestM5 {

    static String URL="http://localhost:8081/myblog";


    @Test
    public void test_that_css_is_reachable_even_for_nested_urls() throws Throwable {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/foo/bar/app.do", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

        var elem= doc.getElementsByTag("link").first();
        var link = elem.attr("href");

        assertThat(link).startsWith("/myblog");
    }
}
