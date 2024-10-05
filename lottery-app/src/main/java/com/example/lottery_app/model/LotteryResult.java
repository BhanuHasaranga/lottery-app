package com.example.lottery_app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bhanu_new_lottery_results_v7")
public class LotteryResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String drawDate;

    private String result;

    public LotteryResult() {}

    // Constructor accepting drawDate, letter, and numbers


    public LotteryResult(String drawDate, String result) {
        this.drawDate = drawDate;
        this.result = result;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
