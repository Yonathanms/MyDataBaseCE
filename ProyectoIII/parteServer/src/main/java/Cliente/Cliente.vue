<template>
  <div>
    <button @click="getInstances">Obtener instancias</button>
    <button @click="getInstanceById(1)">Obtener instancia por ID</button>
    <button @click="createInstance">Crear instancia</button>
    <button @click="updateInstance(1)">Actualizar instancia por ID</button>
    <button @click="deleteInstance(1)">Eliminar instancia por ID</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  methods: {
    getInstances() {
      axios.get('http://localhost:8080/api/instances')
          .then(response => {
            console.log(response.data); // Aquí puedes manejar la respuesta del servidor
          })
          .catch(error => {
            console.error(error); // Manejo de errores
          });
    },
    getInstanceById(id) {
      axios.get(`http://localhost:8080/api/instances/${id}`)
          .then(response => {
            console.log(response.data); // Aquí puedes manejar la respuesta del servidor
          })
          .catch(error => {
            console.error(error); // Manejo de errores
          });
    },
    createInstance() {
      axios.post('http://localhost:8080/api/instances')
          .then(response => {
            console.log(response.data); // Aquí puedes manejar la respuesta del servidor
          })
          .catch(error => {
            console.error(error); // Manejo de errores
          });
    },
    updateInstance(id) {
      axios.put(`http://localhost:8080/api/instances/${id}`)
          .then(response => {
            console.log(response.data); // Aquí puedes manejar la respuesta del servidor
          })
          .catch(error => {
            console.error(error); // Manejo de errores
          });
    },
    deleteInstance(id) {
      axios.delete(`http://localhost:8080/api/instances/${id}`)
          .then(response => {
            console.log(response.data); // Aquí puedes manejar la respuesta del servidor
          })
          .catch(error => {
            console.error(error); // Manejo de errores
          });
    }
  }
};
</script>
