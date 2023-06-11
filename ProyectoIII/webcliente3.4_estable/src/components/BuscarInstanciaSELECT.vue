<template>
  <div>
    <label for="folderName">Nombre de la Carpeta:</label>
    <input type="text" id="folderName" v-model="folderName">

    <label for="instanceAttribute">Atributo de la instancia:</label>
    <input type="text" id="instanceAttribute" v-model="instanceAttribute">

    <label for="searchValue">Valor de búsqueda:</label>
    <input type="text" id="searchValue" v-model="searchValue">

    <button @click="buscar">Buscar</button>

    <label>{{ resultadoBusqueda }}</label>
  </div>
</template>

<script setup>
/// logica de las componentes
/// constantes que ingresan en la función del Spring-boot utilizando peticions http por medio de la librería Vue Axios
import { ref } from 'vue';
import axios from 'axios';

const folderName = ref('');
const instanceAttribute = ref('');
const searchValue = ref('');
const resultadoBusqueda = ref('');

const buscar = () => {
  const url = 'http://localhost:8080/SELECTbusqueda';
  const data = {
    nombreCarpeta: folderName.value,
    campo: instanceAttribute.value,
    valor: searchValue.value
  };

  axios.post(url, data)
      .then(response => {
        // Actualiza el resultado de la búsqueda
        resultadoBusqueda.value = response.data;
      })
      .catch(error => {
        console.error(error);
      });

  // Limpiar los campos de formulario
  folderName.value = '';
  instanceAttribute.value = '';
  searchValue.value = '';
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
  text-align: center;
}

input {
  width: 200px;
  margin-top: 5px;
  margin-right: auto;
  margin-left: auto;
  display: block;
}

button {
  margin-top: 10px;
}
</style>