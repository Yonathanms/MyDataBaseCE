<template>
  <div>
    <div v-if="mostrarComponenteInicioSesion">
      <h1>Iniciar sesión</h1>
      <form @submit.prevent="verificarCuenta">
        <div>
          <label for="correo">Correo:</label>
          <input type="email" id="correo" v-model="correo" required>
        </div>
        <div>
          <label for="clave">Clave:</label>
          <input type="password" id="clave" v-model="clave" required>
        </div>
        <div>
          <button type="submit">Iniciar sesión</button>
        </div>
      </form>

      <button @click="mostrarComponenteRegistro">Registrar</button>
    </div>

    <div v-else>
      <BotonesMenu v-if="personaEncontrada" />
      <Registro v-else @registroCompletado="registroCompletado" />
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import Registro from "./Registro.vue";
import BotonesMenu from "./BotonesMenu.vue";

const correo = ref("");
const clave = ref("");
const mostrarComponenteInicioSesion = ref(true);
const personaEncontrada = ref(false);

const verificarCuenta = () => {
  const url = `http://localhost:8080/verificacion/${correo.value}/${clave.value}`;

  axios
      .get(url)
      .then((response) => {
        const existePersona = response.data;
        if (existePersona) {
          console.log("La persona existe en la base de datos");
          personaEncontrada.value = true;
          mostrarComponenteInicioSesion.value = false;
        } else {
          console.log("La persona no existe en la base de datos");
          // Realiza las acciones necesarias si la persona no existe
        }

        // Limpia los campos de formulario
        correo.value = "";
        clave.value = "";
      })
      .catch((error) => {
        console.error(error);
      });
};

const mostrarComponenteRegistro = () => {
  mostrarComponenteInicioSesion.value = false;
};

const registroCompletado = () => {
  mostrarComponenteInicioSesion.value = true;
};
</script>

<style>
h1 {
  text-align: center;
  color: darkseagreen;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

label {
  margin-top: 10px;
}

input {
  width: 200px;
  margin-top: 5px;
}

button {
  margin-top: 10px;
}
</style>