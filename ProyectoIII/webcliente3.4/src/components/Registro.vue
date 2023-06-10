<template>
  <div>
    <h1>Registro</h1>
    <form @submit.prevent="registrarUsuario">
      <div>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" v-model="nombre" required>
      </div>
      <div>
        <label for="apellidos">Apellidos:</label>
        <input type="text" id="apellidos" v-model="apellidos" required>
      </div>
      <div>
        <label for="correo">Correo:</label>
        <input type="email" id="correo" v-model="correo" required>
      </div>
      <div>
        <label for="clave">Clave:</label>
        <input type="password" id="clave" v-model="clave" required>
      </div>
      <div>
        <button type="submit">Registrar Usuario Nuevo</button>
      </div>
    </form>
  </div>
</template>

<script>
import { ref, onMounted, getCurrentInstance } from "vue";
import axios from "axios";

export default {
  name: "Registro",
  setup() {
    const nombre = ref("");
    const apellidos = ref("");
    const correo = ref("");
    const clave = ref("");

    const { emit } = getCurrentInstance();

    const registrarUsuario = () => {
      const persona = {
        nombre: nombre.value,
        apellidos: apellidos.value,
        correo: correo.value,
        clave: clave.value
      };

      axios.post("http://localhost:8080/add", persona)
          .then(response => {
            console.log(response.data);

            // Luego de registrar, puedes llamar al método retornar_algoritmoHuffman
            const datosClave = {
              clave: clave.value
            };
            axios.post("http://localhost:8080/COMPRIMIRclave", datosClave)
                .then(response => {
                  console.log(response.data);
                })
                .catch(error => {
                  console.error(error);
                });

            // Luego de registrar y llamar al método, puedes limpiar los campos de formulario
            nombre.value = "";
            apellidos.value = "";
            correo.value = "";
            clave.value = "";

            // Emitir evento al componente padre para indicar que se ha completado el registro
            emit("registroCompletado");
          })
          .catch(error => {
            console.error(error);
          });
    };

    return {
      nombre,
      apellidos,
      correo,
      clave,
      registrarUsuario
    };
  }
};
</script>

<style>
h1 {
  text-align: center;
  color: darkseagreen;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

label {
  margin-top: 10px;
}

input {
  width: 200px;
  margin-top: 5px;
}

button {
  margin-top: 10px;
}
</style>