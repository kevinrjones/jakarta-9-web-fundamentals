package com.knowledgespike;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class MainServletTestM9 {

    static String URL = "http://localhost:8081/myblog";

    @Test
    public void test_that_when_not_logged_in_then_the_colors_are_not_set() throws Throwable {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/foo/bar/app.do", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

        var elem = doc.getElementById("navbarDropdown").siblingElements().first();
        var text = elem.text();

        assertThat(text)
                .withFailMessage("==> The user is not logged in")
                .contains("No user logged in");
    }

    @Test
    public void test_that_when_logging_in_the_correct_form_is_shown() throws Throwable {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/showlogin.do", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

        Element elem = null;
        try {
            elem = doc.getElementsByClass("login-form").first();
        } catch (Exception e) {
        }
        assertThat(elem)
                .withFailMessage("==> The login form has not been shown")
                .isNotNull();

    }

    @Test
    public void test_that_when_logging_in_with_incorrect_values_the_showlogin_page_is_reshown() throws Throwable {

        String params = getInvalidPostParameters();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(String.format("%s/login.do", URL)))
                .POST(HttpRequest.BodyPublishers.ofString(params))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        assertThat(response)
                .withFailMessage("==> The login page has not been re-shown")
                .isNotNull();

        assertThat(response.statusCode())
                .withFailMessage("==> The login page has not been re-shown")
                .isEqualTo(302);

        HttpHeaders headers = response.headers();
        var header = headers.firstValue("Location");
        assertThat(header)
                .withFailMessage("==> The login page has not been re-shown")
                .contains("showlogin.do");
    }

    @Test
    public void test_that_when_logging_in_with_correct_values_the_index_page_is_reshown() throws Throwable {

        String params = getValidPostParameters();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(String.format("%s/login.do", URL)))
                .POST(HttpRequest.BodyPublishers.ofString(params))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(302);

        HttpHeaders headers = response.headers();
        var header = headers.firstValue("Location");
        assertThat(header.isPresent())
                .withFailMessage("==> The index page has not been reshown")
                .isTrue();
        assertThat(header.get())
                .withFailMessage("==> The index page has not been reshown")
                .contains("home");
    }

    @Test
    public void test_that_when_logging_in_with_correct_values_the_colours_are_shown() throws Throwable {

        String params = getValidPostParameters();

        CookieManager cm = new CookieManager();
        CookieHandler.setDefault(cm);

        var client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .cookieHandler(CookieHandler.getDefault())
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(String.format("%s/login.do", URL)))
                .POST(HttpRequest.BodyPublishers.ofString(params))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        Document doc = Jsoup.parse(response.body());

        Element elem = null;
        try {
            elem = doc.getElementById("navbarDropdown").siblingElements().first();
        } catch (Exception e) {

        }

        assertThat(elem)
                .withFailMessage("==> Is the user logged in and did you add 'red' to the dropdown")
                .isNotNull();

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

    private String getInvalidPostParameters() {
        var values = new HashMap<String, String>() {{
            put("username", "Kevin");
            put("password", "NotKevin");
        }};

        return values
                .entrySet()
                .stream()
                .map(entry -> Stream.of(
                                URLEncoder.encode(entry.getKey(), UTF_8),
                                URLEncoder.encode(entry.getValue(), UTF_8))
                        .collect(Collectors.joining("="))
                ).collect(Collectors.joining("&"));
    }

    private String getValidPostParameters() {
        var values = new HashMap<String, String>() {{
            put("username", "Kevin");
            put("password", "Kevin");
        }};

        return values
                .entrySet()
                .stream()
                .map(entry -> Stream.of(
                                URLEncoder.encode(entry.getKey(), UTF_8),
                                URLEncoder.encode(entry.getValue(), UTF_8))
                        .collect(Collectors.joining("="))
                ).collect(Collectors.joining("&"));
    }

}
