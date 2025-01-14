//app.js
import 'dotenv/config'; // Carga variables de entorno desde .env
import express from 'express';
import cors from 'cors';
import bodyParser from 'body-parser';

//Importar conexión a DB para inicializar
import { createConnection } from './src/config/db.js';
import cursoRoutes from './src/routes/cursoRoute.js';
import estudianteRoutes from './src/routes/estudianteRoute.js';

//Inicializar la conexión a la BD
createConnection();
const app = express();

//Middlewares
app.use(cors());
app.use(bodyParser.json());

//Rutas
app.use('/cursos', cursoRoutes);
app.use('/estudiantes', estudianteRoutes);

//Ruta raíz de prueba
app.get('/', (req, res) => {
    res.send('Hola, este es el backend para la App Móvil Crud - Examen 02!');
});


//Iniciar Servidor
const PORT = process.env.PORT || 3111;
app.listen(PORT, () => {
    console.log(`Servidor corriendo en el puerto ${PORT}`);
});