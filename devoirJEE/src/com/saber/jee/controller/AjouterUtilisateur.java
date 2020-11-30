package com.saber.jee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saber.jee.model.Role;
import com.saber.jee.model.Tache;
import com.saber.jee.model.Utilisateur;
import com.saber.jee.service.IUtilisateurService;
import com.saber.jee.service.imp.UtilisateurServiceImp;

@WebServlet("/AjouterUtilisateur")
public class AjouterUtilisateur extends HttpServlet {

	private static final long serialVersionUID = 2111324405708548677L;

	private IUtilisateurService utilisateurService;

	public void init() throws ServletException {
		utilisateurService = new UtilisateurServiceImp();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		if (utilisateur == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		} else {

			if (utilisateur.getRole() == Role.CHEF_DE_PROJET) {

			} else if (utilisateur.getRole() == Role.ADMINISTRATEUR) {
			
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/addUtilisateur.jsp").forward(request, response);
			} else {

			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motdepasse = request.getParameter("motdepasse");
		Role role = Role.valueOf(request.getParameter("role"));
		String status ="succes";
		try {
		 utilisateurService.add(new Utilisateur(nom,prenom,email,motdepasse,role));
		} catch(Exception ex) {
			System.out.println("Impossible d'ajouter utilisateur, erreor"+ex.getMessage());
			status = "echec";
			
		}
		
		
		response.sendRedirect("accueil?status="+status);
	}
}