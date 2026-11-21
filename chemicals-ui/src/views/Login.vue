<script setup>

import { ref } from 'vue'
import { useRouter } from 'vue-router'

import LoginService
from '../services/LoginService'

const router = useRouter()

const loginData = ref({

    email: '',

    passwordHash: ''

})

function login() {
console.log("kkkkk");
    if (
        !loginData.value.email ||
        !loginData.value.passwordHash
    ) {
        alert('Enter Email And Password')
        return
    }

    loading.value = true

    LoginService
        .login(loginData.value)
        .then(response => {

            if (response.data) {

                sessionStorage.setItem(
                    'user',
                    JSON.stringify(response.data)
                )

                router.push('/')

            } else {

                alert(
                    'Invalid Email Or Password'
                )

            }

        })
        .catch(error => {

            console.log(error)

            alert(
                'Login Failed'
            )

        })
        .finally(() => {

            loading.value = false

        })
}
function placeOrder() {

    router.push(
        '/place-order'
    )

}

</script>

<template>

<div
    class="container-fluid vh-100 d-flex justify-content-center align-items-center bg-light">

    <div
        class="card shadow"
        style="width: 400px;">

        <div
            class="card-header bg-primary text-white">

            <h4 class="mb-0">

                Swastik Chemicals ERPp

            </h4>

        </div>

        <div class="card-body">

            <div class="mb-3">

                <label
                    class="form-label">

                    Email

                </label>

                <input
                    type="email"
                    class="form-control"
                    v-model="
                    loginData.email">

            </div>

            <div class="mb-3">

                <label
                    class="form-label">

                    Password

                </label>

                <input
                    type="password"
                    class="form-control"
                    v-model="
                    loginData.passwordHash">

            </div>

            <div
                class="d-grid gap-2">

               <button
    class="btn btn-primary"
    @click="login"
    :disabled="loading">

    <span
        v-if="loading"
        class="spinner-border spinner-border-sm me-2">
    </span>

    {{ loading ? 'Logging In...' : 'Login' }}

</button>

                <button
                    class="btn btn-success"
                    @click="
                    placeOrder
                    ">

                    Place Order as Guest

                </button>

            </div>

        </div>

    </div>

</div>

</template>
