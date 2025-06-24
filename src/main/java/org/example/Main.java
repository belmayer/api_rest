package org.example;

import io.javalin.Javalin;
import org.example.controller.UsuarioController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(c -> {
            c.http.defaultContentType = "application/json";
        }).start(7000);

        app.get("/hello", UsuarioController::hello);
        app.get("/status", UsuarioController::status);
        app.get("/saudacao/{nome}", UsuarioController::saudacao);
        app.post("/usuarios", UsuarioController::criar);
        app.get("/usuarios", UsuarioController::listar);
        app.get("/usuarios/{email}", UsuarioController::buscarPorEmail);
    }
}
