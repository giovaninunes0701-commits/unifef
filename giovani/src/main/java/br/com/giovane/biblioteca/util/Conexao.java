package br.com.giovane.biblioteca.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/";
    private String banco = "biblioteca";
    private String usuario = "postgres";
    private String senha = "postdba";

    public Connection conectar() {
        try {
            Class.forName(driver);
            validar();
            return DriverManager.getConnection(url + banco, usuario, senha);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void validar() {
        try (Connection c = DriverManager.getConnection(url + "postgres", usuario, senha);
             Statement s = c.createStatement()) {
            
            boolean existe = false;
            ResultSet rs = c.getMetaData().getCatalogs();
            while (rs.next()) {
                if (banco.equals(rs.getString(1))) {
                    existe = true;
                    break;
                }
            }
            
            if (!existe) {
                s.executeUpdate("CREATE DATABASE " + banco);
                
                try (Connection c2 = DriverManager.getConnection(url + banco, usuario, senha);
                     Statement s2 = c2.createStatement()) {
                    String sql = "CREATE TABLE IF NOT EXISTS livros_tb ("
                            + "id SERIAL PRIMARY KEY,"
                            + "titulo VARCHAR(255),"
                            + "isbn VARCHAR(50),"
                            + "autor VARCHAR(255),"
                            + "publicacao DATE,"
                            + "preco DECIMAL(10,2)"
                            + ");";
                    s2.execute(sql);
                }
            }
        } catch (Exception e) {
        }
    }
}
