package com.microservice.scrumprojectservice.resource

import com.microservice.scrumprojectservice.config.EnvProperties
import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.ScrumProject
import com.microservice.scrumprojectservice.service.BoardService
import com.microservice.scrumprojectservice.service.ScrumProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardResource {
    @Autowired
    private lateinit var boardService: BoardService

    @PostMapping("/board/create")
    fun createScrumProject(): Message {

        return boardService.createBoard()
    }

    @PostMapping("/board/remove")
    fun removeBoard(@RequestParam boardId: Int): Message {

        return boardService.removeBoard(boardId)
    }
}