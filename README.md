# Task Management Web app in Java
This is a project that I worked on for my Java Web Development class.
The goal is to develop a java web application using JSP and Hibernate that allows managing projects, tasks and task-to-project assignments for employees.

# Main Functionalities

The main functionalities to be developed are:
- Basic Login/Logout (Security concerns are discarded)
- Depending on the authenticated employee's role:
	* Administrator role: allows the administrator to manage teams and employees.
	* Project Manager role: allows the project manager to manage his own projects and their tasks, he can also assign tasks to employees.
	* Employee role: allows the employee to only see his tasks and update them (status, start date and end date).
The "manage" functionality presents the CRUD operations: get, search, edit and delete.

# Database design
The database was designed beforehand, and one of the goals of this work was to create a DAO layer based on the following Database design:

![MLD diagram](screenshots/mld.png?raw=true "Database 'MLD' design")

# End-result example screenshots
### Authentication 
![Authentication ](screenshots/authentification-echoue.jpg?raw=true "Authentication")

### Administrator's employee management page
![Administrator's employee management page](screenshots/admin-ajouter.jpg?raw=true "Administrator's employee management page")

### Administrator's add employee form
![Administrator's add employee form](screenshots/admin-ajouter-utilisateur.jpg?raw=true "Administrator's add employee form")

### Project manager's employee assignment panel
![Project manager's employee assignment panel](screenshots/affecter-employe.jpg?raw=true "Project manager's employee assignment panel")

### Project manager's task-to-project panel
![Project manager's task-to-project panel](screenshots/taches-de-projet.jpg?raw=true "Project manager's task-to-project panel")

### Project manager's task-to-project assignment form
![Project manager's task-to-project assignment form](screenshots/ajouter-tache.jpg?raw=true "Project manager's task-to-project assignment form")

### Project manager's project creation form
![Project manager's project creation form](screenshots/cdp-ajouter-projet.jpg?raw=true "Project manager's project creation form")

### Project manager's project modification form
![Project manager's project modification form](screenshots/cdp-modifier-projet.jpg?raw=true "Project manager's project modification form")

### Employee workspace
![Employee workspace](screenshots/employe-espace-de-travail.jpg?raw=true "Employee workspace")

### Employee Task update form
![Employee Task update form](screenshots/modifier-tache-employe.jpg?raw=true "Employee Task update form")

### Employee profile page
![Employee profile page](screenshots/profil-emp.jpg?raw=true "Employee profile page")

### 404 error page
![404 error page](screenshots/404.jpg?raw=true "404 error page")

### 500 error page
![500 error page](screenshots/500.jpg?raw=true "500 error page")