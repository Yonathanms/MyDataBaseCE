<template>
  <div>
    <label>Nombre de la Carpeta 1</label>
    <input type="text" v-model="nombreCarpeta1">

    <label>Nombre de la Carpeta 2</label>
    <input type="text" v-model="nombreCarpeta2">

    <label>Atributo 1</label>
    <input type="text" v-model="atributo1">

    <label>Valor del Atributo 1</label>
    <input type="text" v-model="valorAtributo1">

    <label>Atributo 2</label>
    <input type="text" v-model="atributo2">

    <label>Valor del Atributo 2</label>
    <input type="text" v-model="valorAtributo2">

    <label>Atributo 3</label>
    <input type="text" v-model="atributo3">

    <label>Valor del Atributo 3</label>
    <input type="text" v-model="valorAtributo3">

    <button @click="buscar">Buscar Instancias</button>

    <label>{{ resultadoBusqueda }}</label>
  </div>
</template>

<script setup>
/// logica de las componentes
/// constantes que ingresan en la función del Spring-boot utilizando peticions http por medio de la librería Vue Axios
import { ref } from "vue";
import axios from "axios";

const nombreCarpeta1 = ref("");
const nombreCarpeta2 = ref("");
const atributo1 = ref("");
const valorAtributo1 = ref("");
const atributo2 = ref("");
const valorAtributo2 = ref("");
const atributo3 = ref("");
const valorAtributo3 = ref("");
const resultadoBusqueda = ref("");

const buscar = () => {
  const url = "http://localhost:8080/JOINbuscarinstancias";
  const data = {
    nombreCarpeta1: nombreCarpeta1.value,
    nombreCarpeta2: nombreCarpeta2.value,
    campo: atributo1.value,
    valor: valorAtributo1.value,
    campo2: atributo2.value,
    valor2: valorAtributo2.value,
    campo3: atributo3.value,
    valor3: valorAtributo3.value
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
