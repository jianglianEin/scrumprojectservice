package com.microservice.scrumprojectservice.resource

import com.microservice.scrumprojectservice.config.EnvProperties
import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.ScrumProject
import com.microservice.scrumprojectservice.service.ScrumProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ScrumProjectResource {
    @Autowired
    private lateinit var env: EnvProperties
    @Autowired
    private lateinit var scrumProjectService: ScrumProjectService

    @GetMapping()
    fun hello(): String {
        System.out.println("run in Scrum Project Service")
        System.out.println(env.env)
        return "hello world\n" + env.env
    }

    @PostMapping("/scrum_project/create")
    fun createScrumProject(@RequestParam projectName: String,
                           @RequestParam teamId: Int?,
                           @RequestParam creator: String): Message {
        val newScrumProject = ScrumProject(projectName, teamId, creator)

        return scrumProjectService.createScrumProject(newScrumProject)
    }

    @PostMapping("/scrum_project/update")
    fun updateScrumProject(@RequestParam projectId: Int,
                           @RequestParam projectName: String?,
                           @RequestParam teamId: Int?,
                           @RequestParam colTitle: ArrayList<String>?,
                           @RequestParam rowTitle: ArrayList<String>?,
                           @RequestParam iteration: Int? = 14): Message {
        val updateScrumProject = ScrumProject(projectName = projectName, teamId = teamId)
        updateScrumProject.id = projectId
        updateScrumProject.colTitle = colTitle
        updateScrumProject.rowTitle = rowTitle
        updateScrumProject.iteration = iteration

        return scrumProjectService.updateScrumProject(updateScrumProject)
    }

    @PostMapping("/scrum_project/remove")
    fun removeScrumProject(@RequestParam projectId: Int): Message {

        return scrumProjectService.removeScrumProject(projectId)
    }
}