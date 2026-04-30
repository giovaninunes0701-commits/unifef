package br.com.giovane.biblioteca.controller;

import br.com.giovane.biblioteca.dao.LivroDAO;
import br.com.giovane.biblioteca.model.Livro;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "BibliotecaServlet", urlPatterns = {"/livros", "/livros/novo", "/livros/salvar", "/livros/editar", "/livros/excluir"})
public class BibliotecaServlet extends HttpServlet {

    private LivroDAO dao;

    @Override
    public void init() {
        dao = new LivroDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        if (path.equals("/livros/novo")) {
            req.getRequestDispatcher("/livro-form.jsp").forward(req, resp);
        } else if (path.equals("/livros/editar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Livro l = dao.getById(id);
            req.setAttribute("livro", l);
            req.getRequestDispatcher("/livro-form.jsp").forward(req, resp);
        } else if (path.equals("/livros/excluir")) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/livros");
        } else {
            List<Livro> lista = dao.list();
            req.setAttribute("livros", lista);
            req.getRequestDispatcher("/livro-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();

        if (path.equals("/livros/salvar")) {
            String id = req.getParameter("id");
            String nome = req.getParameter("nomeLivro");
            String isbn = req.getParameter("isbn");
            String autor = req.getParameter("autor");
            String data = req.getParameter("dataPublicacao");
            String valor = req.getParameter("valorLivro").replace(",", ".");

            Livro l = new Livro();
            if (id != null && !id.isEmpty()) {
                l.setId(Integer.parseInt(id));
            }
            l.setNomeLivro(nome);
            l.setIsbn(isbn);
            l.setAutor(autor);
            l.setDataPublicacao(LocalDate.parse(data));
            l.setValorLivro(Double.parseDouble(valor));

            if (l.getId() == null) {
                dao.insert(l);
            } else {
                dao.update(l);
            }

            resp.sendRedirect(req.getContextPath() + "/livros");
        }
    }
}
