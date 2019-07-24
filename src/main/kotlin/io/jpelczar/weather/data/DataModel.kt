package io.jpelczar.weather.data

import org.springframework.data.annotation.Id
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*


data class ProviderDataModel(val stationId: Long,
                             val station: String,
                             val measureDate: LocalDateTime,
                             val temperature: Float,
                             val windSpeed: Float,
                             val windDirection: Short,
                             val humidity: Float,
                             val rain: Float,
                             val pressure: Float,
                             val collectionId: String,
                             val provider: Provider,
                             val createDate: LocalDateTime = LocalDateTime.now(),
                             @Id val id: String = UUID.randomUUID().toString())

@Service
class ProviderDataFactory

enum class Provider {
    OPEN, SYNOP, DARK_SKY
}