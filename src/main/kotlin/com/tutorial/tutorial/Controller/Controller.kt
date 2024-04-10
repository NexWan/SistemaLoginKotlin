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

    @RequestMapping("/api/login")
    fun login(@RequestParam("user") user: String, @RequestParam("password") password: String, request: HttpServletRequest): Map<String,String>{
        val query = "SELECT * FROM \"Users\" WHERE username = ? AND password = ?"
        val result = jdbcTemplate.queryForList(query, user.lowercase(), password)
        request.session.setAttribute("user", null)
        val response = if(result.isEmpty())
            mapOf("status" to "Failed")
        else{
            request.session.setAttribute("user", user)
            mapOf("status" to "Successful")
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