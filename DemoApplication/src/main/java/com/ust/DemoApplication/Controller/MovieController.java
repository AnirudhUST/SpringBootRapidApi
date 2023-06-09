package com.ust.DemoApplication.Controller;

import org.asynchttpclient.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final AsyncHttpClient client;

    public MovieController() {
        client = new DefaultAsyncHttpClient();
    }

    @GetMapping
    public CompletableFuture<String> getTopMovies() {
        String url = "https://imdb-top-100-movies.p.rapidapi.com/";

        Request request = new RequestBuilder()
                .setMethod("GET")
                .setUrl(url)
                .addHeader("X-RapidAPI-Key", "d92c3e1fcfmshef81bb9b5397c4cp11b60fjsn72ac60f80d5f")
                .addHeader("X-RapidAPI-Host", "imdb-top-100-movies.p.rapidapi.com")
                .build();

        return client
                .executeRequest(request)
                .toCompletableFuture()
                .thenApply(Response::getResponseBody)
                .whenComplete((responseBody, throwable) -> {
                    try {
                        client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    @GetMapping("/{id}")
    public CompletableFuture<String> getMovieById(@PathVariable("id") String id) {
        String url = "https://imdb-top-100-movies.p.rapidapi.com/" + id;

        Request request = new RequestBuilder()
                .setMethod("GET")
                .setUrl(url)
                .addHeader("X-RapidAPI-Key", "d92c3e1fcfmshef81bb9b5397c4cp11b60fjsn72ac60f80d5f")
                .addHeader("X-RapidAPI-Host", "imdb-top-100-movies.p.rapidapi.com")
                .build();

        return client
                .executeRequest(request)
                .toCompletableFuture()
                .thenApply(Response::getResponseBody)
                .whenComplete((responseBody, throwable) -> {
                    try {
                        client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}

