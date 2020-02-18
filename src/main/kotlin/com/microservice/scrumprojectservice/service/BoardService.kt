package com.microservice.scrumprojectservice.service

import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.Board
import com.microservice.scrumprojectservice.entity.BoardProjectRelation
import com.microservice.scrumprojectservice.repostiry.BoardProjectRelationRepository
import com.microservice.scrumprojectservice.repostiry.BoardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BoardService {
    @Autowired
    private lateinit var boardRepository: BoardRepository
    @Autowired
    private lateinit var boardProjectRelationRepository: BoardProjectRelationRepository

    fun createBoard(projectId: Int): Message {
        val createTime = System.currentTimeMillis().toString()
        val newBoard = Board()
        newBoard.createTime = createTime

        val savedBoard = boardRepository.save(newBoard)
        val newBoardProjectRelation = BoardProjectRelation(projectId, savedBoard.id)
        boardProjectRelationRepository.save(newBoardProjectRelation)
        return Message(true, savedBoard)
}


    fun removeBoard(boardId: Int): Message {
        boardRepository.deleteById(boardId)
        return Message(true, "board remove success")
    }
}