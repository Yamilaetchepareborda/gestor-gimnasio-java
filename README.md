# Gym Manager (Java)

Desktop application to manage a gym's **clients, trainers and activities**, built with Java Swing, a layered **MVC + DAO** architecture and MySQL.

> 🇦🇷 Gestor de gimnasio de escritorio en Java Swing con arquitectura MVC, patrón DAO y MySQL. Trabajo académico en equipo.

<!-- Screenshot: add docs/menu.png here (main menu or client form) -->
_Screenshot coming soon._

## Tech stack

- Java 17 · Java Swing (NetBeans GUI Builder)
- MySQL · JDBC (MySQL Connector/J 9.x)
- MVC + DAO pattern

## Features

- **Clients:** create, list, update and delete (name, phone, DNI)
- **Trainers:** create, list, update and delete (name, specialty)
- **Activities:** create, list, update and delete (name, description, duration, max capacity), each optionally linked to a trainer and a client
- Activity list loads the trainer's data with a `LEFT JOIN`
- Form validation: required fields and numeric checks (DNI, duration, capacity) with user-facing error messages
- Queries that take user input use `PreparedStatement`; connections are closed with try-with-resources

## Architecture

```
GYM-managment/src/gym/managment/
├── model/       # Cliente, Entrenador, Actividad (POJOs)
├── dao/         # ClienteDAO, EntrenadorDAO, ActividadDAO (SQL / JDBC)
├── controller/  # Controller classes (one per entity)
├── view/        # Swing frames (menu + one form per entity)
└── util/        # ConexionBD (connection factory), TestConexion
```

All SQL lives in the DAO layer; the Swing forms call the DAOs, so no query is written inside the UI code.

## Getting started

1. Create the database with [`database/schema.sql`](database/schema.sql) (reconstructed from the DAO queries).
2. Set your MySQL user and password in `util/ConexionBD.java` (the repo only has placeholders — never commit real credentials).
3. Open `GYM-managment` in **NetBeans** (the forms use NetBeans' AbsoluteLayout library).
4. Add **MySQL Connector/J** to the project libraries and run `GYMManagment.java`.

## What I learned

- Keeping all SQL in a DAO layer so the Swing forms never contain queries.
- Writing CRUD operations with JDBC safely using `PreparedStatement` and try-with-resources.
- Modeling relations between entities (activity → trainer) and loading them with joins.

## Team

Academic project developed with classmates at IDRA:
**Yamila Etchepareborda**, Tatiana Roque and Ariel Soria.

My contributions: business logic, MVC structure, DAO data access, MySQL connection and GUI integration.

## License

[GPL-3.0](LICENSE)
