package com.microservice.scrumprojectservice.service

import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.Card
import com.microservice.scrumprojectservice.entity.CardBoardRelation
import com.microservice.scrumprojectservice.repostiry.CardBoardRelationRepository
import com.microservice.scrumprojectservice.repostiry.CardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CardService {
    @Autowired
    private lateinit var cardRepository: CardRepository
    @Autowired
    private lateinit var cardBoardRelationRepository: CardBoardRelationRepository

    fun createCard(newCard: Card, boardId: Int): Message {
        val updateTime = System.currentTimeMillis().toString()
        newCard.createTime = updateTime

        val savedCard = cardRepository.save(newCard)
        val newCardBoardRelation = CardBoardRelation(savedCard.id, boardId)
        cardBoardRelationRepository.save(newCardBoardRelation)
        return Message(true, savedCard)
    }

    fun updateCard(updateCard: Card): Message {
        val oldCardOption = cardRepository.findById(updateCard.id!!)
        if (oldCardOption.isPresent) {
            val oldCard = oldCardOption.get()

            if (updateCard.title != null) oldCard.title = updateCard.title
            if (updateCard.description != null) oldCard.description = updateCard.description
            if (updateCard.storyPoints != null) oldCard.storyPoints = updateCard.storyPoints
            if (updateCard.priority != null) oldCard.priority = updateCard.priority
            if (updateCard.processor != null) oldCard.processor = updateCard.processor
            if (updateCard.status != null) oldCard.status = updateCard.status

            val savedCard = cardRepository.save(oldCard)
            return Message(true, savedCard)

        }
        return Message(false, "no this card")
    }

    fun removeCard(cardId: Int): Message {
        cardRepository.deleteById(cardId)
        return Message(true, "card remove success")
    }
}