package io.jpelczar.weather.utils

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import java.util.*

@Service
class TaskRunner {

    val logger by LoggerDelegate()

    fun run(function: () -> Unit) {
        val taskId = UUID.randomUUID().toString()
        logger.debug("Start task: $taskId")
        GlobalScope.launch {
            function()
            logger.debug("End task: $taskId")
        }
    }
}