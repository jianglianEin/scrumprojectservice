package com.microservice.scrumprojectservice.repostiry

import com.microservice.scrumprojectservice.entity.ScrumProject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScrumProjectRepository: JpaRepository<ScrumProject, Int> {
    fun findAllByCreator(creator: String) :MutableList<ScrumProject>
    fun findAllByTeamId(teamId: String): MutableList<ScrumProject>
}