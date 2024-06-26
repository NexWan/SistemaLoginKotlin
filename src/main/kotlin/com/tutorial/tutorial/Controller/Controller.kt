package com.tutorial.tutorial.Controller

import jakarta.servlet.http.HttpServletRequest
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

    @PostMapping(value = ["/api/login"], consumes = ["application/json"])
    fun login(@RequestBody user:User,request: HttpServletRequest): Map<String,String>{
        val username = user.username
        val password = user.password
        val query = "SELECT * FROM \"Users\" WHERE username = ? AND password = ?"
        val result = jdbcTemplate.queryForList(query, username.lowercase(), password)
        request.session.setAttribute("user", null)
        val response = if(result.isEmpty())
            mapOf("status" to "Failed", "user" to "")
        else{
            request.session.setAttribute("user", user)
            mapOf("status" to "Successful", "user" to user)
        }
        return response
    }

    @GetMapping("/error")
    fun error(): ResponseEntity<String> {
        return ResponseEntity("Error occurred", HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/api/verifySession")
    fun verifySession(request: HttpServletRequest): Map<String, String>{
        val user = request.session.getAttribute("user")
        return if(user == null) mapOf("status" to "Failed") else mapOf("status" to "Successful")
    }

    @GetMapping("/api/logout")
    fun logout(request: HttpServletRequest): Map<String, String>{
        request.session.setAttribute("user", null)
        return mapOf("status" to "Successful")
    }
    // { "status": "Failed"} {"Status": "Successful" }
}

class User(val username: String, val password: String)