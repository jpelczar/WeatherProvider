package io.jpelczar.weather.darksky

import com.fasterxml.jackson.annotation.JsonProperty
import io.jpelczar.weather.data.Provider
import io.jpelczar.weather.data.ProviderDataModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class DarkSkyImportData(
        @JsonProperty("latitude") val latitude: Float,
        @JsonProperty("longitude") val longitude: Float,
        @JsonProperty("timezone") val timezone: String,
        @JsonProperty("currently") val currently: DarkSkyCurrentlyImportData
)

data class DarkSkyCurrentlyImportData(
        @JsonProperty("time") val time: Long,
        @JsonProperty("precipIntensity") val precipIntensity: Float,
        @JsonProperty("temperature") val temperature: Float,
        @JsonProperty("humidity") val humidity: Float,
        @JsonProperty("pressure") val pressure: Float,
        @JsonProperty("windSpeed") val windSpeed: Float,
        @JsonProperty("windBearing") val windBearing: Short
)

fun create(darkSkyImportData: DarkSkyImportData, collectionId: String) =
        ProviderDataModel(-1, "${darkSkyImportData.latitude},${darkSkyImportData.longitude}",
                LocalDateTime.ofEpochSecond(darkSkyImportData.currently.time, 0,
                        ZoneId.of(darkSkyImportData.timezone).rules.getOffset(Instant.now())),
                darkSkyImportData.currently.temperature,
                darkSkyImportData.currently.windSpeed, darkSkyImportData.currently.windBearing,
                darkSkyImportData.currently.humidity * 100, darkSkyImportData.currently.precipIntensity,
                darkSkyImportData.currently.pressure, collectionId, Provider.DARK_SKY)

