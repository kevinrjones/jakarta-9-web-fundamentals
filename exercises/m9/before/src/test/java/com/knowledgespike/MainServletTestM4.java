package com.knowledgespike;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class MainServletTestM4 {

    static String URL="http://localhost:8081/myblog";

    @Test
    public void test_that_index_jsp_is_not_reachable() throws Throwable {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/index.jsp", URL)))
                .GET() // GET is default
                .build();

        HttpResponse<Void> response = client.send(request,
                HttpResponse.BodyHandlers.discarding());


        assertThat(response)
                .withFailMessage("==> Did you deploy the application?")
                .isNotNull();

        assertThat(response.statusCode())
                .withFailMessage("==> Did you add the redirect?")
                .isEqualTo(404);
    }


}
