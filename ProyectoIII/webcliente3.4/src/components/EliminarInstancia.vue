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
