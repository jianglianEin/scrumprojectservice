package com.microservice.scrumprojectservice.service

import com.microservice.scrumprojectservice.entity.ScrumProject
import com.microservice.scrumprojectservice.repostiry.ScrumProjectRepostiry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScrumProjectService {
    @Autowired
    private lateinit var scrumProjectRepostiry: ScrumProjectRepostiry

    fun createScrumProject(scrumProject: ScrumProject) {

    }
}