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
        <div class="buttons-container">
          <button type="submit">Iniciar sesión</button>
        </div>
      </form>
      <p v-if="errorMensaje" class="error-message">{{ errorMensaje }}</p>
      <button @click="mostrarComponenteRegistro">Registrar</button>
    </div>

    <div v-else>
      <BotonesMenu v-if="personaEncontrada" />
      <Registro v-else @registroCompletado="registroCompletado" />
    </div>
  </div>
</template>

<script setup>
/// logica de las componentes
/// constantes que ingresan en la función del Spring-boot utilizando peticions http por medio de la librería Vue Axios
import { ref } from "vue";
import axios from "axios";
import Registro from "./Registro.vue";
import BotonesMenu from "./BotonesMenu.vue";

const correo = ref("");
const clave = ref("");
const mostrarComponenteInicioSesion = ref(true);
const personaEncontrada = ref(false);
const errorMensaje = ref("");

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
          errorMensaje.value = "Correo o clave incorrectos";
        }

        // Limpia los campos de formulario
        correo.value = "";
        clave.value = "";
      })
      .catch((error) => {
        console.error(error);
        errorMensaje.value = "Error al iniciar sesión";
      });
};

const mostrarComponenteRegistro = () => {
  mostrarComponenteInicioSesion.value = false;
  errorMensaje.value = "";
};

const registroCompletado = () => {
  mostrarComponenteInicioSesion.value = true;
};
</script>

<style>
/// Estilos Css de las componentes
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
  display: block;
}

input {
  width: 200px;
  margin-top: 5px;
}

.buttons-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

button {
  width: 120px;
  margin-right: auto;
  margin-left: auto;
}

.error-message {
  text-align: center;
  color: red;
  margin-top: 10px;
}
</style>
