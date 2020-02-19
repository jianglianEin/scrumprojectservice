package com.microservice.scrumprojectservice.service

import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.ScrumProject
import com.microservice.scrumprojectservice.repostiry.*
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

    fun createScrumProject(newScrumProject: ScrumProject): Message {
        val updateTime = System.currentTimeMillis().toString()
        newScrumProject.createTime = updateTime

        val savedScrumProject = scrumProjectRepository.save(newScrumProject)
        return Message(true, savedScrumProject)
}

    fun updateScrumProject(updateScrumProject: ScrumProject): Message {
        val oldProjectOption = scrumProjectRepository.findById(updateScrumProject.id!!)
        if (oldProjectOption.isPresent) {
            val oldProject = oldProjectOption.get()
            if (updateScrumProject.projectName != null) oldProject.projectName = updateScrumProject.projectName
            if (updateScrumProject.teamId != null) oldProject.teamId = updateScrumProject.teamId
            if (updateScrumProject.colTitle != null) oldProject.colTitle = updateScrumProject.colTitle
            if (updateScrumProject.rowTitle != null) oldProject.rowTitle = updateScrumProject.rowTitle
            if (updateScrumProject.iteration != null) oldProject.iteration = updateScrumProject.iteration

            val savedScrumProject = scrumProjectRepository.save(oldProject)
            return Message(true, savedScrumProject)

        }
        return Message(false, "no this project")
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
}