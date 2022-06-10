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

        assertThat(response.statusCode())
                .withFailMessage("==> Did you deploy the application")
                .isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

        var elem= doc.getElementById("navbarDropdown").siblingElements().first();
        var text = elem.text();

        assertThat(text)
                .withFailMessage("==> Is the user logged in by mistake")
                .contains("No user logged in");
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

            assertThat(text)
                    .withFailMessage("==> Is the user logged in and did you add 'red' to the dropdown")
                    .contains("Red");
            assertThat(text)
                    .withFailMessage("==> Is the user logged in and did you add 'green' to the dropdown")
                    .contains("Green");
            assertThat(text)
                    .withFailMessage("==> Is the user logged in and did you add 'default' to the dropdown")
                    .contains("Default");
    }
}
