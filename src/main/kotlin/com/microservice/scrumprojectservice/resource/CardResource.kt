package com.microservice.scrumprojectservice.resource

import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.Card
import com.microservice.scrumprojectservice.service.CardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CardResource {
    @Autowired
    private lateinit var cardService: CardService

    @PostMapping("/card/create")
    fun createCard(@RequestParam title: String,
                   @RequestParam description: String?,
                   @RequestParam storyPoints: Int?,
                   @RequestParam priority: String?,
                   @RequestParam processor: String?,
                   @RequestParam founder: String,
                   @RequestParam status: String,
                   @RequestParam boardId: Int): Message {
        val newCard = Card(title, description, storyPoints, priority, processor, founder, status)

        return cardService.createCard(newCard, boardId)
    }

    @PostMapping("/card/update")
    fun updateCard(@RequestParam cardId: Int,
                   @RequestParam title: String?,
                   @RequestParam description: String?,
                   @RequestParam storyPoints: Int?,
                   @RequestParam priority: String?,
                   @RequestParam processor: String?,
                   @RequestParam status: String?): Message {
        val updateCard = Card(title = title, description = description, storyPoints = storyPoints,
                priority = priority, processor = processor, status = status)
        updateCard.id = cardId

        return cardService.updateCard(updateCard)
    }

    @PostMapping("/card/remove")
    fun removeCard(@RequestParam cardId: Int): Message {

        return cardService.removeCard(cardId)
    }

    @PostMapping("/card/selectByBoard")
    fun selectCardsByBoard(@RequestParam boardId: Int): MutableList<Card> {

        return cardService.selectCardsByBoard(boardId)
    }
}