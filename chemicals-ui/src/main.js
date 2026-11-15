import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import 'bootstrap/dist/css/bootstrap.min.css'

import './assets/css/theme.css'
import './assets/css/tables.css'
import './assets/css/forms.css'

createApp(App)
.use(router)
.mount('#app')