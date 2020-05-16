package com.team1.covid19info.model;

public class AdviseQuestion {
    private int linkYesAnswer;
    private int linkNoAnswer;
    private String questionText;
    private String adviceText;

    public AdviseQuestion( String body, String advice) {
        this.questionText = body;
        this.adviceText = advice;
    }

    public AdviseQuestion( int linkYesAnswer, int linkNoAnswer, String body) {
        this.linkYesAnswer = linkYesAnswer;
        this.linkNoAnswer = linkNoAnswer;
        this.questionText = body;
    }


    public int getLinkYesAnswer() {
        return linkYesAnswer;
    }

    public int getLinkNoAnswer() {
        return linkNoAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAdviceText() {
        return adviceText;
    }
}

