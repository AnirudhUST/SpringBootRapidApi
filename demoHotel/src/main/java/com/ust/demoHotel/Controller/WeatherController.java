package com.ust.demoHotel.Controller;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WeatherController {

    @GetMapping("/weather/{latitude}/{longitude}")
    public void getWeather(@PathVariable String latitude, @PathVariable String longitude) throws IOException {
        AsyncHttpClient client = new DefaultAsyncHttpClient();

        String url = "https://dark-sky.p.rapidapi.com/" + latitude + "," + longitude + "?units=auto&lang=en";

        client.prepare("GET", url)
                .setHeader("X-RapidAPI-Key", "d92c3e1fcfmshef81bb9b5397c4cp11b60fjsn72ac60f80d5f")
                .setHeader("X-RapidAPI-Host", "dark-sky.p.rapidapi.com")
                .execute()
                .toCompletableFuture()
                .thenAccept(System.out::println)
                .join();

        client.close();
    }
}

