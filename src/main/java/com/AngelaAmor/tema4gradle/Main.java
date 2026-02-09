package com.AngelaAmor.tema4gradle;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    final String TOKEN = "PEGA_AQUI_TU_TOKEN";
    var model = OpenAiChatModel.builder()
            .baseUrl("http://localhost:11434/v1")
            .apiKey(TOKEN)
            .modelName("llama3.1:8b")
            .build();

    List<ChatMessage> history = new ArrayList<>();

    // Interacción 1
    history.add(new UserMessage("Hola, soy Ángela"));
    AiMessage respuesta = model.chat(history).aiMessage();
    history.add(respuesta);

    // Interacción 2
    history.add(new UserMessage("¿Recuerdas cómo me llamo?"));
    respuesta = model.chat(history).aiMessage();
    history.add(respuesta);

    System.out.println(respuesta.text());

    }
  }
