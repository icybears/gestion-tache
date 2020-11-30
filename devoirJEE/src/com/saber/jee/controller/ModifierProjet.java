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

@WebServlet("/ModifierProjet")
public class ModifierProjet extends HttpServlet {

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
				String id = request.getParameter("id");
				Projet projet = projetService.getById(id);
				request.setAttribute("projet", projet);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProjet.jsp").forward(request, response);
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
			Projet projet = projetService.getById(id);
			projet.setIntitule(intitule);
			projet.setChargeHoraire(chargeHoraire);
			projet.setDescription(description);
			projet.setStatut(statut);
		 projetService.edit(projet);
		} catch(Exception ex) {
			System.out.println("Impossible de modifier le projet, erreur"+ex.getMessage());
			status = "echec";
			
		}
		
		
		response.sendRedirect("accueil?status="+status);
	}
}