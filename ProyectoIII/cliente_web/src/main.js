import { createApp } from 'vue'

import App from './App.vue'
import DataTable from "primevue/datatable";
import Column from "primevue/column";

import './assets/main.css'

//const app = createApp(App)

createApp(App).component('DataTable', DataTable)
createApp(App).component('Column', Column);

createApp(App).mount('#app')
