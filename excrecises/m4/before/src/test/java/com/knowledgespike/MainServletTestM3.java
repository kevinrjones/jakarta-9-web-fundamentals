package com.knowledgespike;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class MainServletTestM3 {

    static String URL="http://localhost:8081/myblog";

    @Test
    public void test_that_home_url_is_reachable() throws Throwable {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/home", URL)))
                .GET() // GET is default
                .build();

        HttpResponse<Void> response = client.send(request,
                HttpResponse.BodyHandlers.discarding());

        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    public void test_that_do_urls_are_reachable() throws Throwable {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/foo.do", URL)))
                .GET() // GET is default
                .build();

        HttpResponse<Void> response = client.send(request,
                HttpResponse.BodyHandlers.discarding());

        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    public void test_that_my_blog_returns_the_topics() throws Throwable {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/foo.do?name=Kevin", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

        var elem= doc.getElementById("topics");
        assertThat(elem.text()).contains("Servlets");
        assertThat(elem.text()).contains("JSP");
        assertThat(elem.text()).contains("Security");
        assertThat(elem.text()).contains("All");
    }

    @Test
    public void test_that_my_blog_returns_the_blog_entries() throws Throwable {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/foo.do?name=Kevin", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

        var elem= doc.getElementById("newsitems");
        assertThat(elem.text()).contains("Creating Servlets");
        assertThat(elem.text()).contains("Servlet Context");
        assertThat(elem.text()).contains("Expression Language");
        assertThat(elem.text()).contains("OIDC");
        assertThat(elem.text()).contains("OAuth");
    }
}
