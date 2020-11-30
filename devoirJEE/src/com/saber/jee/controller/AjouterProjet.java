package com.saber.jee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saber.jee.model.Projet;
import com.saber.jee.model.Role;
import com.saber.jee.model.StatutProjet;
import com.saber.jee.model.Utilisateur;
import com.saber.jee.service.IProjetService;
import com.saber.jee.service.IUtilisateurService;
import com.saber.jee.service.imp.ProjetServiceImp;
import com.saber.jee.service.imp.UtilisateurServiceImp;

@WebServlet("/AjouterProjet")
public class AjouterProjet extends HttpServlet {

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

			if (utilisateur.getRole() == Role.CHEF_DE_PROJET) {
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/addProjet.jsp").forward(request, response);
			} else if (utilisateur.getRole() == Role.ADMINISTRATEUR) {
			
				
			} else {

			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String id = request.getParameter("id");
		String intitule = request.getParameter("intitule");
		String description = request.getParameter("description");
		int chargeHoraire = Integer.parseInt(request.getParameter("chargeHoraire"));
		StatutProjet statut = StatutProjet.valueOf(request.getParameter("statut"));
		String status ="succes";
		try {
		 projetService.add(new Projet(id,intitule,description,statut,chargeHoraire));
		} catch(Exception ex) {
			System.out.println("Impossible d'ajouter le projet, erreur"+ex.getMessage());
			status = "echec";
			
		}
		
		
		response.sendRedirect("accueil?status="+status);
	}
}