package com.example.lottery_app.controller;

import com.example.lottery_app.service.LotteryResultService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LotteryResultController {

    @Autowired
    private LotteryResultService lotteryResultService;

    @GetMapping("/scrape-lottery")
    public String scrapeAndSaveLotteryResults() {
        String url = "https://results.govdoc.lk/results/jayoda-2057";

        try {
            // Step 1: Fetch the HTML content from the URL
            Document doc = Jsoup.connect(url).get();

            // Step 2: Select the specific HTML element that contains the lottery results
            Element resultDiv = doc.selectFirst("div.wrp.single-result.name");

            if (resultDiv != null) {
                // Extract the letter
                Element letterElement = resultDiv.selectFirst("div.result-block.letter");
                String letter = letterElement != null ? letterElement.text() : "N/A";

                // Extract the numbers
                Elements numberElements = resultDiv.select("div.result-block.number");
                StringBuilder numbers = new StringBuilder();
                for (Element numberElement : numberElements) {
                    numbers.append(numberElement.text()).append(" ");
                }

                // Save the extracted data to the database using the service
                lotteryResultService.saveLotteryResult(letter, numbers.toString().trim());

                return "Lottery results successfully scraped and saved!";
            } else {
                return "Could not find lottery results on the page.";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while scraping the lottery results.";
        }
    }
}
