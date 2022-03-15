package com.knowledgespike;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class MainServletTestM6 {

    static String URL="http://localhost:8081/myblog";

    @Test
    public void test_that_when_logging_in_then_the_colors_are_not_set() throws Throwable {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/foo/bar/app.do", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

        var elem= doc.getElementById("navbarDropdown").siblingElements().first();
        var text = elem.text();

        assertThat(text).contains("No user logged in");
    }

        @Test
    public void test_that_when_logging_in_then_the_colors_are_set() throws Throwable {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/showlogin.do", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

            var elem= doc.getElementById("navbarDropdown").siblingElements().first();
            var text = elem.text();

            assertThat(text).contains("Red");
            assertThat(text).contains("Green");
            assertThat(text).contains("Default");
    }
}
