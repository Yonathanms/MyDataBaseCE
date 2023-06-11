<template>
  <div>
    <label for="carpeta">Nombre de la Carpeta:</label>
    <input type="text" id="carpeta" v-model="nombreCarpeta">

    <label for="atributo">Atributo de la Instancia:</label>
    <input type="text" id="atributo" v-model="atributoInstancia">

    <label for="valor">Valor a eliminar:</label>
    <input type="text" id="valor" v-model="valorEliminar">

    <button @click="eliminar">Eliminar</button>

    <label>{{ resultadoEliminar }}</label>
  </div>
</template>

<script setup>
/// logica de las componentes
/// constantes que ingresan en la función del Spring-boot utilizando peticions http por medio de la librería Vue Axios
import { ref } from "vue";
import axios from "axios";

const nombreCarpeta = ref("");
const atributoInstancia = ref("");
const valorEliminar = ref("");
const resultadoEliminar = ref("");

const eliminar = () => {
  const url = "http://localhost:8080/DELETEinstancia";
  const data = {
    nombreCarpeta: nombreCarpeta.value,
    campo: atributoInstancia.value,
    valor: valorEliminar.value
  };

  axios.post(url, data)
      .then(() => {
        resultadoEliminar.value = "Instancia eliminada exitosamente";
      })
      .catch(error => {
        console.error("Error al eliminar instancia:", error);
        resultadoEliminar.value = "Error al eliminar instancia";
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
