package br.gov.sp.fatec.projetomaven.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private static final long serialVersionUID = -7802229025454480671L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Main", "MainController");
        resp.getWriter().print("Bem vindo," + resp.getHeader("Usuario") + " !");
    }

}