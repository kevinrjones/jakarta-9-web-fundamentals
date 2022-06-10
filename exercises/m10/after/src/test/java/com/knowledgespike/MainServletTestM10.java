package com.knowledgespike;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class MainServletTestM10 {

    static String URL = "http://localhost:8081/myblog";

    @Test
    public void test_that_the_default_cookie_color_is_white() throws Throwable {
        CookieManager cm = new CookieManager();
        CookieHandler.setDefault(cm);

        var client  = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .cookieHandler(CookieHandler.getDefault())
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/color.do", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode())
                .withFailMessage("==> The color page is not available")
                .isEqualTo(200);

        var cookies = cm.getCookieStore().getCookies();
        var cookieValue = "";
        for(HttpCookie cookie:cookies) {
            if(cookie.getName().equals("color")) cookieValue = cookie.getValue();
        }

        assertThat(cookieValue)
                .withFailMessage("==> The cookie is not set")
                .isEqualTo("white");
    }

    @Test
    public void test_that_the_seeting_the_color_to_blue_sets_the_cookie_value_to_blue() throws Throwable {
        CookieManager cm = new CookieManager();
        CookieHandler.setDefault(cm);

        var client  = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .cookieHandler(CookieHandler.getDefault())
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/color.do?color=blue", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode())
                .withFailMessage("==> The color page is not available")
                .isEqualTo(200);

        var cookies = cm.getCookieStore().getCookies();
        var cookieValue = "";
        for(HttpCookie cookie:cookies) {
            if(cookie.getName().equals("color")) cookieValue = cookie.getValue();
        }

        assertThat(cookieValue)
                .withFailMessage("==> The cookie is not set")
                .isEqualTo("blue");
    }
}
