package com.tutorial.tutorial.Controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.NoHandlerFoundException

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
class Controller(private val jdbcTemplate: JdbcTemplate){

    @RequestMapping("/hello")
    fun hello(): String {
        return "Hello, World!"
    }

    @RequestMapping("/api/login")
    fun login(@RequestParam("user") user: String, @RequestParam("password") password: String): Map<String,String>{
        val query = "SELECT * FROM users WHERE username = ? AND pass = ?"
        val result = jdbcTemplate.queryForList(query, user, password)
        return if(result.isEmpty()) return mapOf("status" to "Failed") else mapOf("status" to "Successful")
    }

    @GetMapping("/error")
    fun error(): ResponseEntity<String> {
        return ResponseEntity("Error occurred", HttpStatus.BAD_REQUEST)
        
    }


    // { "status": "Failed"} {"Status": "Successful" }
}