<script>

import BarraNavegacion from "@/componentes/BarraNavegacion.vue";

export default {
  name: 'VistaPersonas',
  components : {
    BarraNavegacion
  },
  data(){
    return {
       personas: []
    }
  },

  methods: {
    get_Personas(){
      fetch('http://localhost:8080/personas')
          .then(res => res.json())
          .then(data => {
            this.personas = data
            console.log(data)
          })
    },

    eliminar_PersonasID(id){
      fetch(`http://localhost:8080/eliminarpersona/${id}`,{
        method: 'ELIMINAR'
      })
          .then(data=> {
            console.log(data)
            this.get_Personas()
          })
    }

  }
}

</script>

<template>
  <main>
    <BarraNavegacion />

    <!-- tabla -->
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-center">Vista Personas</h1>

          <a href="/add" class="btn btn-primary">Agregar persona</a>
          <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Nombre</th>
                <th scope="col">Apellidos</th>
                <th scope="col">Correo electrónico</th>
                <th scope="col">contraseña</th>
                <!-- deberia haber una accion aca de ser necesario -->
              </tr>
            </thead>
            <tbody>
              <tr v-for="persona in personas":key="persona.id">
                <th scope="row">{{ persona.id }}}}</th>
                <td> {{ persona.nombre}} </td>
                <td> {{ persona.correo }} </td>
                <td> {{persona.correo}}</td>
                <td> {{persona.clave}} </td>
                <td>
                    <a class="btn btn-primary":href="'/edit/${patient.id}'">Editar </a>
                    <button class="btn btn-danger mx-2" @click="eliminar_PersonasID(persona.id)">Eliminar Persona</button>
                </td>

              </tr>
            </tbody>
          </table>

        </div>
      </div>
    </div>



  </main>

</template>



<style scoped>

</style>