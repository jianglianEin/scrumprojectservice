package com.microservice.scrumprojectservice.resource

import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.Board
import com.microservice.scrumprojectservice.service.BoardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardResource {
    @Autowired
    private lateinit var boardService: BoardService

    @PostMapping("/board/create")
    fun createBoard(@RequestParam projectId: Int): Message {

        return boardService.createBoard(projectId)
    }

    @PostMapping("/board/remove")
    fun removeBoard(@RequestParam boardId: Int): Message {

        return boardService.removeBoard(boardId)
    }

    @PostMapping("/board/selectByProject")
    fun selectBoardsByProject(@RequestParam projectId: Int): MutableList<Board> {

        return boardService.selectBoardsByProject(projectId)
    }
}