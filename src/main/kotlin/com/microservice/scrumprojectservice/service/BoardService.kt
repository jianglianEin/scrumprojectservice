package com.microservice.scrumprojectservice.service

import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.Board
import com.microservice.scrumprojectservice.entity.BoardProjectRelation
import com.microservice.scrumprojectservice.repostiry.BoardProjectRelationRepository
import com.microservice.scrumprojectservice.repostiry.BoardRepository
import com.microservice.scrumprojectservice.repostiry.CardBoardRelationRepository
import com.microservice.scrumprojectservice.repostiry.CardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BoardService {
    @Autowired
    private lateinit var boardRepository: BoardRepository
    @Autowired
    private lateinit var boardProjectRelationRepository: BoardProjectRelationRepository
    @Autowired
    private lateinit var cardBoardRelationRepository: CardBoardRelationRepository
    @Autowired
    private lateinit var cardRepository: CardRepository

    fun createBoard(projectId: Int): Message {
        val createTime = System.currentTimeMillis().toString()
        val newBoard = Board()
        newBoard.createTime = createTime

        val savedBoard = boardRepository.save(newBoard)
        val newBoardProjectRelation = BoardProjectRelation(projectId, savedBoard.id)
        boardProjectRelationRepository.save(newBoardProjectRelation)
        return Message(true, "board creat success")
}


    fun removeBoard(boardId: Int): Message {
        val deleteCardBoardList = cardBoardRelationRepository.findAllByBoardId(boardId)
        for (deleteCardBoard in deleteCardBoardList) {
            cardRepository.deleteById(deleteCardBoard.cardId!!)
        }

        boardRepository.deleteById(boardId)
        return Message(true, "board remove success")
    }
}