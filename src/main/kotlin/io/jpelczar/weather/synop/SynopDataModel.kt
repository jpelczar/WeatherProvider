package io.jpelczar.weather.synop

import com.fasterxml.jackson.annotation.JsonProperty
import io.jpelczar.weather.data.Provider
import io.jpelczar.weather.data.ProviderDataModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class SynopImportData(
        @JsonProperty("id_stacji") val stationId: Long,
        @JsonProperty("stacja") val station: String,
        @JsonProperty("data_pomiaru") val measureDate: String,
        @JsonProperty("godzina_pomiaru") val measureHour: String,
        @JsonProperty("temperatura") val temperature: Float,
        @JsonProperty("predkosc_wiatru") val windSpeed: Float,
        @JsonProperty("kierunek_wiatru") val windDirection: Short,
        @JsonProperty("wilgotnosc_wzgledna") val humidity: Float,
        @JsonProperty("suma_opadu") val rain: Float,
        @JsonProperty("cisnienie") val pressure: Float
)

fun create(synopImportData: SynopImportData, collectionId: String) =
        ProviderDataModel(synopImportData.stationId, synopImportData.station,
                LocalDateTime.parse("${synopImportData.measureDate} ${synopImportData.measureHour}",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH")),
                synopImportData.temperature, synopImportData.windSpeed, synopImportData.windDirection,
                synopImportData.humidity, synopImportData.rain, synopImportData.pressure, collectionId, Provider.SYNOP)
