package com.team1.covid19info.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class LastUpdatedInstant {
    var instant: Long? = null

    constructor() {
    }

    constructor(instant: Long?) {
        this.instant = instant
    }

}

