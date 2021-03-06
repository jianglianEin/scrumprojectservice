package com.microservice.scrumprojectservice.repostiry

import com.microservice.scrumprojectservice.entity.CardBoardRelation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardBoardRelationRepository: JpaRepository<CardBoardRelation, Int> {
    fun findAllByBoardId(boardId: Int): MutableList<CardBoardRelation>
    fun findByCardId(cardId: Int): CardBoardRelation
}