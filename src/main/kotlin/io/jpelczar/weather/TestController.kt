package io.jpelczar.weather

import io.jpelczar.weather.data.DataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TestController {

    @Autowired
    private lateinit var dataService: DataService

    @GetMapping("update/data")
    fun updateData(): ResponseEntity<Any> {

        dataService.getAndSave()

        return ResponseEntity.noContent().build()
    }

    @GetMapping("get/data")
    fun getData(): ResponseEntity<Any> {

        dataService.getAndSave()

        return ResponseEntity.noContent().build()
    }

}