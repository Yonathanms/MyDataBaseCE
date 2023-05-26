<script setup>
import { ref } from "vue";

const inputString = ref("");
const stringImpreso = ref("");

const imprimirString = () => {
  stringImpreso.value = inputString.value;
};
</script>

<template>
  <h1>Impresión de String</h1>
  <div class="inputs">
    <input type="text" v-model="inputString" placeholder="Ingrese un string">
    <button @click="imprimirString">Imprimir</button>
    <p>{{ stringImpreso }}</p>
  </div>
</template>

<style>
h1 {
  text-align: center;
  color: darkseagreen;
}

.inputs {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 50px;
}

input {
  text-align: center;
  width: 200px;
  margin-bottom: 10px;
}

button {
  margin-bottom: 10px;
}

p {
  font-size: 18px;
  font-weight: bold;
}
</style>