package com.microservice.scrumprojectservice.entity


import com.vladmihalcea.hibernate.type.array.IntArrayType
import com.vladmihalcea.hibernate.type.array.StringArrayType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import javax.persistence.*


@TypeDefs(TypeDef(name = "string-array", typeClass = StringArrayType::class), TypeDef(name = "int-array", typeClass = IntArrayType::class))
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

    @Column(name = "create_time")
    var createTime: String? = null

    @Type( type = "string-array" )
    @Column(name = "col", columnDefinition = "varchar(50)[]")
    var colTitle: MutableList<String>? = null

    @Type( type = "string-array" )
    @Column(name = "row", columnDefinition = "varchar(50)[]")
    var rowTitle: MutableList<String>? = null

    @Column(name = "iteration")
    var iteration: Int? = 14

    constructor(projectName: String? = null,
                teamId: Int? = null,
                creator: String? = null,
                createTime: String? = null) : this() {
        this.projectName = projectName
        this.teamId = teamId
        this.creator = creator
        this.createTime = createTime
    }
}