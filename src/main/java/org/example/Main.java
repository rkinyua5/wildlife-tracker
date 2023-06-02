package org.example;
import static spark.Spark.*;

public class Main {
    private static final String port = System.getenv("PORT");

    public static void main(String[] args) {
        port(port == null ? 8000 : Integer.parseInt(port));
        get("/", (req, res) -> {
            return "Setup";
        });
    }
}