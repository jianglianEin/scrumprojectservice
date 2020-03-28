package com.microservice.scrumprojectservice.service

import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.Board
import com.microservice.scrumprojectservice.entity.BoardProjectRelation
import com.microservice.scrumprojectservice.repostiry.BoardProjectRelationRepository
import com.microservice.scrumprojectservice.repostiry.BoardRepository
import com.microservice.scrumprojectservice.repostiry.CardBoardRelationRepository
import com.microservice.scrumprojectservice.repostiry.CardRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    private var logger = KotlinLogging.logger {}

    @Transactional
    fun createBoard(projectId: Int): Board {
        val createTime = System.currentTimeMillis().toString()
        val newBoard = Board()
        newBoard.createTime = createTime

        val savedBoard = boardRepository.save(newBoard)
        val newBoardProjectRelation = BoardProjectRelation(projectId, savedBoard.id)
        boardProjectRelationRepository.save(newBoardProjectRelation)

        logger.info { "board create success" }

        return savedBoard
}


    fun removeBoard(boardId: Int): Message {
        val deleteCardBoardList = cardBoardRelationRepository.findAllByBoardId(boardId)
        for (deleteCardBoard in deleteCardBoardList) {
            cardRepository.deleteById(deleteCardBoard.cardId!!)
        }

        boardRepository.deleteById(boardId)
        return Message(true, "board remove success")
    }

    fun selectBoardsByProject(projectId: Int): MutableList<Board> {
        val boardProjectRelationList = boardProjectRelationRepository.findAllByProjectId(projectId)
        val boardList = mutableListOf<Board>()

        boardProjectRelationList.map {
            val boardOptional = boardRepository.findById(it.boardId!!)
            if (boardOptional.isPresent) {
                boardList.add(boardOptional.get())
            }
        }
        return boardList
    }
}