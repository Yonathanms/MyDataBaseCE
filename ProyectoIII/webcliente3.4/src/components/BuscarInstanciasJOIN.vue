<template>
  <div>
    <label>Nombre de la Carpeta 1</label>
    <input type="text" v-model="nombreCarpeta1">

    <label>Nombre de la Carpeta 2</label>
    <input type="text" v-model="nombreCarpeta2">

    <label>Atributos de las instancias</label>
    <input type="text" v-model="atributosInstancias">

    <label>Valor de búsqueda</label>
    <input type="text" v-model="valorBusqueda">

    <button @click="buscar">Buscar</button>

    <label>{{ resultadoBusqueda }}</label>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";

const nombreCarpeta1 = ref("");
const nombreCarpeta2 = ref("");
const atributosInstancias = ref("");
const valorBusqueda = ref("");
const resultadoBusqueda = ref("");

const buscar = () => {
  const url = "http://localhost:8080/JOINbuscarinstancias";
  const data = {
    nombreCarpeta1: nombreCarpeta1.value,
    nombreCarpeta2: nombreCarpeta2.value,
    campo: atributosInstancias.value,
    valor: valorBusqueda.value
  };

  axios.post(url, data)
      .then(response => {
        resultadoBusqueda.value = response.data;
      })
      .catch(error => {
        console.error("Error al buscar instancias:", error);
      });
};
</script>

<style scoped>
/* Estilos de la componente aquí */
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
