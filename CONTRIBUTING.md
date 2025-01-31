
## 🛠️ FLUJO DE TRABAJO:
<br>

- Cada proyecto tendrá una rama principal llamada `main`.
- Cada tarea, o grupo de tareas vinculadas con una misma funcionalidad, será desarrollada por un miembro del equipo en una nueva rama local que salga de `main`.  
  La rama debe llamarse siguiendo el patrón:  
  `feature/nombre_descriptivo_nueva_funcionalidad`

---

### 🚀 Empezar a programar una nueva funcionalidad:

1. Desde la rama `main local`:<br>
   `git pull`  
   `git checkout -b feature/nombre_descriptivo_nueva_funcionalidad`

---

### 📤 Subir una funcionalidad o cambio ya finalizado:

1. Desde la rama <span style="color:yellow;font-weight:bold">feature</span>:<br>
   `git add .`  
   `git commit -m "Mensaje descriptivo del cambio o funcionalidad añadida"`


2. Desde la rama <span style="color:yellow;font-weight:bold">main local</span>:<br>
   `git pull`


3. Desde la rama <span style="color:yellow;font-weight:bold">feature</span>:<br>
   `git merge main`


4. Desde la rama <span style="color:yellow;font-weight:bold">main local</span>:<br>
   `git merge feature`  
   `git push`
