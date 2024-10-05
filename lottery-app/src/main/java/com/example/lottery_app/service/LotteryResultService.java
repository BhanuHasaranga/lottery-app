package com.example.lottery_app.service;

import com.example.lottery_app.model.LotteryResult;
import com.example.lottery_app.repository.LotteryResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotteryResultService {

    @Autowired
    private LotteryResultRepository lotteryResultRepository;

    public void saveLotteryResult(String letter, String numbers) {
        // Assuming the drawDate is not on the page, you can use the current date.
        String drawDate = java.time.LocalDate.now().toString();

        String result = "Letter: " + letter + ", Numbers: " + numbers;

        LotteryResult lotteryResult = new LotteryResult(drawDate, result);
        lotteryResultRepository.save(lotteryResult);
    }
}
