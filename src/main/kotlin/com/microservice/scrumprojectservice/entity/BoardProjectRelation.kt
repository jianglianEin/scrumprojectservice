package com.microservice.scrumprojectservice.entity

import javax.persistence.*

@Entity
@Table(name = "board_project_relation", schema = "public")
class BoardProjectRelation() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null

    @Column(name = "project_id")
    var projectId: Int? = null

    @Column(name = "board_id")
    var boardId: Int? = null

    constructor(projectId: Int? = null,
                boardId: Int? = null) : this() {
        this.projectId = projectId
        this.boardId = boardId
    }
}