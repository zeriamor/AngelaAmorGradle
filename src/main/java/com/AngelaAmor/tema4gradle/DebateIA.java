package com.AngelaAmor.tema4gradle;

import dev.langchain4j.data.message.*;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.ArrayList;
import java.util.List;

public class DebateIA {
  public static void main(String[] args) {
    String modelo = "gemma:2b";
    String tema="¿Cuál es la mejor manera de invertir?";

    //INICIALIZAR
    OpenAiChatModel modeloChat= OpenAiChatModel.builder()
            .baseUrl("http://localhost:11434/v1")
            .apiKey("no se precisa")
            .modelName("gemma:2b")
            .build();
    System.out.println("***** Debate entre dos IAs *****");
    System.out.println("modelo >>>"+modelo);
    System.out.println(">>>> "+tema+"\n*********************************************");

    //IA 1 pregunta
    List<ChatMessage> historial = new ArrayList<>();
    historial.add(new SystemMessage("Eres un experto en criptomonedas"));
    historial.add(new UserMessage("haz una pregunta sobre " + tema));

    AiMessage pregunta = modeloChat.chat(historial).aiMessage();
    System.out.println("IA 1 pregunta: " + pregunta.text());
    historial.add(pregunta);

    // IA 2 responde
    historial = new ArrayList<>();
    historial.add(new SystemMessage("Eres un experto en ETFs e Inversiones en general"));
    historial.add(new UserMessage("Responde a esta pregunta: " + pregunta));
    AiMessage respuesta = modeloChat.chat(historial).aiMessage();
    System.out.println("IA 2 responde: " + respuesta.text());

  }
}
