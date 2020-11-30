package com.saber.jee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.saber.jee.model.Utilisateur;
import com.saber.jee.service.IUtilisateurService;
import com.saber.jee.service.imp.UtilisateurServiceImp;

@WebServlet("/profil")
public class ModifierProfil extends HttpServlet {

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

				Utilisateur user = utilisateurService.getById(utilisateur.getId());
				request.setAttribute("utilisateur", user);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request, response);
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		String status = "succes";
		try {
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		
		String nv_motdepasse = request.getParameter("nv_motdepasse");
		String confirmation_motdepasse = request.getParameter("confirmation_motdepasse");
		
		if(nv_motdepasse.trim().length() != 0 && nv_motdepasse == confirmation_motdepasse)
		{
			utilisateur.setMotdepasse(nv_motdepasse);
		} else if (nv_motdepasse.trim().length() != 0 && nv_motdepasse != confirmation_motdepasse){
			status="echec";
			
		}
		
		
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		
		
		session.setAttribute("utilisateur", utilisateur);
		utilisateurService.edit(utilisateur);
		} catch(Exception ex) {
			System.out.println("Impossible de modifier l'utilisateur, erreur"+ex.getMessage());
			System.out.println(ex.getStackTrace());
			status = "echec";
		}

		
		//getServletContext().getRequestDispatcher("/accueil").include(request, response);
		response.sendRedirect("profil?status="+status);
	}
}