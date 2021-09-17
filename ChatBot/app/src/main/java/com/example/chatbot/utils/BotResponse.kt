package com.example.chatbot.utils

import com.example.chatbot.utils.Constants.OPEN_GOOGLE
import com.example.chatbot.utils.Constants.OPEN_SEARCH
import java.lang.Exception

object BotResponse {

    fun basicResponses(_message:String):String{

        val random = (0..2).random()
        val message = _message.lowercase()

        return when{

            message.contains("hello") -> {
                when(random){
                    0 -> "Hello there"
                    1 -> "Hello!!!"
                    2 -> "Hi!!"
                    else -> "error"
                }
            }

            message.contains("how are you") -> {
                when(random){
                    0 -> "I am doing good, thanks for asking"
                    1 -> "Fine. How about you?"
                    2 -> "Pretty good"
                    else -> "error"
                }
            }

            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if(r ==0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            message.contains("solve") -> {
                val equation:String? = message.substringAfter("solve")

                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    answer.toString()
                }catch (e:Exception){
                    "Sorry, I cant solve that!"
                }
            }

            message.contains("time") && message.contains("?") -> {
                Time.timestamp()
            }

            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }

            message.contains("search") -> {
                OPEN_SEARCH
            }

            else -> {
                when(random){
                    0 -> "I don't understand"
                    1 -> "I dont know"
                    2 -> "Try asking me something different"
                    else -> "error"
                }
            }

        }
    }
}