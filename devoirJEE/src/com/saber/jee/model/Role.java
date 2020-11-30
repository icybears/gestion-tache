package com.saber.jee.model;

public enum Role {
	
	EMPLOYE("Employé"), 
	ADMINISTRATEUR("Administrateur"), 
	CHEF_DE_PROJET("Chef de Projet");
	
	private String roleName;
	
	private Role(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleName() {
		return this.roleName;
		
	}
}

