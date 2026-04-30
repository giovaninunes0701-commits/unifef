package br.com.giovane.biblioteca.dao;

import br.com.giovane.biblioteca.model.Livro;
import br.com.giovane.biblioteca.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private Conexao con;

    public LivroDAO() {
        this.con = new Conexao();
    }

    public void insert(Livro l) {
        String sql = "INSERT INTO livros_tb (titulo, isbn, autor, publicacao, preco) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = con.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, l.getNomeLivro());
            ps.setString(2, l.getIsbn());
            ps.setString(3, l.getAutor());
            ps.setDate(4, Date.valueOf(l.getDataPublicacao()));
            ps.setDouble(5, l.getValorLivro());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Livro l) {
        String sql = "UPDATE livros_tb SET titulo = ?, isbn = ?, autor = ?, publicacao = ?, preco = ? WHERE id = ?";
        try (Connection c = con.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, l.getNomeLivro());
            ps.setString(2, l.getIsbn());
            ps.setString(3, l.getAutor());
            ps.setDate(4, Date.valueOf(l.getDataPublicacao()));
            ps.setDouble(5, l.getValorLivro());
            ps.setInt(6, l.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM livros_tb WHERE id = ?";
        try (Connection c = con.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> list() {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM livros_tb ORDER BY id";
        try (Connection c = con.conectar();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Livro l = new Livro();
                l.setId(rs.getInt("id"));
                l.setNomeLivro(rs.getString("titulo"));
                l.setIsbn(rs.getString("isbn"));
                l.setAutor(rs.getString("autor"));
                Date d = rs.getDate("publicacao");
                if (d != null) l.setDataPublicacao(d.toLocalDate());
                l.setValorLivro(rs.getDouble("preco"));
                lista.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Livro getById(int id) {
        String sql = "SELECT * FROM livros_tb WHERE id = ?";
        try (Connection c = con.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Livro l = new Livro();
                    l.setId(rs.getInt("id"));
                    l.setNomeLivro(rs.getString("titulo"));
                    l.setIsbn(rs.getString("isbn"));
                    l.setAutor(rs.getString("autor"));
                    Date d = rs.getDate("publicacao");
                    if (d != null) l.setDataPublicacao(d.toLocalDate());
                    l.setValorLivro(rs.getDouble("preco"));
                    return l;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
