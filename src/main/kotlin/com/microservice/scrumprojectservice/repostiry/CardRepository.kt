package com.microservice.scrumprojectservice.repostiry

import com.microservice.scrumprojectservice.entity.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository: JpaRepository<Card, Int> {
}