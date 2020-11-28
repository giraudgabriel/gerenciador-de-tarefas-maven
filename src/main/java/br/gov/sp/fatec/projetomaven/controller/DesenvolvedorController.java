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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Desenvolvedor desenvolvedor = mapper.readValue(req.getReader(), Desenvolvedor.class);

        DesenvolvedorDao desenvolvedorDao = new DesenvolvedorDaoJpa();
        desenvolvedorDao.salvar(desenvolvedor);

        String desenvolvedorJson = mapper.writeValueAsString(desenvolvedor);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.setStatus(201);

        String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/dev?id="
                + desenvolvedor.getId();

        resp.setHeader("Location", location);

        PrintWriter out = resp.getWriter();

        out.print(desenvolvedorJson);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        
        ObjectMapper mapper = new ObjectMapper();
        Desenvolvedor desenvolvedor = mapper.readValue(req.getReader(), Desenvolvedor.class);

        DesenvolvedorDao desenvolvedorDao = new DesenvolvedorDaoJpa();
        Desenvolvedor dev = desenvolvedorDao.buscarPorId(id);

        dev.setNome(desenvolvedor.getNome());
        dev.setNomeUsuario(desenvolvedor.getNomeUsuario());
        dev.setFuncoes(desenvolvedor.getFuncoes());
        dev.setSenha(desenvolvedor.getSenha());
        dev.setTarefas(desenvolvedor.getTarefas());

        desenvolvedorDao.salvar(dev);

        String desenvolvedorJson = mapper.writeValueAsString(dev);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.setStatus(200);

        PrintWriter out = resp.getWriter();

        out.print(desenvolvedorJson);
        out.flush();
    }
}