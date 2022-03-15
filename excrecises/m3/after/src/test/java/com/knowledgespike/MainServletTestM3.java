package com.knowledgespike;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

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

public class MainServletTestM3 {

    static String URL="http://localhost:8081/myblog";

    @Test
    public void test_that_my_blog_website_is_reachable() throws Throwable {

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
    public void test_that_my_blog_returns_the_structured_output_when_the_query_string_contains_a_name() throws Throwable {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/foo.do?name=Kevin", URL)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

        var elem= doc.getElementsByTag("name").first();
        assertThat(elem.text()).isEqualTo("Hello, Kevin");

        elem= doc.getElementsByTag("product").first();
        assertThat(elem.text()).isEqualTo("Super Blog");

        elem= doc.getElementsByTag("connectionStr").first();
        assertThat(elem.text()).isEqualTo("My Connection String");
    }

    @Test
    public void test_that_my_blog_returns_the_correct_content_type_when_the_query_string_contains_a_name() throws Throwable {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/foo.do?name=Kevin", URL)))
                .GET()
                .build();

        var response = client.send(request,
                HttpResponse.BodyHandlers.discarding());

        assertThat(response.statusCode()).isEqualTo(200);

        HttpHeaders headers = response.headers();
        var header =headers.firstValue("content-type");

        assertThat(header.get()).contains("text/xml");
    }

    @Test
    public void test_that_a_post_can_be_sent_to_site() throws Throwable {
        String params = getPostParameters();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(String.format("%s/home", URL)))
                .POST(HttpRequest.BodyPublishers.ofString(params))
                .build();

        HttpResponse<Void> response = client.send(request,
                HttpResponse.BodyHandlers.discarding());

        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    public void test_that_the_return_value_from_the_post_is_hello_name() throws Throwable {
        String params = getPostParameters();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(String.format("%s/home", URL)))
                .POST(HttpRequest.BodyPublishers.ofString(params))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(200);

        Document doc = Jsoup.parse(response.body());

        var elem= doc.getElementsByTag("name").first();

        assertThat(elem).isNotNull();
        assertThat(elem.text()).isEqualTo("Hello, Kevin");
    }

    @Test
    public void test_that_the_post_contains_the_correct_content_type() throws Throwable {
        String params = getPostParameters();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(String.format("%s/home", URL)))
                .POST(HttpRequest.BodyPublishers.ofString(params))
                .build();

        var response = client.send(request,
                HttpResponse.BodyHandlers.discarding());

        assertThat(response.statusCode()).isEqualTo(200);

        HttpHeaders headers = response.headers();
        var header =headers.firstValue("content-type");

        assertThat(header.get()).contains("text/xml");
    }

    private String getPostParameters() {
        var values = new HashMap<String, String>() {{
            put("name", "Kevin");
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