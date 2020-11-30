package com.saber.jee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;


import com.saber.jee.model.Utilisateur;
import com.saber.jee.service.IUtilisateurService;
import com.saber.jee.service.imp.UtilisateurServiceImp;

@WebServlet("/SupprimerUtilisateur")
public class SupprimerUtilisateur extends HttpServlet {

	private static final long serialVersionUID = 2111324405708548677L;

	private IUtilisateurService utilisateurService;

	public void init() throws ServletException {
		utilisateurService = new UtilisateurServiceImp();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
		if(utilisateur.getId() == id)
		{
			response.sendRedirect("accueil?status=echec");
			return;
		}
		
		String status ="succes";
		try {
		 Utilisateur user = utilisateurService.getById(id);
		 utilisateurService.delete(user);
		} catch(Exception ex) {
			System.out.println("Impossible de supprimer utilisateur, erreur"+ex.getMessage());
			status = "echec";
			
		}
		
		
		response.sendRedirect("accueil?status="+status);
	}
}