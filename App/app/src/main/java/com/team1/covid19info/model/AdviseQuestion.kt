package com.team1.covid19info.model

class AdviseQuestion {
    var linkYesAnswer: Int = 0
    var linkNoAnswer: Int = 0
    var questionText: String? = null
    var adviceText: String? = null
    var infoButton: Boolean = false

    constructor(body: String, advice: String, info: Boolean) {
        this.questionText = body
        this.adviceText = advice
        this.infoButton = info
    }

    constructor(linkYesAnswer: Int, linkNoAnswer: Int, body: String) {
        this.linkYesAnswer = linkYesAnswer
        this.linkNoAnswer = linkNoAnswer
        this.questionText = body
    }
}
