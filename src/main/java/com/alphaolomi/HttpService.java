package com.alphaolomi;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface HttpService {
    HttpResponse<String> sendRequest(String url, String method, String params) throws IOException, InterruptedException, IOException;
}