// config/db.js
import mysql from "mysql2";

let db;

export function createConnection() {
    db = mysql.createConnection({
        host: process.env.DB_HOST || "localhost",
        user: process.env.DB_USER || "root",
        password: process.env.DB_PASS || "root",
        database: process.env.DB_NAME || "cursos_app",
    });

    db.connect((err) => {
        if (err) {
            console.error("Error al conectar con la base de datos:", err);
            return;
        }
        console.log("Conexión exitosa a la base de datos MySQL");
    });
}

export function getConnection() {
    return db;
}
