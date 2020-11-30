package com.saber.jee.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saber.jee.model.Projet;
import com.saber.jee.model.Role;
import com.saber.jee.model.StatutTache;
import com.saber.jee.model.Tache;
import com.saber.jee.model.Utilisateur;
import com.saber.jee.service.IProjetService;
import com.saber.jee.service.ITacheService;
import com.saber.jee.service.IUtilisateurService;
import com.saber.jee.service.imp.ProjetServiceImp;
import com.saber.jee.service.imp.TacheServiceImp;
import com.saber.jee.service.imp.UtilisateurServiceImp;

@WebServlet("/AjouterTacheProjet")
public class AjouterTacheProjet extends HttpServlet {

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
				String id = request.getParameter("id");
				Projet projet = projetService.getById(id);
				request.setAttribute("projet", projet);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/addTacheProjet.jsp").forward(request, response);
			} else if (utilisateur.getRole() == Role.ADMINISTRATEUR) {

			} else {

			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String status = "succes";
		String idProjet = request.getParameter("numero");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");

		String description = request.getParameter("description");
		int duree = Integer.parseInt(request.getParameter("duree"));
		Date dateDebut = null;
		Date dateFin = null;
		try {
			dateDebut = sdf.parse(request.getParameter("dateDebut"));
			dateFin = sdf.parse(request.getParameter("dateFin"));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		StatutTache statut = StatutTache.valueOf(request.getParameter("statut"));
		try {
			Projet projetEntity = projetService.getById(idProjet);
			Tache tache = new Tache(description, duree, dateDebut, dateFin, statut);
			int idTache = tacheService.add(tache);

			Tache tacheEntity = tacheService.getById(idTache);
			tacheEntity.setProjet(projetEntity);

			tacheService.edit(tacheEntity);

		} catch (Exception ex) {
			System.out.println("Impossible d'ajouter la tache au projet, erreur" + ex.getMessage());
			status = "echec";

		}

		response.sendRedirect("accueil?status=" + status);
	}
}