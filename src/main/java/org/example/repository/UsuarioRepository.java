package org.example.repository;

import org.example.model.Usuario;
import java.util.*;

public class UsuarioRepository {
    public static List<Usuario> usuarios = new ArrayList<>();

    public static void adicionar(Usuario u) {
        usuarios.add(u);
    }

    public static List<Usuario> listar() {
        return usuarios;
    }

    public static Usuario buscarPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.email.equals(email)) {
                return u;
            }
        }
        return null;
    }
}
