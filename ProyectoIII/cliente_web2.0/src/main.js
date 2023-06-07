import './assets/main.css'
import DataTable from "primevue/datatable";
import Column from "primevue/column";

import { createApp } from 'vue';
import App from './App.vue';

import 'primevue/resources/themes/saga-blue/theme.css';
import 'primevue/resources/primevue.min.css';
import 'primeicons/primeicons.css';

const app = createApp(App);
app.component('Datatable', DataTable);
app.component('Column', Column);

app.mount('#app');
