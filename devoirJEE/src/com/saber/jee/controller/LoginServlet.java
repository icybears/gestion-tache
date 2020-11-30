package com.saber.jee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saber.jee.model.Utilisateur;
import com.saber.jee.service.IUtilisateurService;
import com.saber.jee.service.imp.UtilisateurServiceImp;

@WebServlet("")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 2111324405708548677L;
	
	private IUtilisateurService utilisateurService;
	
	public void init() throws ServletException {
	     utilisateurService = new UtilisateurServiceImp();
	   }
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("utilisateur") != null) {
			response.sendRedirect("accueil");
			return;
		}
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		if(utilisateurService == null) {
			System.out.println("utilisateur service is null");
		}
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("email: "+email);
	
		Utilisateur utilisateur = utilisateurService.authenticate(email, password);
		
		if(utilisateur == null) {
			String errorMsg = "Email ou mot de passe incorrecte";
			request.setAttribute("error",true);
			request.setAttribute("message", errorMsg);
			System.out.println("Authentication échoué;");
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			System.out.println("Authentication reussie;");
			//getServletContext().getRequestDispatcher("/accueil").forward(request, response);
			response.sendRedirect("accueil");
		}
	}
}
