<template>
  <div>
    <h1>XML Store Client</h1>
    <button @click="getXmlStore">Obtener XML Store</button>
    <div v-if="xmlStoreData">
      <h2>Contenido del XML Store:</h2>
      <pre>{{ xmlStoreData }}</pre>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      xmlStoreData: null
    };
  },
  methods: {
    getXmlStore() {
      axios.get('http://localhost:3000/xmlstore')
          .then(response => {
            this.xmlStoreData = response.data;
          })
          .catch(error => {
            console.error(error);
          });
    }
  }
};
</script>
