package org.example.controller;

import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class EchoController {
    public static void echo(Context ctx) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> data = mapper.readValue(ctx.body(), Map.class);
            String mensagem = data.get("mensagem");
            ctx.json(Map.of("mensagem", mensagem));
        } catch (Exception e) {
            ctx.status(400).result("Erro ao processar JSON.");
        }
    }
}