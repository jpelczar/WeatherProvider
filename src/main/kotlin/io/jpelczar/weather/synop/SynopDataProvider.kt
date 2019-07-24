package io.jpelczar.weather.synop

import io.jpelczar.weather.data.DataProvider
import io.jpelczar.weather.data.ProviderDataFactory
import io.jpelczar.weather.data.ProviderDataModel
import io.jpelczar.weather.utils.LoggerDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class SynopDataProvider : DataProvider {

    val logger by LoggerDelegate()

    @Value("\${io.jpelczar.synop.url}")
    private lateinit var url: String

    @Value("\${io.jpelczar.synop.city}")
    private lateinit var city: String

    @Autowired
    private lateinit var providerDataFactory: ProviderDataFactory
    @Autowired
    private lateinit var restTemplate: RestTemplate

    override fun getData(collectionId: String): ProviderDataModel? {
        logger.debug("getData() for SYNOP - START")

        val response = restTemplate.exchange("$url$city", HttpMethod.GET, null,
                object : ParameterizedTypeReference<SynopImportData>() {})

        logger.debug("getData() for SYNOP - END")

        return response.body?.let { x -> create(x, collectionId) }
    }

}