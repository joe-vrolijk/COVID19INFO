package com.team1.covid19info.model

class AdviseQuestion {
     var linkYesAnswer: Int = 0
     var linkNoAnswer: Int = 0
     var questionText: String? = null
     var adviceText: String = ""

    constructor(body: String, advice: String) {
        this.questionText = body
        this.adviceText = advice
    }

    constructor(linkYesAnswer: Int, linkNoAnswer: Int, body: String) {
        this.linkYesAnswer = linkYesAnswer
        this.linkNoAnswer = linkNoAnswer
        this.questionText = body
    }
}
