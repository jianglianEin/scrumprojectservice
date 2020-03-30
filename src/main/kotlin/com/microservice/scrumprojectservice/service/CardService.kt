package com.microservice.scrumprojectservice.service

import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.Card
import com.microservice.scrumprojectservice.entity.CardBoardRelation
import com.microservice.scrumprojectservice.entity.CardPos
import com.microservice.scrumprojectservice.repostiry.BoardProjectRelationRepository
import com.microservice.scrumprojectservice.repostiry.CardBoardRelationRepository
import com.microservice.scrumprojectservice.repostiry.CardRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CardService {
    @Autowired
    private lateinit var cardRepository: CardRepository
    @Autowired
    private lateinit var cardBoardRelationRepository: CardBoardRelationRepository
    @Autowired
    private lateinit var boardProjectRelationRepository: BoardProjectRelationRepository

    private var logger = KotlinLogging.logger {}

    fun createCard(newCard: Card, boardId: Int): Card {
        val updateTime = System.currentTimeMillis().toString()
        newCard.createTime = updateTime

        val savedCard = cardRepository.save(newCard)
        val newCardBoardRelation = CardBoardRelation(savedCard.id, boardId)
        cardBoardRelationRepository.save(newCardBoardRelation)

        logger.info { "card create success" }
        return savedCard
    }

    fun updateCard(updateCard: Card): Card {
        val oldCardOption = cardRepository.findById(updateCard.id!!)
        if (oldCardOption.isPresent) {
            val oldCard = oldCardOption.get()

            if (updateCard.title != null) oldCard.title = updateCard.title
            if (updateCard.description != null) oldCard.description = updateCard.description
            if (updateCard.storyPoints != null) oldCard.storyPoints = updateCard.storyPoints
            if (updateCard.priority != null) oldCard.priority = updateCard.priority
            if (updateCard.processor != null) oldCard.processor = updateCard.processor
            if (updateCard.status != null) oldCard.status = updateCard.status

            logger.info { "card updating..." }

            return cardRepository.save(oldCard)
        }

        logger.warn { "no this card" }
        return Card()
    }

    fun removeCard(cardId: Int): Message {
        cardRepository.deleteById(cardId)
        return Message(true, "card remove success")
    }

    fun selectCardsByBoard(boardId: Int): MutableList<Card> {
        val cardBoardRelationList = cardBoardRelationRepository.findAllByBoardId(boardId)
        val cardList = mutableListOf<Card>()

        cardBoardRelationList.map {
            val cardOptional = cardRepository.findById(it.cardId!!)
            if (cardOptional.isPresent) {
                cardList.add(cardOptional.get())
            }
        }
        return cardList
    }

    fun selectCardPosById(cardId: Int): CardPos {
        val cardBoardRelation = cardBoardRelationRepository.findByCardId(cardId)
        val boardProjectRelation = boardProjectRelationRepository.findByBoardId(cardBoardRelation.boardId!!)

        return CardPos(cardId = cardId.toString(),
                boardId = cardBoardRelation.boardId.toString(),
                projectId = boardProjectRelation.projectId.toString())
    }
}