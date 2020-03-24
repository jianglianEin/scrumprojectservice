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
                           @RequestParam creator: String,
                           @RequestParam teamId: String?,
                           @RequestParam colTitle: ArrayList<String>?,
                           @RequestParam rowTitle: ArrayList<String>?,
                           @RequestParam iteration: Int?): Message {
        val newScrumProject = ScrumProject(projectName, teamId, creator)
        newScrumProject.colTitle = colTitle
        newScrumProject.rowTitle = rowTitle
        newScrumProject.iteration = iteration

        return scrumProjectService.createScrumProject(newScrumProject)
    }

    @PostMapping("/scrum_project/update")
    fun updateScrumProject(@RequestParam projectId: Int,
                           @RequestParam projectName: String?,
                           @RequestParam teamId: String?,
                           @RequestParam colTitle: ArrayList<String>?,
                           @RequestParam rowTitle: ArrayList<String>?,
                           @RequestParam iteration: Int? = 14): Message {
        val updateScrumProject = ScrumProject()
        updateScrumProject.id = projectId
        updateScrumProject.projectName = projectName
        updateScrumProject.teamId = teamId
        updateScrumProject.colTitle = colTitle
        updateScrumProject.rowTitle = rowTitle
        updateScrumProject.iteration = iteration

        return scrumProjectService.updateScrumProject(updateScrumProject)
    }

    @PostMapping("/scrum_project/remove")
    fun removeScrumProject(@RequestParam projectId: Int): Message {

        return scrumProjectService.removeScrumProject(projectId)
    }

    @PostMapping("/scrum_project/selectByCreator")
    fun selectProjectByCreator(@RequestParam creator: String): MutableList<ScrumProject> {
        return scrumProjectService.selectProjectByCreator(creator)
    }
}