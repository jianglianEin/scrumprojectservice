package com.microservice.scrumprojectservice.resource

import com.microservice.scrumprojectservice.config.EnvProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScrumProjectResource {
    @Autowired
    private lateinit var env: EnvProperties

    @GetMapping()
    fun hello(): String {
        System.out.println("run in MessageService")
        System.out.println(env.env)
        return "hello world\n" + env.env
    }
}