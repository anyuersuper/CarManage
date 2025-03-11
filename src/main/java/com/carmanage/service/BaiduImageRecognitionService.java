package com.carmanage.service;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.Base64;
import org.json.JSONObject;

public class BaiduImageRecognitionService implements ImageRecognitionService {

    // Baidu API Keys
    private static final String API_KEY = "bPY4lVG048ydQ2e99a6gOCGu";
    private static final String SECRET_KEY = "BmfvIQ1o6WfowVvOHiADW6GjrcIqe1vd";

    @Override
    public JSONObject recognizeCar(String filepath) throws IOException {
        String accessToken = getAccessToken();
        String imageBase64 = encodeImageToBase64(filepath);

        // Prepare POST data
        String payload = "image=" + URLEncoder.encode(imageBase64, "UTF-8") + "&output_brand=false";
        
        // Prepare the URL for the API request
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/car?access_token=" + accessToken;
        
        // Make the HTTP request and get the response
        String response = sendPostRequest(url, payload);
        
        // Parse the JSON response and return it
        return new JSONObject(response);
    }

    private String getAccessToken() throws IOException {
        String url = "https://aip.baidubce.com/oauth/2.0/token";
        String params = "grant_type=client_credentials&client_id=" + API_KEY + "&client_secret=" + SECRET_KEY;

        String response = sendPostRequest(url, params);
        JSONObject jsonResponse = new JSONObject(response);
        return jsonResponse.getString("access_token");
    }

    private String encodeImageToBase64(String filepath) throws IOException {
        File file = new File(filepath);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }

    private String sendPostRequest(String url, String payload) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        // Send request data
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(payload);
            wr.flush();
        }

        // Get the response code
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        //System.out.println("API Response: " + response); // 打印API的原始响应

        return response.toString();
    }
}
