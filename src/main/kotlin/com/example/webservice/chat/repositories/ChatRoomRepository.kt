package com.example.webservice.chat.repositories

import com.example.webservice.chat.models.entities.ChatRoom
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ChatRoomRepository : JpaRepository<ChatRoom, Long> {

    @Query("SELECT cr FROM ChatRoom cr WHERE (:q IS NULL OR cr.title LIKE %:q%) AND cr.deleted=false")
    fun search(@Param("q") query: String?, pageable: Pageable): Page<ChatRoom>

    @Query("SELECT cr FROM ChatRoom cr WHERE cr.id=:id AND cr.deleted=false")
    fun find(@Param("id") id: Long): Optional<ChatRoom>

}