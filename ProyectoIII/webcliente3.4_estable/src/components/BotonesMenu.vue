<template>
  <div>
    <h1>Manejo de métodos de Base de Datos-Proyecto III</h1>

    <div class="button-container">
      <button @click="mostrarAgregarInstancia">Agregar Instancias</button>
      <button @click="buscarInstanciasSelect">Buscar Instancias (Select)</button>
      <button @click="buscarInstanciasJoin">Buscar Instancias (Join)</button>
      <button @click="eliminarUnaInstancia">Eliminar una instancia</button>
      <button @click="eliminarInstancias">Eliminar instancias</button>
      <button @click="actualizarInstancias">Actualizar instancias</button>
      <button @click="actualizarUnaInstancia">Actualizar una instancia</button>
    </div>

    <AgregarInstancia v-if="mostrarComponenteAgregarInstancia" />
    <BuscarInstanciaSELECT v-if="mostrarComponenteBuscarInstanciaSELECT"/>
    <BuscarInstanciasJOIN v-if="mostrarComponenteBuscarInstanciaJOIN"/>
    <EliminarInstancia v-if="mostrarComponenteEliminarInstancia"/>
    <EliminarInstancias v-if="mostrarComponenteEliminarInstancias"/>
    <ActualizarInstancias v-if="mostrarComponenteActualizarInstancias"/>
    <ActualizarInstancia v-if="mostrarComponenteActualizarInstancia"/>

  </div>
</template>

<script>
/// importaciones de componetnes
import AgregarInstancia from "./AgregarInstancia.vue";
import BuscarInstanciaSELECT from "@/components/BuscarInstanciaSELECT.vue";
import BuscarInstanciasJOIN from "@/components/BuscarInstanciasJOIN.vue";
import EliminarInstancia from "@/components/EliminarInstancia.vue";
import EliminarInstancias from "@/components/EliminarInstancias.vue";
import ActualizarInstancias from "@/components/ActualizarInstancias.vue";
import ActualizarInstancia from "@/components/ActualizarInstancia.vue";

export default {
  components: {
    AgregarInstancia,
    BuscarInstanciaSELECT,
    BuscarInstanciasJOIN,
    EliminarInstancia,
    EliminarInstancias,
    ActualizarInstancias,
    ActualizarInstancia
  },
  data() {
    return {
      mostrarComponenteAgregarInstancia: false,
      mostrarComponenteBuscarInstanciaSELECT: false,
      mostrarComponenteBuscarInstanciaJOIN: false,
      mostrarComponenteEliminarInstancia: false,
      mostrarComponenteEliminarInstancias: false,
      mostrarComponenteActualizarInstancias: false,
      mostrarComponenteActualizarInstancia: false,

    };
  },
  methods: {
    mostrarAgregarInstancia() {
      this.mostrarComponenteAgregarInstancia = true;
      this.mostrarComponenteBuscarInstanciaSELECT = false;
      this.mostrarComponenteBuscarInstanciaJOIN = false;
      this.mostrarComponenteEliminarInstancia= false;
      this.mostrarComponenteEliminarInstancias = false;
      this.mostrarComponenteActualizarInstancias = false;
      this.mostrarComponenteActualizarInstancia = false;
    },
    buscarInstanciasSelect() {
      // Lógica para buscar instancias con Select
      this.mostrarComponenteBuscarInstanciaSELECT = true;
      this.mostrarComponenteAgregarInstancia =false;
      this.mostrarComponenteBuscarInstanciaJOIN = false;
      this.mostrarComponenteEliminarInstancia= false;
      this.mostrarComponenteEliminarInstancias = false;
      this.mostrarComponenteActualizarInstancias = false;
      this.mostrarComponenteActualizarInstancia = false;
    },
    buscarInstanciasJoin() {
      // Lógica para buscar instancias con Join
      this.mostrarComponenteBuscarInstanciaJOIN = true;
      this.mostrarComponenteAgregarInstancia = false;
      this.mostrarComponenteBuscarInstanciaSELECT = false;
      this.mostrarComponenteEliminarInstancia= false;
      this.mostrarComponenteEliminarInstancias = false;
      this.mostrarComponenteActualizarInstancias = false;
      this.mostrarComponenteActualizarInstancia = false;
    },
    eliminarUnaInstancia() {
      // Lógica para eliminar una instancia
      this.mostrarComponenteAgregarInstancia = false;
      this.mostrarComponenteBuscarInstanciaSELECT = false;
      this.mostrarComponenteBuscarInstanciaJOIN = false;
      this.mostrarComponenteEliminarInstancia= true;
      this.mostrarComponenteEliminarInstancias = false;
      this.mostrarComponenteActualizarInstancias = false;
      this.mostrarComponenteActualizarInstancia = false;
    },
    eliminarInstancias() {
      // Lógica para eliminar instancias
      this.mostrarComponenteEliminarInstancias = true;
      this.mostrarComponenteAgregarInstancia = false;
      this.mostrarComponenteBuscarInstanciaSELECT = false;
      this.mostrarComponenteBuscarInstanciaJOIN = false;
      this.mostrarComponenteEliminarInstancia= false;
      this.mostrarComponenteActualizarInstancias = false;
      this.mostrarComponenteActualizarInstancia = false;
    },
    actualizarInstancias() {
      // Lógica para actualizar instancias
      this.mostrarComponenteActualizarInstancias = true;
      this.mostrarComponenteAgregarInstancia = false;
      this.mostrarComponenteBuscarInstanciaSELECT = false;
      this.mostrarComponenteBuscarInstanciaJOIN = false;
      this.mostrarComponenteEliminarInstancia= false;
      this.mostrarComponenteEliminarInstancias = false;
      this.mostrarComponenteActualizarInstancia = false;
    },
    actualizarUnaInstancia() {
      // Lógica para actualizar una instancia
      this.mostrarComponenteActualizarInstancia = true;
      this.mostrarComponenteAgregarInstancia = false;
      this.mostrarComponenteBuscarInstanciaSELECT = false;
      this.mostrarComponenteBuscarInstanciaJOIN = false;
      this.mostrarComponenteEliminarInstancia= false;
      this.mostrarComponenteEliminarInstancias = false;
      this.mostrarComponenteActualizarInstancias = false;
    }
  }
};
</script>

<style>
h1 {
  text-align: center;
  color: darkseagreen;
}

button {
  display: block;
  margin-top: 10px;
}
</style>