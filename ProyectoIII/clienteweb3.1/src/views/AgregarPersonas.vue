<script>

import BarraNavegacion from "@/componentes/BarraNavegacion.vue";

export default {
  name: 'AgregarPersona',
  components: {
    BarraNavegacion
  },

  data() {
    return{
      persona: {
        nombre: '',
        apellidos: '',
        correo: '',
        clave: '',
      }
    }
  },

  methods: {
    agregar_Persona() {
      fetch('http://localhost:8080/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.persona)
      })
          .then(data => {
            console.log(data)
            this.$router.push("/")
          })
    }
  }
}

</script>

<template>
  <main>

    <BarraNavegacion/>
    <div class="my-5">
      <div class="mx-auto w-25" style="max-width: 100%;">
        <h2 class="text-center mb-3">Agregar Persona</h2>
        <form @submit.prevent="agregar_Persona">

        </form>
      </div>
    </div>

  </main>

</template>

<style scoped>

</style>