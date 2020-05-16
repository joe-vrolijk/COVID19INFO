package com.team1.covid19info.data;

import com.team1.covid19info.model.AdviseQuestion;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdviseDataRepository {
    private Map<Integer, AdviseQuestion> questionMap;

    public AdviseDataRepository() {
        questionMap = new LinkedHashMap<>();
        questionMap.put(0, new AdviseQuestion(1, 18, "Heeft u koorts, 38 graden " +
                "Celsius of hoger?"));
        questionMap.put(1, new AdviseQuestion(2, 3, "Bent u ouder dan 70 jaar, " +
                "hebt een chronische ziekte of heeft u minder weerstand?"));
        questionMap.put(2, new AdviseQuestion("Bel de huisarts of huisartsenpost\nOp basis van de door " +
                "u ingevulde gegevens en de RIVM richtlijnen adviseren wij u direct contact op te nemen met een arts. ",
                "Ons advies\nOmdat u in een zogenoemde risicogroep valt en omdat u koorts heeft dient u " +
                        "de huisarts te bellen. Doe geen boodschappen en ontvang geen bezoek. Heeft u huisgenoten, " +
                        "dan mogen zij ook niet naar buiten. Als uitzondering hierop mogen alleen huisgenoten die " +
                        "geen klachten hebben even boodschappen doen. Als iedereen 24 uur geen klachten heeft, " +
                        "mag u weer naar buiten. Voor meer informatie klik op onderstaande button. Zorg dat u " +
                        "anderen niet besmet. Houd dus afstand tot andere mensen. Houdt de komende 14 dagen uw " +
                        "gezondheid in de gaten."));
        questionMap.put(3, new AdviseQuestion(4, 7, "Heeft u verkoudheidsklachten, " +
                "zoals neusverkoudheid, loopneus, keelpijn, lichte hoest of verhoging tot 38 graden Celsius?"));
        questionMap.put(4, new AdviseQuestion(5, 6, "Bent u benauwd of ademt u " +
                "steeds moeilijker (bijvoorbeeld bij het lopen)?"));
        questionMap.put(5, new AdviseQuestion("Blijf thuis, ziek uit\nOp basis van de door u ingevulde " +
                "gegevens en de RIVM richtlijnen adviseren wij u om thuis te blijven en uit te zieken.",
                "Ons advies\nDoe geen boodschappen en ontvang geen bezoek. Heeft u huisgenoten, dan mogen " +
                        "zij ook niet naar buiten. Als uitzondering hierop mogen alleen huisgenoten die geen " +
                        "klachten hebben even boodschappen doen. Als iedereen 24 uur geen klachten heeft, mag u weer " +
                        "naar buiten. Voor meer informatie klik op onderstaande button. Zorg dat u anderen niet " +
                        "besmet. Houd dus afstand tot andere mensen. Houdt de komende 14 dagen uw gezondheid in de " +
                        "gaten. Bel alleen met de huisarts als de klachten verergeren en u medische hulp nodig hebt: " +
                        "verkoudheidsklachten met koorts (38 graden Celsius of hoger) en/of benauwdheid."));
        questionMap.put(6, new AdviseQuestion("Blijf thuis, ziek uit\nOp basis van de door u ingevulde " +
                "gegevens en de RIVM richtlijnen adviseren wij u om thuis te blijven en uit te zieken.",
                "Ons advies\nDoe geen boodschappen en ontvang geen bezoek. Heeft u huisgenoten, dan mogen " +
                        "zij ook niet naar buiten. Als uitzondering hierop mogen alleen huisgenoten die geen " +
                        "klachten hebben even boodschappen doen. Als iedereen 24 uur geen klachten heeft, mag u weer " +
                        "naar buiten. Voor meer informatie klik op onderstaande button. Zorg dat u anderen niet " +
                        "besmet. Houd dus afstand tot andere mensen. Houdt de komende 14 dagen uw gezondheid in de " +
                        "gaten. Bel alleen met de huisarts als de klachten verergeren en u medische hulp nodig hebt: " +
                        "verkoudheidsklachten met koorts (38 graden Celsius of hoger) en/of benauwdheid."));
        questionMap.put(7, new AdviseQuestion(8, 9, "Bent u benauwd of ademt u " +
                "steeds moeilijker (bijvoorbeeld bij het lopen)?"));
        questionMap.put(8, new AdviseQuestion("Blijf thuis, ziek uit\nOp basis van de door u ingevulde " +
                "gegevens en de RIVM richtlijnen adviseren wij u om thuis te blijven en uit te zieken.",
                "Ons advies\nDoe geen boodschappen en ontvang geen bezoek. Heeft u huisgenoten, dan mogen " +
                        "zij ook niet naar buiten. Als uitzondering hierop mogen alleen huisgenoten die geen " +
                        "klachten hebben even boodschappen doen. Als iedereen 24 uur geen klachten heeft, mag u weer " +
                        "naar buiten. Voor meer informatie klik op onderstaande button. Zorg dat u anderen niet " +
                        "besmet. Houd dus afstand tot andere mensen. Houdt de komende 14 dagen uw gezondheid in " +
                        "de gaten. Bel alleen met de huisarts als de klachten verergeren en u medische hulp nodig " +
                        "hebt: verkoudheidsklachten met koorts (38 graden Celsius of hoger) en/of benauwdheid."));
        questionMap.put(9, new AdviseQuestion(10, 15, "Heeft u in de afgelopen " +
                "twee weken contact gehad met een patiënt met het coronavirus?"));
        questionMap.put(10, new AdviseQuestion(11, 12, "Heeft uw huisgenoot het " +
                "coronavirus?"));
        questionMap.put(11, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde gegevens " +
                "en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\nOmdat uw " +
                "huisgenoot COVID-19 heeft mag u niet naar buiten. U kunt tot 14 dagen na het laatste contact met " +
                "deze persoon nog ziek worden, daarom moet u thuis in quarantaine blijven. U kunt klachten krijgen " +
                "zoals verkoudheid, hoesten en koorts. De informatie via onderstaande button is voor u van belang."));
        questionMap.put(12, new AdviseQuestion(13, 14, "Heeft uw huisgenoot " +
                "verkoudheidsklachten met koorts (38 graden Celcius of hoger), en/of benauwdheid?"));
        questionMap.put(13, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde gegevens " +
                "en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\nOmdat uw " +
                "huisgenoot klachten heeft mag u niet naar buiten. Als uitzondering hierop mag u of een " +
                "huisgenoot, (iemand die geen klachten heeft), even boodschappen doen. U kunt weer naar buiten " +
                "wanneer u en alle huisgenoten 24 uur geen klachten meer hebben. Dat betekent: geen koorts, " +
                "geen neusverkoudheid en niet hoesten. Voor meer informatie klik op onderstaande button."));
        questionMap.put(14, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde gegevens " +
                "en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\n" +
                "Omdat u in de afgelopen twee contact heeft gehad met een patiënt met het coronavirus mag u niet " +
                "naar buiten. Als uitzondering hierop mag u of een huisgenoot, (iemand die geen klachten heeft), " +
                "even boodschappen doen. U kunt weer naar buiten wanneer u en alle huisgenoten 24 uur geen " +
                "klachten meer hebben. Dat betekent: geen koorts, geen neusverkoudheid en niet hoesten. Voor " +
                "meer informatie klik op onderstaande button."));
        questionMap.put(15, new AdviseQuestion(16, 17, "Heeft uw huisgenoot " +
                "verkoudheidsklachten met koorts (38 graden Celcius of hoger), en/of benauwdheid?"));
        questionMap.put(16, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde gegevens " +
                "en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\n" +
                "Omdat uw huisgenoot klachten heeft mag u niet naar buiten. Als uitzondering hierop mag u of een " +
                "huisgenoot, (iemand die geen klachten heeft), even boodschappen doen. U kunt weer naar buiten " +
                "wanneer u en alle huisgenoten 24 uur geen klachten meer hebben. Dat betekent: geen koorts, geen " +
                "neusverkoudheid en niet hoesten. Voor meer informatie klik op onderstaande button."));
        questionMap.put(17, new AdviseQuestion("Blijf thuis, ziek uit\nOp basis van de door u ingevulde " +
                "gegevens en de RIVM richtlijnen adviseren wij u om thuis te blijven en uit te zieken.", "Ons " +
                "advies\nZorg dat u anderen niet besmet. Houd afstand tot andere mensen. Houdt de komende " +
                "14 dagen uw gezondheid in de gaten. Bel alleen met de huisarts als de klachten verergeren " +
                "en u medische hulp nodig hebt: verkoudheidsklachten met koorts (38 graden Celsius of hoger) " +
                "en/of benauwdheid. Doe geen boodschappen en ontvang geen bezoek. Laat anderen boodschappen doen, " +
                "of laat ze bezorgen, zorg dat een ander de hond uitlaat. Voor huisgenoten zonder klachten gelden " +
                "de regels die voor iedereen in Nederland gelden. Als u 24 uur lang geen klachten hebt, " +
                "mag u weer naar buiten. Voor meer informatie klik op onderstaande button."));
        questionMap.put(18, new AdviseQuestion(19, 30, "Heeft u " +
                "verkoudheidsklachten, zoals neusverkoudheid, loopneus, keelpijn, lichte hoest of " +
                "verhoging tot 38 graden Celsius?"));
        questionMap.put(19, new AdviseQuestion(20, 21, "Bent u benauwd of ademt " +
                "u steeds moeilijker (bijvoorbeeld bij het lopen)?"));
        questionMap.put(20, new AdviseQuestion("Bel de huisarts of huisartsenpost\nOp basis van de " +
                "door u ingevulde gegevens en de RIVM richtlijnen adviseren wij u direct contact op te nemen " +
                "met een arts.", "Ons advies\nDe huisarts overlegt met de GGD of onderzoek naar het " +
                "coronavirus nodig is, maar mogelijk heeft u andere klachten. Ga niet naar het spreekuur, " +
                "maar blijf thuis! Om verspreiding te voorkomen wordt u gevraagd zoveel mogelijk thuis te " +
                "blijven en 1,5 meter afstand te houden. Voor meer informatie klik op onderstaande button."));
        questionMap.put(21, new AdviseQuestion(22, 27, "Heeft in de afgelopen " +
                "twee weken contact gehad met een patiënt met het coronavirus?"));
        questionMap.put(22, new AdviseQuestion(23, 24, "Heeft uw huisgenoot " +
                "het coronavirus?"));
        questionMap.put(23, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde gegevens " +
                "en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\nOmdat uw " +
                "huisgenoot COVID-19 heeft mag u niet naar buiten. U kunt tot 14 dagen na het laatste " +
                "contact met deze persoon nog ziek worden, daarom moet u thuis in quarantaine blijven. U " +
                "kunt klachten krijgen zoals verkoudheid, hoesten en koorts. De informatie via onderstaande " +
                "button is voor u van belang."));
        questionMap.put(24, new AdviseQuestion(25, 26, "Heeft uw huisgenoot " +
                "verkoudheidsklachten met koorts (38 graden Celcius of hoger), en/of benauwdheid?"));
        questionMap.put(25, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde gegevens " +
                "en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\nOmdat uw " +
                "huisgenoot klachten heeft mag u niet naar buiten. Als uitzondering hierop mag u of een huisgenoot, " +
                "(iemand die geen klachten heeft), even boodschappen doen. U kunt weer naar buiten wanneer u " +
                "en alle huisgenoten 24 uur geen klachten meer hebben. Dat betekent: geen koorts, geen " +
                "neusverkoudheid en niet hoesten. Voor meer informatie klik op onderstaande button."));
        questionMap.put(26, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde gegevens " +
                "en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\n" +
                "Omdat u in de afgelopen twee contact heeft gehad met een patiënt met het coronavirus mag u niet " +
                "naar buiten. Als uitzondering hierop mag u of een huisgenoot, (iemand die geen klachten heeft), " +
                "even boodschappen doen. U kunt weer naar buiten wanneer u en alle huisgenoten 24 uur geen klachten " +
                "meer hebben. Dat betekent: geen koorts, geen neusverkoudheid en niet hoesten. Voor meer informatie " +
                "klik op onderstaande button."));
        questionMap.put(27, new AdviseQuestion(28, 29, "Heeft uw huisgenoot " +
                "verkoudheidsklachten met koorts (38 graden Celcius of hoger), en/of benauwdheid?"));
        questionMap.put(28, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde gegevens " +
                "en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\n" +
                "Omdat uw huisgenoot klachten heeft mag u niet naar buiten. Als uitzondering hierop mag u of een " +
                "huisgenoot, (iemand die geen klachten heeft), even boodschappen doen. U kunt weer naar buiten " +
                "wanneer u en alle huisgenoten 24 uur geen klachten meer hebben. Dat betekent: geen koorts, " +
                "geen neusverkoudheid en niet hoesten. Voor meer informatie klik op onderstaande button."));
        questionMap.put(29, new AdviseQuestion("Blijf thuis, ziek uit\nOp basis van de door u ingevulde " +
                "gegevens en de RIVM richtlijnen adviseren wij u om thuis te blijven en uit te zieken.",
                "Ons advies\nZorg dat u anderen niet besmet. Houd afstand tot andere mensen. Houdt de " +
                        "komende 14 dagen uw gezondheid in de gaten. Bel alleen met de huisarts als de klachten " +
                        "verergeren en u medische hulp nodig hebt: verkoudheidsklachten met koorts (38 graden " +
                        "Celsius of hoger) en/of benauwdheid. Doe geen boodschappen en ontvang geen bezoek. Laat " +
                        "anderen boodschappen doen, of laat ze bezorgen, zorg dat een ander de hond uitlaat. Voor " +
                        "huisgenoten zonder klachten gelden de regels die voor iedereen in Nederland gelden. Als " +
                        "u 24 uur lang geen klachten hebt, mag u weer naar buiten. Voor meer informatie klik op " +
                        "onderstaande button."));
        questionMap.put(30, new AdviseQuestion(31, 36, "Heeft in de afgelopen " +
                "twee weken contact gehad met een patiënt met het coronavirus?"));
        questionMap.put(31, new AdviseQuestion(32, 33, "Heeft uw huisgenoot " +
                "het coronavirus?"));
        questionMap.put(32, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde gegevens " +
                "en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\nOmdat uw " +
                "huisgenoot COVID-19 heeft mag u niet naar buiten. U kunt tot 14 dagen na het laatste contact " +
                "met deze persoon nog ziek worden, daarom moet u thuis in quarantaine blijven. U kunt klachten " +
                "krijgen zoals verkoudheid, hoesten en koorts. De informatie via onderstaande button is voor u van " +
                "belang."));
        questionMap.put(33, new AdviseQuestion(34, 35, "Heeft uw huisgenoot " +
                "verkoudheidsklachten met koorts (38 graden Celcius of hoger), en/of benauwdheid?"));
        questionMap.put(34, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde gegevens " +
                "en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\n" +
                "Omdat uw huisgenoot klachten heeft mag u niet naar buiten. Als uitzondering hierop mag u of een " +
                "huisgenoot, (iemand die geen klachten heeft), even boodschappen doen. U kunt weer naar buiten " +
                "wanneer u en alle huisgenoten 24 uur geen klachten meer hebben. Dat betekent: geen koorts, " +
                "geen neusverkoudheid en niet hoesten. Voor meer informatie klik op onderstaande button."));
        questionMap.put(35, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde " +
                "gegevens en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\n" +
                "Omdat u in de afgelopen twee contact heeft gehad met een patiënt met het coronavirus mag " +
                "u niet naar buiten. Als uitzondering hierop mag u of een huisgenoot, (iemand die geen " +
                "klachten heeft), even boodschappen doen. U kunt weer naar buiten wanneer u en alle huisgenoten " +
                "24 uur geen klachten meer hebben. Dat betekent: geen koorts, geen neusverkoudheid en niet " +
                "hoesten. Voor meer informatie klik op onderstaande button."));
        questionMap.put(36, new AdviseQuestion(37, 38, "Heeft uw huisgenoot " +
                "verkoudheidsklachten met koorts (38 graden Celcius of hoger), en/of benauwdheid?"));
        questionMap.put(37, new AdviseQuestion("Blijf thuis\nOp basis van de door u ingevulde " +
                "egevens en de RIVM richtlijnen adviseren wij u om thuis te blijven.", "Ons advies\n" +
                "Omdat uw huisgenoot klachten heeft mag u niet naar buiten. Als uitzondering hierop mag u " +
                "of een huisgenoot, (iemand die geen klachten heeft), even boodschappen doen. U kunt weer " +
                "naar buiten wanneer u en alle huisgenoten 24 uur geen klachten meer hebben. Dat betekent: " +
                "geen koorts, geen neusverkoudheid en niet hoesten. Voor meer informatie klik op onderstaande " +
                "button."));
        questionMap.put(38, new AdviseQuestion("U hoeft niet naar de dokter\nOp basis van de door " +
                "u ingevulde gegevens en de RIVM richtlijnen lijkt er voor nu geen reden om de dokter te " +
                "bellen voor vragen over het coronavirus.", "Ons advies\nOm verspreiding te voorkomen " +
                "wordt u gevraagd zoveel mogelijk thuis te blijven. 1,5 meter afstand te houden. Alleen naar " +
                "buiten te gaan als dat nodig is en ga niet op bezoek bij mensen van 70 jaar of ouder en " +
                "mensen met een kwetsbare gezondheid. Voor meer informatie klik op onderstaande button."));
    }

    public Map<Integer, AdviseQuestion> getQuestionMap() {
        return questionMap;
    }

    public AdviseQuestion getNextAdviseQuestion(Integer nextQuestionId) {
        return questionMap.get(nextQuestionId);
    }

    public String getQuestionText(Integer nextQuestionId) {
        return questionMap.get(nextQuestionId).getQuestionText();
    }

    public String getAdviseText(Integer nextQuestionId) {
        return questionMap.get(nextQuestionId).getAdviceText();
    }

    public int getNextLinkYesAnswer(Integer nextQuestionId) {
        return questionMap.get(nextQuestionId).getLinkYesAnswer();
    }

    public int getNextLinkNoAnswer(Integer nextQuestionId) {
        return questionMap.get(nextQuestionId).getLinkNoAnswer();
    }

    public int getQuestionMapSize() {
        return questionMap.size();
    }
}