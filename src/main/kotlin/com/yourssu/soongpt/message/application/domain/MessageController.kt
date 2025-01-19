package com.yourssu.soongpt.message.application.domain

import com.yourssu.soongpt.common.application.domain.common.Response
import com.yourssu.soongpt.message.business.domain.MessageCreatedCommand
import com.yourssu.soongpt.message.business.domain.MessageResponse
import com.yourssu.soongpt.message.business.domain.MessageService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/messages")
class MessageController(
    private val messageService: MessageService,
) {
    @PostMapping
    fun create(@Valid @RequestBody request: MessageCreateRequest): ResponseEntity<Response<MessageResponse>> {
        val response = messageService.create(request.toCommand())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(Response(result = response))
    }

    @GetMapping("/{messageId}")
    fun find(@PathVariable @NotNull messageId: Long): ResponseEntity<Response<MessageResponse>> {
        val response = messageService.find(messageId)
        return ResponseEntity.ok(Response(result = response))
    }

    @GetMapping
    fun findAll(): ResponseEntity<Response<List<MessageResponse>>> {
        val response = messageService.findAll()
        return ResponseEntity.ok(Response(result = response))
    }
}

data class MessageCreateRequest(
    @NotBlank
    val content: String,
) {
    fun toCommand(): MessageCreatedCommand {
        return MessageCreatedCommand(
            content = content
        )
    }
}