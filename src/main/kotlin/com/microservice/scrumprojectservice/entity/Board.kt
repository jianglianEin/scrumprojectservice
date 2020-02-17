package com.microservice.scrumprojectservice.entity


import javax.persistence.*


@Entity
@Table(name = "board", schema = "public")
class Board() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null

    @Column(name = "create_time")
    var createTime: String? = null


    constructor(createTime: String? = null) : this() {
        this.createTime = createTime
    }
}