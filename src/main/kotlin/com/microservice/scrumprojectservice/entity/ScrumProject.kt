package com.microservice.scrumprojectservice.entity

import javax.persistence.*


@Entity
@Table(name = "scrum_project", schema = "public")
class ScrumProject() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null

    @Column(name = "project_name")
    var projectName: String? = null

    @Column(name = "creator")
    var creator: String? = null

    @Column(name = "team_id")
    var teamId: Int? = null

    @Column(name = "board_string")
    var boardString: String? = null

    @Column(name = "update_time")
    var updateTime: String? = null

    constructor(projectName: String? = null,
                teamId: Int? = null,
                creator: String? = null,
                updateTime: String? = null,
                boardString: String? = null ) : this() {
        this.projectName = projectName
        this.teamId = teamId
        this.creator = creator
        this.updateTime = updateTime
        this.boardString = boardString
    }
}