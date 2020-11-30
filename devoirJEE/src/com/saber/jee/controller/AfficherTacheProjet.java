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
import com.saber.jee.service.ITacheService;
import com.saber.jee.service.IUtilisateurService;
import com.saber.jee.service.imp.ProjetServiceImp;
import com.saber.jee.service.imp.TacheServiceImp;
import com.saber.jee.service.imp.UtilisateurServiceImp;

@WebServlet("/AfficherTacheProjet")
public class AfficherTacheProjet extends HttpServlet {

	private static final long serialVersionUID = 2111324405708548677L;

	private IUtilisateurService utilisateurService;
	private IProjetService projetService;
	private ITacheService tacheService;

	public void init() throws ServletException {
		utilisateurService = new UtilisateurServiceImp();
		projetService = new ProjetServiceImp();
		tacheService = new TacheServiceImp();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		if (utilisateur == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		} else {

			if (utilisateur.getRole() == Role.CHEF_DE_PROJET) {
				String status ="succes";
				String id = request.getParameter("id");
				try {
				Projet projet = projetService.getById(id);
				request.setAttribute("projet", projet);
				
				List<Tache> taches = projet.getTaches();
				
				request.setAttribute("taches", taches);
				}
				catch(Exception ex) {
					System.out.println("Impossible les taches du projet, erreur: "+ex.getMessage());
					status = "echec";
					response.sendRedirect("accueil?status="+status);
				}
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/showTacheProjet.jsp").forward(request, response);

			} else if (utilisateur.getRole() == Role.ADMINISTRATEUR) {

			} else {

			}
		}

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

	}
}
