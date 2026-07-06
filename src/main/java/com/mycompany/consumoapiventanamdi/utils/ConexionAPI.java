package com.mycompany.consumoapiventanamdi.utils;

import com.mycompany.consumoapiventanamdi.model.Post;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConexionAPI {

    // URL estática de la API
    private static final String URL_API = "https://jsonplaceholder.typicode.com/posts";

    public List<Post> obtenerPosts() {
        List<Post> listaPosts = new ArrayList<>();

        try {
            // 1. Establecer la conexión
            URL url = new URL(URL_API);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Validar que la petición fue exitosa (Código 200)
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error HTTP: " + conn.getResponseCode());
            }

            // 2. Leer la respuesta
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder respuestaJson = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                respuestaJson.append(linea);
            }
            conn.disconnect();

            // 3. Parsear el JSON y convertirlo a objetos Post
            JSONArray jsonArray = new JSONArray(respuestaJson.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Post post = new Post();
                post.setUserId(jsonObject.getInt("userId"));
                post.setId(jsonObject.getInt("id"));
                post.setTitle(jsonObject.getString("title"));
                post.setBody(jsonObject.getString("body"));

                listaPosts.add(post);
            }

        } catch (Exception e) {
            System.err.println("Error al consumir la API: " + e.getMessage());
        }

        return listaPosts;
    }
}
