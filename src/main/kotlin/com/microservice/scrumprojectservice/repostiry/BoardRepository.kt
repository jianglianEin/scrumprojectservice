package com.microservice.scrumprojectservice.repostiry

import com.microservice.scrumprojectservice.entity.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository: JpaRepository<Board, Int> {
}