import { createRouter, createWebHistory } from 'vue-router'
import LoginForm from './components/LoginForm.vue'
import RegisterForm from './components/RegisterForm.vue'
import FileUpload from "./components/FileUpload.vue";

const routes = [
    { path: '/login', component: LoginForm },
    { path: '/register', component: RegisterForm },
    { path: '/upload', component: FileUpload},
    { path: '/', redirect: '/login' } // default
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
