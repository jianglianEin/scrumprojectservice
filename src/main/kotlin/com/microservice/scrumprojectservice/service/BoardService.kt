package com.microservice.scrumprojectservice.service

import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.Board
import com.microservice.scrumprojectservice.entity.ScrumProject
import com.microservice.scrumprojectservice.repostiry.BoardRepository
import com.microservice.scrumprojectservice.repostiry.ScrumProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BoardService {
    @Autowired
    private lateinit var boardRepository: BoardRepository

    fun createBoard(): Message {
        val createTime = System.currentTimeMillis().toString()
        val newBoard = Board()
        newBoard.createTime = createTime

        val savedBoard = boardRepository.save(newBoard)
        return Message(true, savedBoard)
}


    fun removeBoard(boardId: Int): Message {
        boardRepository.deleteById(boardId)
        return Message(true, "board remove success")
    }
}