package org.example.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Cliente {

    public static void main(String[] args) throws Exception {
        criarUsuario("marcella", "marcella@gmail.com", 16);
        listarUsuarios();
        buscarUsuarioPorEmail("marcella@gmail.com");
        consultarStatus();
    }

    public static void criarUsuario(String nome, String email, int idade) throws Exception {
        URL url = new URL("http://localhost:7000/usuarios");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        String json = "{\"nome\":\"" + nome + "\",\"email\":\"" + email + "\",\"idade\":" + idade + "}";
        try (OutputStream os = con.getOutputStream()) {
            os.write(json.getBytes());
        }

        System.out.println("Status criar: " + con.getResponseCode());
        con.disconnect();
    }

    public static void listarUsuarios() throws Exception {
        URL url = new URL("http://localhost:7000/usuarios");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        System.out.println("Status listar: " + con.getResponseCode());
        System.out.println("Resposta: " + ler(con));
        con.disconnect();
    }

    public static void buscarUsuarioPorEmail(String email) throws Exception {
        URL url = new URL("http://localhost:7000/usuarios/" + email);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        System.out.println("Status buscar: " + con.getResponseCode());
        System.out.println("Resposta: " + ler(con));
        con.disconnect();
    }

    public static void consultarStatus() throws Exception {
        URL url = new URL("http://localhost:7000/status");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        System.out.println("Status: " + con.getResponseCode());
        System.out.println("Resposta: " + ler(con));
        con.disconnect();
    }

    private static String ler(HttpURLConnection con) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = in.readLine()) != null) {
            sb.append(linha);
        }
        in.close();
        return sb.toString();
    }
}
