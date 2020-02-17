package com.microservice.scrumprojectservice.entity

import javax.persistence.*

@Entity
@Table(name = "card", schema = "public")
class Card() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null

    @Column(name = "title")
    var title: String? = null

    @Column(name = "description")
    var description: String? = null

    @Column(name = "story_points")
    var storyPoints: Int? = null

    @Column(name = "priority")
    var priority: String? = null

    @Column(name = "processor")
    var processor: String? = null

    @Column(name = "founder")
    var founder: String? = null

    @Column(name = "status")
    var status: String? = null

    @Column(name = "create_time")
    var createTime: String? = null

    constructor(title: String? = null,
                description: String? = null,
                storyPoints: Int? = null,
                priority: String? = null,
                processor: String? = null,
                founder: String? = null,
                status: String? = null,
                createTime: String? = null) : this() {

        this.title = title
        this.description = description
        this.storyPoints = storyPoints
        this.priority = priority
        this.processor = processor
        this.founder = founder
        this.status = status
        this.createTime = createTime
    }
}