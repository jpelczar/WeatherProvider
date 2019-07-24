package io.jpelczar.weather.open

import com.fasterxml.jackson.annotation.JsonProperty
import io.jpelczar.weather.data.Provider
import io.jpelczar.weather.data.ProviderDataModel
import java.time.LocalDateTime
import java.time.ZoneOffset

data class OpenImportData(
        @JsonProperty("id") val stationId: Long,
        @JsonProperty("name") val station: String,
        @JsonProperty("main") val main: OpenImportMainData,
        @JsonProperty("wind") val wind: OpenImportWindData,
        @JsonProperty("dt") val dt: Long,
        @JsonProperty("timezone") val timezone: Long
)

data class OpenImportMainData(
        @JsonProperty("temp") val temp: Float,
        @JsonProperty("pressure") val pressure: Float,
        @JsonProperty("humidity") val humidity: Float
)

data class OpenImportWindData(
        @JsonProperty("speed") val speed: Float,
        @JsonProperty("deg") val deg: Short
)

fun create(openImportData: OpenImportData, collectionId: String) =
        ProviderDataModel(openImportData.stationId, openImportData.station,
                LocalDateTime.ofEpochSecond(openImportData.dt, 0, ZoneOffset.ofTotalSeconds(
                        openImportData.timezone.toInt())),
                openImportData.main.temp, openImportData.wind.speed, openImportData.wind.deg,
                openImportData.main.humidity, -1f, openImportData.main.pressure, collectionId, Provider.OPEN)

