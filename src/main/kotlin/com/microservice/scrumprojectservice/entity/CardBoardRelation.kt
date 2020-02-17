package com.microservice.scrumprojectservice.entity

import javax.persistence.*

@Entity
@Table(name = "card_board_relation", schema = "public")
class CardBoardRelation() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null

    @Column(name = "card_id")
    var cardId: Int? = null

    @Column(name = "board_id")
    var boardId: Int? = null

    constructor(cardId: Int? = null,
                boardId: Int? = null) : this() {
        this.cardId = cardId
        this.boardId = boardId
    }
}