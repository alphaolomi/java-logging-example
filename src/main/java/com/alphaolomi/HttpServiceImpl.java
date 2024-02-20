package com.alphaolomi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpServiceImpl implements HttpService {
    @Override
    public HttpResponse<String> sendRequest(String url, String method, String params) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(url));

        if ("POST".equalsIgnoreCase(method)) {
            requestBuilder = requestBuilder.POST(BodyPublishers.ofString(params));
        } else {
            requestBuilder = requestBuilder.GET();
        }

        HttpRequest request = requestBuilder.build();
        return client.send(request, BodyHandlers.ofString());
    }
}
