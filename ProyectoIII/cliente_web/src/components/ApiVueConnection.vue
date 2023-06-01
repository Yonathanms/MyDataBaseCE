<script >

import PersonasServices from "@/service/PersonasServices";
export default {
  name: 'ApiVueConnection',
  data() {
    return {
      personas : null
    }
  },
  personasServices  : null,
  created() {
    this.personasServices = new PersonasServices();
  },
  mounted() {
    this.personasServices.getAll().then(data => {
      console.log(data);
    })
  }
}

</script>

<template>

</template>

<style scoped>

</style>