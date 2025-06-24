package org.example.controller;

import io.javalin.http.Context;
import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UsuarioController {

    public static void hello(Context ctx) {
        ctx.result("Hello, Javalin!");
    }

    public static void status(Context ctx) {
        Map<String, String> map = new HashMap<>();
        map.put("status", "ok");
        map.put("timestamp", new Date().toString());
        ctx.json(map);
    }

    public static void saudacao(Context ctx) {
        String nome = ctx.pathParam("nome");
        ctx.json(Map.of("mensagem", "Olá, " + nome + "!"));
    }

    public static void criar(Context ctx) {
        Usuario u = ctx.bodyAsClass(Usuario.class);
        UsuarioRepository.adicionar(u);
        ctx.status(201).json(u);
    }

    public static void listar(Context ctx) {
        ctx.json(UsuarioRepository.listar());
    }

    public static void buscarPorEmail(Context ctx) {
        String email = ctx.pathParam("email");
        Usuario u = UsuarioRepository.buscarPorEmail(email);
        if (u != null) {
            ctx.json(u);
        } else {
            ctx.status(404);
        }
    }
}
