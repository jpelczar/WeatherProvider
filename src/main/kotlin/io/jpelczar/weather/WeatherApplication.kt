package io.jpelczar.weather

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class WeatherApplication

@Configuration
class AppConfiguration {
    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()
}

fun main(args: Array<String>) {
    runApplication<WeatherApplication>(*args)
}
