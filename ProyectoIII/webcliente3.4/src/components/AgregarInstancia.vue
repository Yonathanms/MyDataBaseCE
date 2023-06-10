<template>
  <div>
    <label for="nombre-carpeta">Nombre de la Carpeta:</label>
    <input type="text" id="nombre-carpeta" v-model="nombreCarpeta" />

    <label for="instancia-deseada">Instancia Deseada:</label>
    <input type="text" id="instancia-deseada" v-model="instanciaDeseada" />

    <button @click="commit">Commit</button>

    <label>{{ mensajeConsola }}</label>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const nombreCarpeta = ref('');
const instanciaDeseada = ref('');
const mensajeConsola = ref('');

const commit = () => {
  const url = 'http://localhost:8080/INSERTinstancia';
  const data = {
    nombreCarpeta: nombreCarpeta.value,
    instancias: [instanciaDeseada.value] // Envolvemos la instancia en un arreglo
  };

  axios.post(url, data)
      .then(response => {
        mensajeConsola.value = 'Instancia insertada exitosamente';
        // Realizar acciones adicionales si es necesario
      })
      .catch(error => {
        mensajeConsola.value = 'Error al insertar la instancia: ' + error.message;
        // Realizar acciones adicionales en caso de error
      });

  // Limpiar los campos de formulario
  nombreCarpeta.value = '';
  instanciaDeseada.value = '';
};
</script>

<style scoped>
label {
  display: block;
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