<script>
import ServiciosPersonas from "@/servicios/ServiciosPersonas";
export default {
  name : 'CrudApp',
  data(){
    return {
      personas : null
    }
  },
  serviciosPersonas : null,
  created() {
    this.serviciosPersonas = new ServiciosPersonas();
  },
  mounted() {
    this.serviciosPersonas.getAll().then(data => {
      this.personas = data.data;
      console.log(this.personas);
    });
  }
}


</script>

<template>

</template>

<style>

</style>