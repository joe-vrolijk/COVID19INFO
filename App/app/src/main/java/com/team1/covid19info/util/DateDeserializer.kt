package com.team1.covid19info.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer : JsonDeserializer<Date> {

    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, arg1: Type, arg2: JsonDeserializationContext): Date? {
        val date = element.asString

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        format.setTimeZone(TimeZone.getTimeZone("GMT"))

        try {
            return format.parse(date)
        } catch (exp: ParseException) {
            System.err.println(exp.message)
            return null
        }

    }
}