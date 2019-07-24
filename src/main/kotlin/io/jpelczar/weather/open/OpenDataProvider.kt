package io.jpelczar.weather.open

import io.jpelczar.weather.data.DataProvider
import io.jpelczar.weather.data.ProviderDataFactory
import io.jpelczar.weather.data.ProviderDataModel
import io.jpelczar.weather.utils.LoggerDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
@PropertySources(value = [
    PropertySource("classpath:application.properties"),
    PropertySource("classpath:secret.properties")
])
class OpenDataProvider : DataProvider {

    val logger by LoggerDelegate()

    @Value("\${io.jpelczar.open.url}")
    private lateinit var url: String

    @Value("\${io.jpelczar.open.key}")
    private lateinit var apiKey: String

    @Value("\${io.jpelczar.open.city}")
    private lateinit var city: String

    @Autowired
    private lateinit var providerDataFactory: ProviderDataFactory
    @Autowired
    private lateinit var restTemplate: RestTemplate

    override fun getData(collectionId: String): ProviderDataModel? {
        logger.debug("getData() for OPEN - START")

        val response = restTemplate.exchange("$url?appid=$apiKey&q=$city&units=metric", HttpMethod.GET, null,
                object : ParameterizedTypeReference<OpenImportData>() {})

        logger.debug("getData() for OPEN - START")

        return response.body?.let { x -> create(x, collectionId) }
    }

}

