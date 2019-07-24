package io.jpelczar.weather.data

import org.springframework.data.mongodb.repository.MongoRepository

interface ProviderDataRepository : MongoRepository<ProviderDataModel, String>