package com.litsynp.application.domain.test.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/test")
class TestController {

    @GetMapping("/all")
    fun allAccess(): ResponseEntity<String> {
        return ResponseEntity.ok("User content.")
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun userAccess(): ResponseEntity<String> {
        return ResponseEntity.ok("User content.")
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun adminAccess(): ResponseEntity<String> {
        return ResponseEntity.ok("User content.")
    }
}
