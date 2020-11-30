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

@WebServlet("/ModifierUtilisateur")
public class ModifierUtilisateur extends HttpServlet {

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
				int id = Integer.parseInt(request.getParameter("id"));
				Utilisateur user = utilisateurService.getById(id);
				request.setAttribute("utilisateur", user);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/editUtilisateur.jsp").forward(request, response);
			} else {

			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String status = "succes";
		try {
		int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		Role role = Role.valueOf(request.getParameter("role"));
		
		Utilisateur utilisateur = utilisateurService.getById(id);
		
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setRole(role);
		
		utilisateurService.edit(utilisateur);
		} catch(Exception ex) {
			System.out.println("Impossible de modifier l'utilisateur, erreor"+ex.getMessage());
			status = "echec";
		}

		
		//getServletContext().getRequestDispatcher("/accueil").include(request, response);
		response.sendRedirect("accueil?status="+status);
	}
}