package br.gov.sp.fatec.projetomaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.projetomaven.dao.DesenvolvedorDao;
import br.gov.sp.fatec.projetomaven.dao.DesenvolvedorDaoJpa;
import br.gov.sp.fatec.projetomaven.dao.UsuarioDao;
import br.gov.sp.fatec.projetomaven.dao.UsuarioDaoJpa;
import br.gov.sp.fatec.projetomaven.entity.Desenvolvedor;

public class AdminController extends HttpServlet {

    private static final long serialVersionUID = -6692052149075365360L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DesenvolvedorDao desenvolvedorDao = new DesenvolvedorDaoJpa();
        List<Desenvolvedor> desenvolvedores = desenvolvedorDao.buscarTodos();

        ObjectMapper mapper = new ObjectMapper();
        String desenvolvedorJson = mapper.writeValueAsString(desenvolvedores);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);

        PrintWriter out = resp.getWriter();
        out.print(desenvolvedorJson);
        out.flush();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Desenvolvedor desenvolvedor = mapper.readValue(req.getReader(), Desenvolvedor.class);


        DesenvolvedorDao desenvolvedorDao = new DesenvolvedorDaoJpa();
        desenvolvedor = desenvolvedorDao.login(desenvolvedor.getNomeUsuario(), desenvolvedor.getSenha());

        String desenvolvedorJson = mapper.writeValueAsString(desenvolvedor);
       
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.setStatus(200);
        PrintWriter out = resp.getWriter();
        out.print(desenvolvedorJson);
        out.flush();
    }
}