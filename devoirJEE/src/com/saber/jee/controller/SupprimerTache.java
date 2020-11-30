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

@WebServlet("/SupprimerTache")
public class SupprimerTache extends HttpServlet {

	private static final long serialVersionUID = 2111324405708548677L;

	private IUtilisateurService utilisateurService;
	private ITacheService tacheService;

	public void init() throws ServletException {
		utilisateurService = new UtilisateurServiceImp();
		tacheService = new TacheServiceImp();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		

		int id = Integer.parseInt(request.getParameter("id"));
		String status = "succes";
		try {
			Tache tache = tacheService.getById(id);
			
//			tache.setUtilisateur(null);
//			tache.setProjet(null);
//			
//			tacheService.edit(tache);
			tacheService.delete(tache);
			

			
		} catch (Exception ex) {
			System.out.println("Erreur, impossible de supprimer la tache: " + ex.getMessage());
			status = "echec";
		}

		response.sendRedirect("accueil?status=" + status);

	}
}
