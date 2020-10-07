package com.microservice.scrumprojectservice.resource

import com.microservice.scrumprojectservice.service.BoardService
import com.microservice.scrumprojectservice.service.CardService
import com.microservice.scrumprojectservice.service.ScrumProjectService

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.web.bind.annotation.*

@RestController
class AuthResource {
    @Autowired
    private lateinit var projectService: ScrumProjectService
    @Autowired
    private lateinit var boardService: BoardService
    @Autowired
    private lateinit var cardService: CardService

    @PostMapping("/auth/permission")
    fun getPermissionResources(@RequestParam username: String, @RequestParam teams: List<String>): MutableMap<String, List<String>> {
        val projectIdList = projectService.selectProjectByCreator(username).
                map { project -> project.id.toString() }.toMutableList()

        for (teamId in teams) {
            val projects = projectService.selectProjectByTeamId(teamId)
            for (project in projects) {
                if (!projectIdList.contains(project.id.toString())) {
                    projectIdList.add(project.id.toString())
                }
            }
        }

        val projectServiceClaimMap = mutableMapOf<String, List<String>>()
        projectServiceClaimMap["projects"] = projectIdList

        return projectServiceClaimMap
    }
}