package com.carmanage.service;

import java.io.IOException;

import org.json.JSONObject;

public interface ImageRecognitionService {
    JSONObject recognizeCar(String filepath) throws IOException;
}
