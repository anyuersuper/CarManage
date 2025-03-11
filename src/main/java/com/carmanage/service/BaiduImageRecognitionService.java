package com.carmanage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.Base64;
import org.json.JSONObject;

@Service
public class BaiduImageRecognitionService implements ImageRecognitionService {

    // 保持变量名不变
    private static String API_KEY;
    private static String SECRET_KEY;

    // 使用 @Value 注解从配置文件中注入值
    @Value("${api.key}")
    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    @Value("${api.passkey}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

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
        
        return response.toString();
    }
}
