package br.gov.sp.fatec.projetomaven.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class LogFilter implements Filter {

    private ServletContext context;
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        this.context.log("Filtro acessado!");
        this.context.log(req.toString());

        HttpServletResponse resp = (HttpServletResponse) res;

        resp.addHeader("In", "Eu passei pelo filtro na entrada!");
        resp.addHeader("Usuario", "Usuario");
        chain.doFilter(req, res);

        resp.addHeader("Out", "Eu passei pelo filtro na saída!");
        this.context.log("Resposta enviada!");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("Filtro inicializado!");
    }
    
}