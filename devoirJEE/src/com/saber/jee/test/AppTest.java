package com.saber.jee.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

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

public class AppTest {
	
	public static void main(String[] args) {
		
		//testServiceUtilisateurEtTache();
		
		//testServiceProjetEtTache()
		
//		try {
//			initialisationBd();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	public static void initialisationBd() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		
		
		
		IUtilisateurService utilisateurService= new UtilisateurServiceImp();
		ITacheService tacheService = new TacheServiceImp();
		
		Utilisateur utilisateur = utilisateurService.getById(1);
		utilisateur.setRole(Role.EMPLOYE);
		
		
		int id1 = tacheService.add(new Tache("Décrire les besoins du système",30,sdf.parse("01-01-2020"),sdf.parse("31-01-2020"),StatutTache.EN_COURS));
		int id2 = tacheService.add(new Tache("Analyser l'interface graphique",30,sdf.parse("01-02-2020"),sdf.parse("31-02-2020"),StatutTache.A_FAIRE));
		int id3 = tacheService.add(new Tache("Debugger l'application",30,sdf.parse("01-03-2020"),sdf.parse("31-03-2020"),StatutTache.A_FAIRE));
		Tache tache1 = tacheService.getById(id1);
		Tache tache2 = tacheService.getById(id2);
		Tache tache3 = tacheService.getById(id3);
		
		tache1.setUtilisateur(utilisateur);
		tache2.setUtilisateur(utilisateur);
		tache3.setUtilisateur(utilisateur);
		
		System.out.println("updating taches!");
		tacheService.edit(tache1);
		tacheService.edit(tache2);
		tacheService.edit(tache3);
		
		
	}
	public static void testServiceProjetEtTache() {
		IProjetService projetService = new ProjetServiceImp();
		ITacheService tacheService = new TacheServiceImp();
		String idproj1 = projetService.add(new Projet("PR39","EEEEEE"));
		String idproj2 = projetService.add(new Projet("PR22","EAAAAA"));
		
		int idTache = tacheService.add(new Tache("tache 39"));
		Tache tache = tacheService.getById(idTache);
		Projet projet = projetService.getById(idproj1);
		projet.addTache(tache);
		
		projetService.edit(projet);
		System.out.println(projetService.getAll().size());
		
		projetService.delete(projet);
	}
	public static void testServiceUtilisateurEtTache() {
		IUtilisateurService utilisateurService= new UtilisateurServiceImp();
		ITacheService tacheService = new TacheServiceImp();
		
		
		int idTache1 = tacheService.add(new Tache("tache1"));
		
		Tache tache1 = tacheService.getById(idTache1);
		
int idTache2 = tacheService.add(new Tache("tache2"));
		
		Tache tache2 = tacheService.getById(idTache2);
		
		
		
		int idUser= utilisateurService.add(new Utilisateur("abc@gmail.com","secret"));
	Utilisateur user = utilisateurService.getById(idUser);
		System.out.println("created user "+ user);
		user.addTache(tache1);
		user.addTache(tache2);
		
		utilisateurService.edit(user);
		

		user = utilisateurService.getById(idUser);
	System.out.println("Nombre de tache associer a user: "+user.getTaches().size());
//		utilisateurService.add(new Utilisateur("ddd@ggg.com","secret1"));
		idUser = utilisateurService.add(new Utilisateur("aaa@ggg.com","secret1"));
		
		System.out.println("Nombre d'utilisateurs: "+utilisateurService.getAll().size());
		
		tache2 = tacheService.getById(idTache2);
		tache2.setUtilisateur(utilisateurService.getById(idUser));
		tacheService.edit(tache2);
//		utilisateurService.edit(user);
	//	utilisateurService.edit(user);
	utilisateurService.delete(user);
//		
//		System.out.println("Nombre d'utilisateurs apre suppression: "+utilisateurService.getAll().size());
	
	}

}
