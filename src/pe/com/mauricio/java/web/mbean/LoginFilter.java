package pe.com.mauricio.java.web.mbean;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.com.mauricio.java.web.dto.UsuarioDto;

@WebFilter("/*")
public class LoginFilter implements Filter, ServletContextListener {
	public LoginFilter() {
	}

	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		 //System.out.println("doFilter...");

		HttpSession session = request.getSession(false);

		UsuarioDto usuario = (session != null) ? (UsuarioDto) session.getAttribute("usuario") : null;

		// Excepciones

		String loginURL = request.getContextPath() + "/faces/login.xhtml";
		String resourcesURL = request.getContextPath() + "/resources";
		String resourcesFaces = request.getContextPath() + "/faces" + ResourceHandler.RESOURCE_IDENTIFIER;

		boolean loginRequest = request.getRequestURI().startsWith(loginURL);
		boolean resourcesRequest = request.getRequestURI().startsWith(resourcesURL);
		boolean resourceRequest = request.getRequestURI().startsWith(resourcesFaces);

		if (usuario != null || loginRequest || resourceRequest || resourcesRequest) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(loginURL);
			//System.out.println("loginURL****");

		}

	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

	}

}
