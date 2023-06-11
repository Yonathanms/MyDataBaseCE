import { createRouter, createWebHistory } from 'vue-router';
import IniciarSesion from './components/IniciarSesion.vue';
import Registro from './components/Registro.vue';

const routes = [
    {
        path: '/',
        name: 'IniciarSesion',
        component: IniciarSesion,
    },
    {
        path: '/registro',
        name: 'Registro',
        component: Registro,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;