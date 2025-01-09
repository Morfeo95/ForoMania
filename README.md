# Foromania

Foromania es una aplicación de foro diseñada para gestionar temas, respuestas y usuarios. Implementa autenticación segura mediante JSON Web Tokens (JWT) y ofrece funciones CRUD completas.

---

## Características

### Funciones principales:
- **Autenticación segura:**
  - Registro de usuarios.
  - Inicio de sesión con generación y validación de JWT.
- **Gestión de usuarios:**
  - Crear, leer, actualizar y eliminar usuarios.
- **Gestión de temas:**
  - Crear, leer, actualizar y eliminar temas (tópicos).
- **Gestión de respuestas:**
  - Crear, leer, actualizar y eliminar respuestas a los temas.

---

## Tecnologías utilizadas

- **Backend:**
  - Lenguaje: [Java](https://www.java.com/)
  - Framework: [Spring Boot](https://spring.io/projects/spring-boot)
  - Seguridad: [Spring Security](https://spring.io/projects/spring-security) con JWT
  - Base de datos: [MySQL](https://www.mysql.com/)
  - ORM: [Hibernate](https://hibernate.org/)

---

## Instalación y configuración

### Prerrequisitos:

1. **JDK 17+**
3. **Base de datos MySQL configurada.**
4. **Maven** (gestor de dependencias para Java).

### Pasos:

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/foromania.git
   cd foromania
   ```

2. Configura la base de datos en `application.properties` o `application.yml`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/foromania
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Compila y ejecuta el backend:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

---

## Endpoints principales

### Autenticación:
- **POST** `/login`: Inicio de sesión (genera JWT).

### Usuarios:
- **GET** `/users`: Lista todos los usuarios.
- **PUT** `/users/{id}`: Actualiza un usuario.
- **DELETE** `/users/{id}`: Elimina un usuario.

### Tópicos:
- **POST** `/topics`: Crea un nuevo tópico.
- **GET** `/topics`: Lista todos los tópicos.
- **GET** `/topics/{id}`: Obtiene un tópico por ID.
- **PUT** `/topics/{id}`: Actualiza un tópico.
- **DELETE** `/topics/{id}`: Elimina un tópico.

### Respuestas:
- **POST** `/replies`: Crea una nueva respuesta.
- **GET** `/replies`: Lista todas las respuestas.
- **PUT** `/replies/{id}`: Actualiza una respuesta.
- **DELETE** `/replies/{id}`: Elimina una respuesta.

---

## Contribuciones

¡Las contribuciones son bienvenidas! Por favor, sigue estos pasos:

1. Haz un fork de este repositorio.
2. Crea una nueva rama (`feature/nueva-funcion`).
3. Haz tus cambios y realiza un commit.
4. Envía un pull request.

---

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más información.

---

