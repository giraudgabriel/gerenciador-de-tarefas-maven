package br.gov.sp.fatec.projetomaven.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.projetomaven.dao.DesenvolvedorDao;
import br.gov.sp.fatec.projetomaven.dao.DesenvolvedorDaoJpa;
import br.gov.sp.fatec.projetomaven.entity.Desenvolvedor;

public class DesenvolvedorController extends HttpServlet {
     
    private static final long serialVersionUID = -6692052149075365360L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        DesenvolvedorDao desenvolvedorDao = new DesenvolvedorDaoJpa();
        Desenvolvedor desenvolvedor = desenvolvedorDao.buscarPorId(id);

        ObjectMapper mapper = new ObjectMapper();
        String desenvolvedorJson = mapper.writeValueAsString(desenvolvedor);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);

        PrintWriter out = resp.getWriter();

        out.print(desenvolvedorJson);
        out.flush();
    }
}