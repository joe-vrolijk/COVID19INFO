package com.team1.covid19info.data

import com.team1.covid19info.model.AdviseQuestion

public class AdviseDataRepository {

    val questionList: List<AdviseQuestion>

    init {
        questionList = listOf(
            AdviseQuestion( // index: 0
                1,
                18,
                "Heeft u koorts, 38 graden Celsius of hoger?"
            ),
            AdviseQuestion( // index: 1
                2,
                3,
                "Bent u ouder dan 70 jaar, hebt een chronische ziekte of heeft u minder " +
                        "weerstand?"
            ),
            AdviseQuestion( // index: 2
                "Bel de huisarts of huisartsenpost\nOp basis van de door u ingevulde " +
                        "gegevens en de RIVM richtlijnen adviseren wij u direct contact op te " +
                        "nemen met een arts. ",
                "Ons advies\nOmdat u in een zogenoemde risicogroep valt en omdat u koorts " +
                        "heeft dient u de huisarts te bellen. Doe geen boodschappen en ontvang " +
                        "geen bezoek. Heeft u huisgenoten, dan mogen zij ook niet naar buiten. " +
                        "Als uitzondering hierop mogen alleen huisgenoten die geen klachten " +
                        "hebben even boodschappen doen. Als iedereen 24 uur geen klachten heeft, " +
                        "mag u weer naar buiten. Voor meer informatie klik op onderstaande " +
                        "button. Zorg dat u anderen niet besmet. Houd dus afstand tot andere " +
                        "mensen. Houdt de komende 14 dagen uw gezondheid in de gaten."
            ),
            AdviseQuestion( // index: 3
                4,
                7,
                "Heeft u verkoudheidsklachten, zoals neusverkoudheid, loopneus, keelpijn, " +
                        "lichte hoest of verhoging tot 38 graden Celsius?"
            ),
            AdviseQuestion( // index: 4
                5,
                6,
                "Bent u benauwd of ademt u steeds moeilijker (bijvoorbeeld bij het lopen)?"
            ),
            AdviseQuestion( // index: 5
                "Blijf thuis, ziek uit\nOp basis van de door u ingevulde gegevens en de " +
                        "RIVM richtlijnen adviseren wij u om thuis te blijven en uit te zieken.",
                "Ons advies\nDoe geen boodschappen en ontvang geen bezoek. Heeft u " +
                        "huisgenoten, dan mogen zij ook niet naar buiten. Als uitzondering " +
                        "hierop mogen alleen huisgenoten die geen klachten hebben even " +
                        "boodschappen doen. Als iedereen 24 uur geen klachten heeft, mag u weer " +
                        "naar buiten. Voor meer informatie klik op onderstaande button. Zorg " +
                        "dat u anderen niet besmet. Houd dus afstand tot andere mensen. " +
                        "Houdt de komende 14 dagen uw gezondheid in de gaten. Bel alleen met " +
                        "de huisarts als de klachten verergeren en u medische hulp nodig hebt: " +
                        "verkoudheidsklachten met koorts (38 graden Celsius of hoger) en/of " +
                        "benauwdheid."
            ),
            AdviseQuestion( // index: 6
                "Blijf thuis, ziek uit\nOp basis van de door u ingevulde gegevens en de " +
                        "RIVM richtlijnen adviseren wij u om thuis te blijven en uit te zieken.",
                "Ons advies\nDoe geen boodschappen en ontvang geen bezoek. Heeft u " +
                        "huisgenoten, dan mogen zij ook niet naar buiten. Als uitzondering " +
                        "hierop mogen alleen huisgenoten die geen klachten hebben even " +
                        "boodschappen doen. Als iedereen 24 uur geen klachten heeft, mag u weer " +
                        "naar buiten. Voor meer informatie klik op onderstaande button. Zorg " +
                        "dat u anderen niet besmet. Houd dus afstand tot andere mensen. Houdt " +
                        "de komende 14 dagen uw gezondheid in de gaten. Bel alleen met de " +
                        "huisarts als de klachten verergeren en u medische hulp nodig hebt: " +
                        "verkoudheidsklachten met koorts (38 graden Celsius of hoger) en/of " +
                        "benauwdheid."
            ),
            AdviseQuestion( // index: 7
                8,
                9,
                "Bent u benauwd of ademt u steeds moeilijker (bijvoorbeeld bij het lopen)?"
            ),
            AdviseQuestion( // index: 8
                "Blijf thuis, ziek uit\nOp basis van de door u ingevulde gegevens en de " +
                        "RIVM richtlijnen adviseren wij u om thuis te blijven en uit te zieken.",
                "Ons advies\nDoe geen boodschappen en ontvang geen bezoek. Heeft u " +
                        "huisgenoten, dan mogen zij ook niet naar buiten. Als uitzondering " +
                        "hierop mogen alleen huisgenoten die geen klachten hebben even " +
                        "boodschappen doen. Als iedereen 24 uur geen klachten heeft, mag u weer " +
                        "naar buiten. Voor meer informatie klik op onderstaande button. Zorg " +
                        "dat u anderen niet besmet. Houd dus afstand tot andere mensen. Houdt de " +
                        "komende 14 dagen uw gezondheid in de gaten. Bel alleen met de huisarts " +
                        "als de klachten verergeren en u medische hulp nodig hebt: " +
                        "verkoudheidsklachten met koorts (38 graden Celsius of hoger) " +
                        "en/of benauwdheid."
            ),
            AdviseQuestion( // index: 9
                10,
                15,
                "Heeft u in de afgelopen twee weken contact gehad met een patiënt met het " +
                        "coronavirus?"
            ),
            AdviseQuestion( // index: 10
                11,
                12,
                "Heeft uw huisgenoot het coronavirus?"
            ),
            AdviseQuestion( // index: 11
                "Blijf thuis\nOp basis van de door u ingevulde gegevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat uw huisgenoot COVID-19 heeft mag u niet naar buiten. " +
                        "U kunt tot 14 dagen na het laatste contact met deze persoon nog ziek " +
                        "worden, daarom moet u thuis in quarantaine blijven. U kunt klachten " +
                        "krijgen zoals verkoudheid, hoesten en koorts. De informatie via " +
                        "onderstaande button is voor u van belang."
            ),
            AdviseQuestion( // index: 12
                13,
                14,
                "Heeft uw huisgenoot verkoudheidsklachten met koorts (38 graden Celcius " +
                        "of hoger), en/of benauwdheid?"
            ),
            AdviseQuestion( // index: 13
                "Blijf thuis\nOp basis van de door u ingevulde gegevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat uw huisgenoot klachten heeft mag u niet naar buiten. " +
                        "Als uitzondering hierop mag u of een huisgenoot, (iemand die geen " +
                        "klachten heeft), even boodschappen doen. U kunt weer naar buiten " +
                        "wanneer u en alle huisgenoten 24 uur geen klachten meer hebben. Dat " +
                        "betekent: geen koorts, geen neusverkoudheid en niet hoesten. Voor meer " +
                        "informatie klik op onderstaande button."
            ),
            AdviseQuestion( // index: 14
                "Blijf thuis\nOp basis van de door u ingevulde gegevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat u in de afgelopen twee contact heeft gehad met een " +
                        "patiënt met het coronavirus mag u niet naar buiten. Als uitzondering " +
                        "hierop mag u of een huisgenoot, (iemand die geen klachten heeft), even " +
                        "boodschappen doen. U kunt weer naar buiten wanneer u en alle " +
                        "huisgenoten 24 uur geen klachten meer hebben. Dat betekent: geen " +
                        "koorts, geen neusverkoudheid en niet hoesten. Voor meer informatie " +
                        "klik op onderstaande button."
            ),
            AdviseQuestion( // index: 15
                16,
                17,
                "Heeft uw huisgenoot verkoudheidsklachten met koorts (38 graden Celcius of " +
                        "hoger), en/of benauwdheid?"
            ),
            AdviseQuestion( // index: 16
                "Blijf thuis\nOp basis van de door u ingevulde gegevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat uw huisgenoot klachten heeft mag u niet naar buiten. " +
                        "Als uitzondering hierop mag u of een huisgenoot, (iemand die geen " +
                        "klachten heeft), even boodschappen doen. U kunt weer naar buiten " +
                        "wanneer u en alle huisgenoten 24 uur geen klachten meer hebben. Dat " +
                        "betekent: geen koorts, geen neusverkoudheid en niet hoesten. Voor meer " +
                        "informatie klik op onderstaande button."
            ),
            AdviseQuestion( // index: 17
                "Blijf thuis, ziek uit\nOp basis van de door u ingevulde gegevens en de " +
                        "RIVM richtlijnen adviseren wij u om thuis te blijven en uit te zieken.",
                "Ons advies\nZorg dat u anderen niet besmet. Houd afstand tot andere " +
                        "mensen. Houdt de komende 14 dagen uw gezondheid in de gaten. Bel " +
                        "alleen met de huisarts als de klachten verergeren en u medische hulp " +
                        "nodig hebt: verkoudheidsklachten met koorts (38 graden Celsius of " +
                        "hoger) en/of benauwdheid. Doe geen boodschappen en ontvang geen bezoek. " +
                        "Laat anderen boodschappen doen, of laat ze bezorgen, zorg dat een ander " +
                        "de hond uitlaat. Voor huisgenoten zonder klachten gelden de regels die " +
                        "voor iedereen in Nederland gelden. Als u 24 uur lang geen klachten " +
                        "hebt, mag u weer naar buiten. Voor meer informatie klik op onderstaande " +
                        "button."
            ),
            AdviseQuestion( // index: 18
                19,
                30,
                "Heeft u verkoudheidsklachten, zoals neusverkoudheid, loopneus, keelpijn, " +
                        "lichte hoest of verhoging tot 38 graden Celsius?"
            ),
            AdviseQuestion( // index: 19
                20,
                21,
                "Bent u benauwd of ademt u steeds moeilijker (bijvoorbeeld bij het lopen)?"
            ),
            AdviseQuestion( // index: 20
                "Bel de huisarts of huisartsenpost\nOp basis van de door u ingevulde " +
                        "gegevens en de RIVM richtlijnen adviseren wij u direct contact op te " +
                        "nemen met een arts.",
                "Ons advies\nDe huisarts overlegt met de GGD of onderzoek naar het " +
                        "coronavirus nodig is, maar mogelijk heeft u andere klachten. Ga niet " +
                        "naar het spreekuur, maar blijf thuis! Om verspreiding te voorkomen " +
                        "wordt u gevraagd zoveel mogelijk thuis te blijven en 1,5 meter " +
                        "afstand te houden. Voor meer informatie klik op onderstaande button."
            ),
            AdviseQuestion( // index: 21
                22,
                27,
                "Heeft in de afgelopen twee weken contact gehad met een patiënt met het " +
                        "coronavirus?"
            ),
            AdviseQuestion( // index: 22
                23,
                24,
                "Heeft uw huisgenoot het coronavirus?"
            ),
            AdviseQuestion( // index: 23
                "Blijf thuis\nOp basis van de door u ingevulde gegevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat uw huisgenoot COVID-19 heeft mag u niet naar buiten. " +
                        "U kunt tot 14 dagen na het laatste contact met deze persoon nog ziek " +
                        "worden, daarom moet u thuis in quarantaine blijven. U kunt klachten " +
                        "krijgen zoals verkoudheid, hoesten en koorts. De informatie via " +
                        "onderstaande button is voor u van belang."
            ),
            AdviseQuestion( // index: 24
                25,
                26,
                "Heeft uw huisgenoot verkoudheidsklachten met koorts (38 graden Celcius " +
                        "of hoger), en/of benauwdheid?"
            ),
            AdviseQuestion( // index: 25
                "Blijf thuis\nOp basis van de door u ingevulde gegevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat uw huisgenoot klachten heeft mag u niet naar buiten. " +
                        "Als uitzondering hierop mag u of een huisgenoot, (iemand die geen " +
                        "klachten heeft), even boodschappen doen. U kunt weer naar buiten " +
                        "wanneer u en alle huisgenoten 24 uur geen klachten meer hebben. Dat " +
                        "betekent: geen koorts, geen neusverkoudheid en niet hoesten. Voor meer " +
                        "informatie klik op onderstaande button."
            ),
            AdviseQuestion( // index: 26
                "Blijf thuis\nOp basis van de door u ingevulde gegevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat u in de afgelopen twee contact heeft gehad met een " +
                        "patiënt met het coronavirus mag u niet naar buiten. Als uitzondering " +
                        "hierop mag u of een huisgenoot, (iemand die geen klachten heeft), even " +
                        "boodschappen doen. U kunt weer naar buiten wanneer u en alle " +
                        "huisgenoten 24 uur geen klachten meer hebben. Dat betekent: geen " +
                        "koorts, geen neusverkoudheid en niet hoesten. Voor meer informatie klik " +
                        "op onderstaande button."
            ),
            AdviseQuestion( // index: 27
                28,
                29,
                "Heeft uw huisgenoot verkoudheidsklachten met koorts (38 graden Celcius of " +
                        "hoger), en/of benauwdheid?"
            ),
            AdviseQuestion( // index: 28
                "Blijf thuis\nOp basis van de door u ingevulde gegevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat uw huisgenoot klachten heeft mag u niet naar buiten. " +
                        "Als uitzondering hierop mag u of een huisgenoot, (iemand die geen " +
                        "klachten heeft), even boodschappen doen. U kunt weer naar buiten wanneer" +
                        " u en alle huisgenoten 24 uur geen klachten meer hebben. Dat betekent: " +
                        "geen koorts, geen neusverkoudheid en niet hoesten. Voor meer informatie " +
                        "klik op onderstaande button."
            ),
            AdviseQuestion( // index: 29
                "Blijf thuis, ziek uit\nOp basis van de door u ingevulde gegevens en de " +
                        "RIVM richtlijnen adviseren wij u om thuis te blijven en uit te zieken.",
                "Ons advies\nZorg dat u anderen niet besmet. Houd afstand tot andere " +
                        "mensen. Houdt de komende 14 dagen uw gezondheid in de gaten. Bel " +
                        "alleen met de huisarts als de klachten verergeren en u medische hulp " +
                        "nodig hebt: verkoudheidsklachten met koorts (38 graden Celsius of " +
                        "hoger) en/of benauwdheid. Doe geen boodschappen en ontvang geen bezoek. " +
                        "Laat anderen boodschappen doen, of laat ze bezorgen, zorg dat een ander " +
                        "de hond uitlaat. Voor huisgenoten zonder klachten gelden de regels die " +
                        "voor iedereen in Nederland gelden. Als u 24 uur lang geen klachten " +
                        "hebt, mag u weer naar buiten. Voor meer informatie klik op onderstaande " +
                        "button."
            ),
            AdviseQuestion( // index: 30
                31,
                36,
                "Heeft in de afgelopen twee weken contact gehad met een patiënt met het " +
                        "coronavirus?"
            ),
            AdviseQuestion( // index: 31
                32,
                33,
                "Heeft uw huisgenoot het coronavirus?"
            ),
            AdviseQuestion( // index: 32
                "Blijf thuis\nOp basis van de door u ingevulde gegevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat uw huisgenoot COVID-19 heeft mag u niet naar buiten. " +
                        "U kunt tot 14 dagen na het laatste contact met deze persoon nog ziek " +
                        "worden, daarom moet u thuis in quarantaine blijven. U kunt klachten " +
                        "krijgen zoals verkoudheid, hoesten en koorts. De informatie via " +
                        "onderstaande button is voor u van belang."
            ),
            AdviseQuestion( // index: 33
                34,
                35,
                "Heeft uw huisgenoot verkoudheidsklachten met koorts (38 graden Celcius " +
                        "of hoger), en/of benauwdheid?"
            ),
            AdviseQuestion( // index: 34
                "Blijf thuis\nOp basis van de door u ingevulde gegevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat uw huisgenoot klachten heeft mag u niet naar buiten. " +
                        "Als uitzondering hierop mag u of een huisgenoot, (iemand die geen " +
                        "klachten heeft), even boodschappen doen. U kunt weer naar buiten " +
                        "wanneer u en alle huisgenoten 24 uur geen klachten meer hebben. " +
                        "Dat betekent: geen koorts, geen neusverkoudheid en niet hoesten. " +
                        "Voor meer informatie klik op onderstaande button."
            ),
            AdviseQuestion( // index: 35
                "Blijf thuis\nOp basis van de door u ingevulde gegevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat u in de afgelopen twee contact heeft gehad met een " +
                        "patiënt met het coronavirus mag u niet naar buiten. Als uitzondering " +
                        "hierop mag u of een huisgenoot, (iemand die geen klachten heeft), even " +
                        "boodschappen doen. U kunt weer naar buiten wanneer u en alle " +
                        "huisgenoten 24 uur geen klachten meer hebben. Dat betekent: geen " +
                        "koorts, geen neusverkoudheid en niet hoesten. Voor meer informatie " +
                        "klik op onderstaande button."
            ),
            AdviseQuestion( // index: 36
                37,
                38,
                "Heeft uw huisgenoot verkoudheidsklachten met koorts (38 graden Celcius " +
                        "of hoger), en/of benauwdheid?"
            ),
            AdviseQuestion( // index: 37
                "Blijf thuis\nOp basis van de door u ingevulde egevens en de RIVM " +
                        "richtlijnen adviseren wij u om thuis te blijven.",
                "Ons advies\nOmdat uw huisgenoot klachten heeft mag u niet naar buiten. " +
                        "Als uitzondering hierop mag u of een huisgenoot, (iemand die geen " +
                        "klachten heeft), even boodschappen doen. U kunt weer naar buiten " +
                        "wanneer u en alle huisgenoten 24 uur geen klachten meer hebben. Dat " +
                        "betekent: geen koorts, geen neusverkoudheid en niet hoesten. Voor meer " +
                        "informatie klik op onderstaande button."
            ),
            AdviseQuestion( // index: 38
                "U hoeft niet naar de dokter\nOp basis van de door u ingevulde gegevens " +
                        "en de RIVM richtlijnen lijkt er voor nu geen reden om de dokter te " +
                        "bellen voor vragen over het coronavirus.",
                "Ons advies\nOm verspreiding te voorkomen wordt u gevraagd zoveel mogelijk " +
                        "thuis te blijven. 1,5 meter afstand te houden. Alleen naar buiten te " +
                        "gaan als dat nodig is en ga niet op bezoek bij mensen van 70 jaar of " +
                        "ouder en mensen met een kwetsbare gezondheid. Voor meer informatie " +
                        "klik op onderstaande button."
            )
        )
    }

    fun getQuestions(): List<AdviseQuestion> {
        return questionList
    }

    fun isAdvice(questionId: Int): Boolean {
        return (questionList[questionId].adviceText != null)
    }

    fun getQuestionText(questionId: Int): String? {
        val question: AdviseQuestion = questionList[questionId]
        return question.questionText
    }

    fun getAdviseText(questionId: Int): String? {
        val question: AdviseQuestion = questionList[questionId]
        return question.adviceText
    }

    fun getLinkYesId(questionId: Int): Int {
        val question: AdviseQuestion = questionList[questionId]
        return question.linkYesAnswer
    }

    fun getLinkNoId(questionId: Int): Int {
        val question: AdviseQuestion = questionList[questionId]
        return question.linkNoAnswer
    }
}