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
import com.saber.jee.service.IProjetService;
import com.saber.jee.service.ITacheService;
import com.saber.jee.service.IUtilisateurService;
import com.saber.jee.service.imp.ProjetServiceImp;
import com.saber.jee.service.imp.TacheServiceImp;
import com.saber.jee.service.imp.UtilisateurServiceImp;

@WebServlet("/AffecterTacheEmploye")
public class AffecterTacheEmploye extends HttpServlet {

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
				int id = Integer.parseInt(request.getParameter("id"));
				Tache tache = tacheService.getById(id);

				request.setAttribute("tache", tache);
				List<Utilisateur> utilisateurs = utilisateurService.getAll();
				utilisateurs.removeIf(u -> (u.getRole() != Role.EMPLOYE));

				for(Utilisateur u : utilisateurs) {
					System.out.println(u);
				}
				request.setAttribute("employees", utilisateurs);

				getServletContext().getRequestDispatcher("/WEB-INF/jsp/affecterEmploye.jsp").forward(request, response);
			} else if (utilisateur.getRole() == Role.ADMINISTRATEUR) {

			} else {

			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String status = "succes";
		try {
			System.out.println("id tache: "+request.getParameter("tache"));
			System.out.println("id employe: "+request.getParameter("employe"));
			System.out.println("id utilisateur: "+request.getParameter("utilisateur"));
			int idTache = Integer.parseInt(request.getParameter("tache"));
			
			int idEmploye = Integer.parseInt(request.getParameter("employe"));
			System.out.println("id employe parseInt: "+idEmploye);
			
			Tache tache = tacheService.getById(idTache);
			Utilisateur utilisateur = utilisateurService.getById(idEmploye);
			tache.setUtilisateur(utilisateur);
			tacheService.edit(tache);
		} catch (Exception ex) {
			System.out.println("Erreur lors de l'affection: " + ex.getMessage());
			status = "echec";
		}

		response.sendRedirect("accueil?status=" + status);
	}
}
