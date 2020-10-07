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

        val boardIdList = mutableListOf<String>()
        for (projectId in projectIdList) {
            val boards = boardService.selectBoardsByProject(projectId.toInt())
            for (board in boards) {
                if (!boardIdList.contains(board.id.toString())) {
                    boardIdList.add(board.id.toString())
                }
            }
        }

        val cardIdList = mutableListOf<String>()
        for (boardId in boardIdList) {
            val cards = cardService.selectCardsByBoard(boardId.toInt())
            for (card in cards) {
                if (!cardIdList.contains(card.id.toString())) {
                    cardIdList.add(card.id.toString())
                }
            }
        }

        val projectServiceClaimMap = mutableMapOf<String, List<String>>()
        projectServiceClaimMap["projects"] = projectIdList
        projectServiceClaimMap["boards"] = boardIdList
        projectServiceClaimMap["cards"] = cardIdList

        return projectServiceClaimMap
    }
}