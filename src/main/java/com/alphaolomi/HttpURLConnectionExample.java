package com.alphaolomi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionExample {

	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String GET_URL = "https://httpbin.org/get";
	private static final String POST_URL = "https://httpbin.org/post";
	private static final String POST_PARAMS = "userName=Java";

	public static void main(String[] args) {
		try {
			System.out.println("Sending GET request...");
			String getResponse = sendRequest(GET_URL, "GET", null);
			System.out.println("GET Response: \n" + getResponse);

			System.out.println("\nSending POST request...");
			String postResponse = sendRequest(POST_URL, "POST", POST_PARAMS);
			System.out.println("POST Response: \n" + postResponse);
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
			// e.printStackTrace();
		}
	}

	private static String sendRequest(String url, String method, String params) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		connection.setRequestMethod(method);
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setConnectTimeout(5000); // 5 seconds connect timeout
		connection.setReadTimeout(5000); // 5 seconds read timeout

		if ("POST".equalsIgnoreCase(method)) {
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setDoOutput(true);
			try (OutputStream os = connection.getOutputStream()) {
				os.write(params.getBytes());
				os.flush();
			}
		}

		int responseCode = connection.getResponseCode();
		System.out.println(method + " Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				return response.toString();
			}
		} else {
			System.out.println(method + " request did not work.");
			return "Error: HTTP response code: " + responseCode;
		}
	}
}
