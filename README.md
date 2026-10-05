# EcoDigital - Sistema de Gestión y Trazabilidad de Donaciones

## Descripción

**EcoDigital** es un sistema web diseñado para la gestión y trazabilidad de donaciones de equipos informáticos (Residuos de Aparatos Eléctricos y Electrónicos) para la **ONG EcoDigital La Libertad**.

---

## 💻 Requisitos previos (Entorno de Desarrollo)

Para levantar este proyecto en local, debes contar con las siguientes herramientas y versiones específicas instaladas en tu sistema:

| Requisito         | Versión              | Uso                                               | Obligatorio / Opcional |
| :---------------- | :------------------- | :------------------------------------------------ | :--------------------- |
| **Java (JDK)**    | 17 o superior        | Compilación y ejecución del backend               | Obligatorio            |
| **Apache Maven**  | 3.9 o superior       | Gestión de dependencias de Java                   | Obligatorio            |
| **MySQL Server**  | 8.0                  | Motor de base de datos relacional (Puerto `3306`) | Obligatorio            |
| **Node.js**       | 20 LTS               | Entorno de ejecución para Angular                 | Obligatorio            |
| **npm**           | 10 o superior        | Gestor de paquetes del frontend                   | Obligatorio            |
| **Angular CLI**   | 17 o superior        | Servidor de desarrollo del frontend               | Obligatorio            |
| **Navegador web** | Chrome, Edge o Brave | Visualización y bypass de SSL autofirmado         | Obligatorio            |

---

## 📁 Estructura general del proyecto

- **Carpeta del Frontend:**
  - `src/app/components/`: Módulos del sistema (`equipos`, `usuarios`, `donantes`, `instituciones`, `despachos`, `historial`, `roles`, `tipo-equipo`, `estados-equipo`).
  - `src/app/services/`: Servicios de conexión HTTP con la API.
  - `styles.css` / `app.css`: Estilos globales y variables de paleta de colores.
- **Carpeta del Backend:**
  - `BD/`: Directorio que contiene el script de la base de datos (`SQLcondatosfinal.sql`).
  - `org.ecodigital.backend.model`: Paquete principal con las entidades de dominio Java.
  - `application.properties`: Archivo principal de configuración.

---

## ⚙️ Configuración del proyecto

### Configuración del backend (`application.properties`)

El backend está preconfigurado para correr de manera segura y conectarse a MySQL con los siguientes parámetros detectados:

- **Puerto:** `8080` (HTTPS).
- **Conexión BD:** `jdbc:mysql://localhost:3306/ecodigital?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true`
- **Credenciales BD:** Usuario `root` / Contraseña `1234`.
- **JPA/Hibernate:** Dialecto `org.hibernate.dialect.MySQLDialect`, `ddl-auto=none`.
- **Seguridad SSL:** Requiere el archivo `ecodigital-keystore.p12` en el classpath (Alias: `sowad-ssl`, Password: `123456`, Tipo: `PKCS12`).

### Configuración del frontend

- **URL del backend:** `http://localhost:8080/api/` (las rutas están configuradas directamente en los servicios).
- **Puerto del frontend:** `4200` (HTTPS).

---

## 🗄️ Preparación de la Base de Datos

1. Inicia el servicio de MySQL local en el puerto `3306`.
2. Localiza el script de inicialización dentro de la carpeta del backend en la ruta: `BD/SQLcondatosfinal.sql`.
3. Ejecuta el script en tu gestor de base de datos (Ej: MySQL Workbench, DBeaver). Este script realizará lo siguiente:
   - Creará la base de datos `ecodigital`.
   - Creará la estructura de tablas.
   - Insertará los datos iniciales y los catálogos.

**Usuarios de prueba precargados en el script:**

- `admin@admin` (Administrador)
- `tecnico@tecnico` (Técnico)
- `registrador@registrador` (Registrador)
- `dev@dev` (Desarrollador)
  > **Contraseña para todos:** `123456`

---

## 🚀 Instalación y ejecución paso a paso

### Orden recomendado de arranque

1. Base de datos (MySQL).
2. Backend (Spring Boot).
3. Autorización de certificados SSL en el navegador.
4. Frontend (Angular).

### 1. Clonar el proyecto

Descarga o clona el repositorio en tu máquina local y asegúrate de haber inicializado la base de datos (paso anterior).

### 2. Levantar el Backend (Spring Boot)

Abre una terminal, dirígete a la carpeta del backend y ejecuta el proyecto usando el Maven Wrapper:

```bash
cd ruta del backend
./mvnw spring-boot:run
El backend se levantará en: http://localhost:8080

### 3. Levantar el Backend (Spring Boot)

Abre una terminal, dirígete a la carpeta del Frontend y ejecuta el proyecto:

cd ruta-al-frontend
npm install
npx ng serve

El frontend se levantará en: http://localhost:4200

```
