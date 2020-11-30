package com.saber.jee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saber.jee.model.Projet;
import com.saber.jee.model.Role;
import com.saber.jee.model.Tache;
import com.saber.jee.model.Utilisateur;
import com.saber.jee.service.IProjetService;
import com.saber.jee.service.IUtilisateurService;
import com.saber.jee.service.imp.ProjetServiceImp;
import com.saber.jee.service.imp.UtilisateurServiceImp;

@WebServlet("/accueil")
public class EspaceDeTravailServlet extends HttpServlet {

	private static final long serialVersionUID = 2111324405708548677L;

	private IUtilisateurService utilisateurService;
	private IProjetService projetService;

	public void init() throws ServletException {
		utilisateurService = new UtilisateurServiceImp();
		projetService = new ProjetServiceImp();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		if (utilisateur == null) {

			response.sendRedirect(request.getContextPath() + "/");
			return;
		} else {

			utilisateur = utilisateurService.getById(utilisateur.getId());
			if (utilisateur.getRole() == Role.CHEF_DE_PROJET) {
				
				List<Projet> projets = projetService.getAll();
				request.setAttribute("projets", projets);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/accueilCdp.jsp").forward(request, response);
				
			} else if (utilisateur.getRole() == Role.ADMINISTRATEUR) {
				
				List<Utilisateur> utilisateurs = utilisateurService.getAll();
				request.setAttribute("utilisateurs", utilisateurs);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/accueilAdmin.jsp").forward(request, response);
				
			} else {
				
				List<Tache> taches = utilisateur.getTaches();
				request.setAttribute("taches", taches);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

	}
}
