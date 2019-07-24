package io.jpelczar.weather.data

import io.jpelczar.weather.utils.LoggerDelegate
import io.jpelczar.weather.utils.TaskRunner
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface DataProvider {
    fun getData(collectionId: String): ProviderDataModel?
}

@Service
@Slf4j
class DataService {

    val logger by LoggerDelegate()

    @Autowired
    private lateinit var providers: List<DataProvider>
    @Autowired
    private lateinit var providerDataRepository: ProviderDataRepository
    @Autowired
    private lateinit var taskRunner: TaskRunner

    fun getAndSave() {
        val collectionId = UUID.randomUUID().toString()

        logger.info("getAndSave() for collectionId: $collectionId")

        providers.forEach {
            logger.debug("Run task for provider: ${it.javaClass.simpleName}")
            taskRunner.run { it.getData(collectionId)?.let { data -> providerDataRepository.save(data) } }
        }
    }
}


