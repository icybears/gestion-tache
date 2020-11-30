package com.saber.jee.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saber.jee.model.Role;
import com.saber.jee.model.StatutTache;
import com.saber.jee.model.Tache;
import com.saber.jee.model.Utilisateur;
import com.saber.jee.service.ITacheService;
import com.saber.jee.service.IUtilisateurService;
import com.saber.jee.service.imp.TacheServiceImp;
import com.saber.jee.service.imp.UtilisateurServiceImp;

@WebServlet("/ModifierTache")
public class ModifierTache extends HttpServlet {

	private static final long serialVersionUID = 2111324405708548677L;

	private IUtilisateurService utilisateurService;
	private ITacheService tacheService;

	public void init() throws ServletException {
		utilisateurService = new UtilisateurServiceImp();
		tacheService = new TacheServiceImp();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		if (utilisateur == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		} else {

			int id = Integer.parseInt(request.getParameter("id"));
			Tache tache = tacheService.getById(id);
			request.setAttribute("tache", tache);
			// utilisateurService.getById(utilisateur.getId());
			if (utilisateur.getRole() == Role.CHEF_DE_PROJET) {
				List<Tache> taches = utilisateur.getTaches();
				request.setAttribute("taches", taches);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/editTache.jsp").forward(request, response);
			} else if (utilisateur.getRole() == Role.ADMINISTRATEUR) {

			} else {
				List<Tache> taches = utilisateur.getTaches();
				request.setAttribute("taches", taches);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/editTacheEmp.jsp").forward(request, response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String status = "succes";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		int id = Integer.parseInt(request.getParameter("numero"));
		String description = request.getParameter("description");
		int duree = Integer.parseInt(request.getParameter("duree"));
		Date dateDebut = null;
		Date dateFin = null;
		try {
			dateDebut = sdf.parse(request.getParameter("dateDebut"));
			dateFin = sdf.parse(request.getParameter("dateFin"));
		} catch (ParseException e) {

			System.out.println("Erreur, date incorrecte :"+e.getMessage());
			status = "echec";
		}

		StatutTache statut = StatutTache.valueOf(request.getParameter("statut"));
		
		try {
			Tache tache = tacheService.getById(id);
			tache.setDescription(description);
			tache.setDateDebut(dateDebut);
			tache.setDateFin(dateFin);
			tache.setDuree(duree);
			tache.setStatut(statut);

			tacheService.edit(tache);
		} catch (Exception ex) {
			System.out.println("Erreur, impossible de modifier la tache: " + ex.getMessage());
			status = "echec";
		}

		response.sendRedirect("accueil?status=" + status);

	}
}
