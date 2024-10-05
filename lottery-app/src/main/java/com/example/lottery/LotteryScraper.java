package com.example.lottery;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LotteryScraper {

    public static void main(String[] args) {
        String url = "https://results.govdoc.lk/results/jayoda-2057";

        try {
            // Step 1: Fetch the HTML content from the URL
            Document doc = Jsoup.connect(url).get();

            // Step 2: Select the specific HTML element that contains the lottery results
            // The class is "wrp single-result name", as seen in the HTML snippet provided
            Element resultDiv = doc.selectFirst("div.wrp.single-result.name");

            // Step 3: Extract the letter and numbers from the result block
            if (resultDiv != null) {
                // Extract the letter
                Element letterElement = resultDiv.selectFirst("div.result-block.letter");
                if (letterElement != null) {
                    System.out.println("Letter: " + letterElement.text());
                }

                // Extract the numbers
                Elements numberElements = resultDiv.select("div.result-block.number");
                System.out.print("Numbers: ");
                for (Element numberElement : numberElements) {
                    System.out.print(numberElement.text() + " ");
                }
                System.out.println();
            } else {
                System.out.println("Could not find lottery result on the page.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error fetching lottery results.");
        }
    }
}
