package com.alphaolomi;

import static org.mockito.Mockito.*;

import java.net.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HttpClientExampleTest {

    private HttpService httpService;
    private HttpClientExample httpClientExample;

    @BeforeEach
    public void setUp() {
        httpService = mock(HttpService.class); // Mock the HttpService
        httpClientExample = new HttpClientExample(httpService); // Inject the mock into your client example
    }

    @Test
    public void testGetRequest() throws Exception {
        String expectedResponse = "{\"success\": true}";
        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.body()).thenReturn(expectedResponse);
        when(httpService.sendRequest(anyString(), eq("GET"), any())).thenReturn(mockResponse);

        String actualResponse = httpClientExample.performGetRequest("https://example.com/get");
        assertEquals(expectedResponse, actualResponse);

        verify(httpService).sendRequest("https://example.com/get", "GET", null);
    }

    // Similar test can be written for POST request
}
