package com.team1.covid19info

import com.team1.covid19info.data.AdviseDataRepository
import org.junit.Assert.*

import org.junit.Test

class AdviseUnitTest() {
    var repository: AdviseDataRepository = AdviseDataRepository()

    @Test
    fun checkForCorrectNextYesAnswer() {
        assertEquals(5, repository.getNextLinkYesAnswer(4).toLong())
    }

    @Test
    fun checkForCorrectNextNoAnswer() {
        assertEquals(6, repository.getNextLinkNoAnswer(4).toLong())
    }

    @Test
    fun checkFirstQuestionText() {
        assertEquals("Heeft u koorts, 38 graden Celsius of hoger?", repository.getQuestionText(0));
    }

    @Test
    fun checkForExistingAdvise() {
        assertNull(repository.getAdviseText(4))
        assertNotNull(repository.getAdviseText(5))
        val ADVISE: String
        ADVISE =
            "Ons advies\nDoe geen boodschappen en ontvang geen bezoek. Heeft u huisgenoten, dan mogen " +
                    "zij ook niet naar buiten. Als uitzondering hierop mogen alleen huisgenoten die geen " +
                    "klachten hebben even boodschappen doen. Als iedereen 24 uur geen klachten heeft, mag u weer " +
                    "naar buiten. Voor meer informatie klik op onderstaande button. Zorg dat u anderen niet " +
                    "besmet. Houd dus afstand tot andere mensen. Houdt de komende 14 dagen uw gezondheid in de " +
                    "gaten. Bel alleen met de huisarts als de klachten verergeren en u medische hulp nodig hebt: " +
                    "verkoudheidsklachten met koorts (38 graden Celsius of hoger) en/of benauwdheid."
        assertEquals(ADVISE, repository.getAdviseText(5))
    }
}