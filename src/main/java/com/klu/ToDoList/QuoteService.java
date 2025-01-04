package com.klu.ToDoList;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class QuoteService {

    public String getRandomQuote() {
        String apiUrl = "https://dummyjson.com/quotes/random";
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Fetch the response as a JSON string
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // Extract the quote from the JSON response (it's inside the "quote" field)
                String responseBody = response.getBody();
                return extractQuote(responseBody);
            } else {
                return "Failed to fetch a quote. Please try again later.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch a quote. Please try again later.";
        }
    }

    private String extractQuote(String json) {
        // Parsing the JSON response to extract the 'quote' field
        int start = json.indexOf("\"quote\":\"") + 9;
        int end = json.indexOf("\"", start);
        return start > 8 && end > start ? json.substring(start, end) : "No quote available.";
    }
}
