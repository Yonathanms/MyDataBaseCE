<template>
  <div>
    <label for="folderName">Nombre de la Carpeta:</label>
    <input type="text" id="folderName" v-model="nombreCarpeta">

    <button @click="eliminarInstancias">Eliminar instancias</button>

    <label>{{ resultadoEliminacion }}</label>
  </div>
</template>

<script setup>
/// logica de las componentes
/// constantes que ingresan en la función del Spring-boot utilizando peticions http por medio de la librería Vue Axios
import { ref } from "vue";
import axios from "axios";

const nombreCarpeta = ref("");
const resultadoEliminacion = ref("");

const eliminarInstancias = () => {
  const url = "http://localhost:8080/DELETEinstancias";
  const data = {
    nombreCarpeta: nombreCarpeta.value
  };

  axios.post(url, data)
      .then(response => {
        console.log("Instancias eliminadas exitosamente");
        resultadoEliminacion.value = "Instancias eliminadas exitosamente";
      })
      .catch(error => {
        console.error("Error al eliminar instancias:", error);
        resultadoEliminacion.value = "Error al eliminar instancias";
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
  display: block;
  margin-right: auto;
  margin-left: auto;
}

button {
  margin-top: 10px;

}
</style>
