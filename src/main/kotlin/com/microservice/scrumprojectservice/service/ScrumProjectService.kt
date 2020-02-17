package com.microservice.scrumprojectservice.service

import com.microservice.scrumprojectservice.dto.Message
import com.microservice.scrumprojectservice.entity.ScrumProject
import com.microservice.scrumprojectservice.repostiry.ScrumProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScrumProjectService {
    @Autowired
    private lateinit var scrumProjectRepository: ScrumProjectRepository

    fun createScrumProject(newScrumProject: ScrumProject): Message {
        val updateTime = System.currentTimeMillis().toString()
        newScrumProject.createTime = updateTime

        val savedScrumProject = scrumProjectRepository.save(newScrumProject)
        return Message(true, savedScrumProject)
    }
}