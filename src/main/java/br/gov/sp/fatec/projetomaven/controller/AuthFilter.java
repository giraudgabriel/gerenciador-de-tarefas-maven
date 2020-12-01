package br.gov.sp.fatec.projetomaven.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.gov.sp.fatec.projetomaven.dao.UsuarioDao;
import br.gov.sp.fatec.projetomaven.dao.UsuarioDaoJpa;
import br.gov.sp.fatec.projetomaven.entity.Usuario;

public class AuthFilter implements Filter {

    private ServletContext context;
    private String realm = "PROTECTED";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        this.context.log("Filtro acessado!");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // Verifica se tem o header Authorization
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            StringTokenizer st = new StringTokenizer(authHeader);
            if (st.hasMoreTokens()) {
                String basic = st.nextToken();
                if (basic.equalsIgnoreCase("Basic")) {
                    try {
                        String credentials = new String(Base64.getDecoder().decode(st.nextToken()));
                        this.context.log("Credentials: " + credentials);
                        Integer p = credentials.indexOf(":");
                        if (p != -1) {
                            String _username = credentials.substring(0, p).trim();
                            String _password = credentials.substring(p + 1).trim();

                            UsuarioDao usuarioDao = new UsuarioDaoJpa();

                            Usuario usuario = usuarioDao.buscarPorNomeUsuarioESenha(_username, _password);

                            if (usuario == null) {
                                unauthorized(response, "Bad credentials");
                                return;
                            }

                            String method = request.getMethod();

                            if (method == "DELETE" || method == "PUT") {
                                if (usuario.getIsAdmin() == null || usuario.getIsAdmin() == false) {
                                    unauthorized(response, "Sem permissão para excluir ou alterar dados");
                                    return;
                                }

                            }
                            // // Prossegue com a requisicao
                            chain.doFilter(req, res);
                        } else {
                            unauthorized(response, "Invalid authentication token");
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new Error("Couldn't retrieve authentication", e);
                    }
                }
            }
        } else {
            unauthorized(response);
        }
    }

    @Override
    public void destroy() {
        // Aqui pode-se desalocar qualquer recurso

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        this.context.log("Filtro inicializado!");
        config.getInitParameter("username");
        config.getInitParameter("password");
        String paramRealm = config.getInitParameter("realm");
        if (paramRealm != null && paramRealm.length() > 0) {
            this.realm = paramRealm;
        }
    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        response.sendError(401, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
    }
}
