package com.microservice.scrumprojectservice.service

import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.ScrumProject
import com.microservice.scrumprojectservice.repostiry.*
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScrumProjectService {
    @Autowired
    private lateinit var scrumProjectRepository: ScrumProjectRepository
    @Autowired
    private lateinit var boardProjectRelationRepository: BoardProjectRelationRepository
    @Autowired
    private lateinit var boardRepository: BoardRepository
    @Autowired
    private lateinit var cardBoardRelationRepository: CardBoardRelationRepository
    @Autowired
    private lateinit var cardRepository: CardRepository

    private var logger = KotlinLogging.logger {}

    fun createScrumProject(newScrumProject: ScrumProject): ScrumProject {
        val updateTime = System.currentTimeMillis().toString()
        newScrumProject.createTime = updateTime
        logger.info { "project create...." }

        return scrumProjectRepository.save(newScrumProject)
}

    fun updateScrumProject(updateScrumProject: ScrumProject): ScrumProject {
        val oldProjectOption = scrumProjectRepository.findById(updateScrumProject.id!!)
        if (oldProjectOption.isPresent) {
            val oldProject = oldProjectOption.get()
            if (updateScrumProject.projectName != null) oldProject.projectName = updateScrumProject.projectName
            if (updateScrumProject.teamId != null) oldProject.teamId = updateScrumProject.teamId
            if (updateScrumProject.colTitle != null) oldProject.colTitle = updateScrumProject.colTitle
            if (updateScrumProject.rowTitle != null) oldProject.rowTitle = updateScrumProject.rowTitle
            if (updateScrumProject.iteration != null) oldProject.iteration = updateScrumProject.iteration

            logger.info { "project updating ...." }
            return scrumProjectRepository.save(oldProject)
        }

        logger.warn { "no this project" }
        return ScrumProject()
    }

    fun removeScrumProject(projectId: Int): Message {
        val deleteBoardProjectList = boardProjectRelationRepository.findAllByProjectId(projectId)
        for (deleteBoardProject in deleteBoardProjectList) {
            val deleteCardBoardList = cardBoardRelationRepository.findAllByBoardId(deleteBoardProject.boardId!!)
            for (deleteCardBoard in deleteCardBoardList) {
                cardRepository.deleteById(deleteCardBoard.cardId!!)
            }

            boardRepository.deleteById(deleteBoardProject.boardId!!)
        }
        scrumProjectRepository.deleteById(projectId)
        return Message(true, "project remove success")
    }

    fun selectProjectByCreator(creator: String): MutableList<ScrumProject> {
        val projectList = scrumProjectRepository.findAllByCreator(creator)

        return projectList
    }

    fun selectProjectById(projectId: Int): ScrumProject {
        val projectOptional = scrumProjectRepository.findById(projectId)
        if (projectOptional.isPresent){
            return projectOptional.get()
        }
        return ScrumProject()
    }

    fun selectProjectByTeamId(teamId: String): MutableList<ScrumProject> {
        return scrumProjectRepository.findAllByTeamId(teamId)
    }
}