package com.microservice.scrumprojectservice.repostiry

import com.microservice.scrumprojectservice.entity.BoardProjectRelation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardProjectRelationRepository: JpaRepository<BoardProjectRelation, Int> {
    fun findAllByProjectId(projectId: Int): MutableList<BoardProjectRelation>
    fun findByBoardId(boardId: Int): BoardProjectRelation
}