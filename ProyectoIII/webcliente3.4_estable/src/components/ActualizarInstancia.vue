<template>
  <div>
    <label for="carpeta">Nombre de la Carpeta:</label>
    <input type="text" id="carpeta" v-model="nombreCarpeta">

    <label for="atributo">Atributo de las instancias:</label>
    <input type="text" id="atributo" v-model="atributoInstancias">

    <label for="valor">Valor del atributo:</label>
    <input type="text" id="valor" v-model="valorAtributo">

    <label for="nuevo-valor">Valores nuevos de la instancia:</label>
    <input type="text" id="nuevo-valor" v-model="nuevosValoresInstancia">

    <button @click="actualizar">Actualizar instancias</button>

    <label>{{ resultadoActualizacion }}</label>
  </div>
</template>


<script setup>
//logica de la componente
import { ref } from 'vue';
import axios from 'axios';

//constantes que ingresan en la función del Spring-Boot
const nombreCarpeta = ref('');
const atributoInstancias = ref('');
const valorAtributo = ref('');
const nuevosValoresInstancia = ref('');
const resultadoActualizacion = ref('');

const actualizar = () => {
  const url = 'http://localhost:8080/UPDATEinstancia';
  const data = {
    nombreCarpeta: nombreCarpeta.value,
    campo: atributoInstancias.value,
    valor: valorAtributo.value,
    nuevoContenido: nuevosValoresInstancia.value
  };

  axios.post(url, data)
      .then(response => {
        resultadoActualizacion.value = 'Instancias actualizadas exitosamente';
        console.log('Instancias actualizadas exitosamente');
      })
      .catch(error => {
        resultadoActualizacion.value = 'Error al actualizar instancias';
        console.error('Error al actualizar instancias:', error);
      });
};
</script>

<style scoped>
label {
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
