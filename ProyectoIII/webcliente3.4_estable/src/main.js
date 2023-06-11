import './assets/main.css'

import { createApp } from 'vue'
import App from './components/IniciarSesion.vue'
import router from './router.js'

const app = createApp(App)
app.use(router);
app.mount('#app');
