<template>
  <div>
    <label for="carpeta">Nombre de la carpeta:</label>
    <input type="text" id="carpeta" v-model="nombreCarpeta">

    <label for="valores">Valores Nuevos:</label>
    <input type="text" id="valores" v-model="valoresNuevos">

    <button @click="actualizarInstancias">Actualizar Instancias</button>

    <label>{{ resultadoActualizacion }}</label>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const nombreCarpeta = ref('');
const valoresNuevos = ref('');
const resultadoActualizacion = ref('');

const actualizarInstancias = () => {
  const url = 'http://localhost:8080/UPDATEinstancias';
  const data = {
    nombreCarpeta: nombreCarpeta.value,
    nuevoContenido: valoresNuevos.value
  };

  axios.post(url, data)
      .then(response => {
        resultadoActualizacion.value = 'Instancias actualizadas exitosamente';
        console.log('Instancias actualizadas exitosamente');
      })
      .catch(error => {
        resultadoActualizacion.value = 'Error al actualizar las instancias';
        console.error('Error al actualizar las instancias:', error);
      });

  // Limpiar los campos de texto después de la actualización
  nombreCarpeta.value = '';
  valoresNuevos.value = '';
};
</script>

<style scoped>
/* Estilos específicos de la componente */
label {
  margin-top: 10px;
}

input {
  width: 200px;
  margin-top: 5px;
  display: block; /* Hace que las cajas de texto se muestren en una línea separada */
}

button {
  margin-top: 10px;
}
</style>
