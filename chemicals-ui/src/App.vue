<script setup>

import { computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()

const route = useRoute()

const showSidebar = computed(() => {

    return ![
        '/login',
        '/place-order'
    ].includes(route.path)

})

function logout() {

    sessionStorage.removeItem(
        'user'
    )

    router.push('/login')

}

</script>

<template>

<div>

    <!-- SIDEBAR -->

    <div
        v-if="showSidebar"
        class="sidebar bg-dark text-white">

        <h4 class="mt-3 px-3">

            Swastik Chemicals
        </h4>

        <hr>

        <ul class="nav flex-column">

            <li class="nav-item">
                <router-link
                    to="/"
                    class="nav-link text-white">

                    Dashboard

                </router-link>
            </li>

            <li class="nav-item">
                <router-link
                    to="/product"
                    class="nav-link text-white">

                    Product

                </router-link>
            </li>

            <li class="nav-item">
                <router-link
                    to="/product-cost-breakup"
                    class="nav-link text-white">

                    Product Cost Breakup

                </router-link>
            </li>

            <li class="nav-item">
                <router-link
                    to="/customer-order"
                    class="nav-link text-white">

                    Customer Order

                </router-link>
            </li>

            <li class="nav-item">
                <router-link
                    to="/pending-approval"
                    class="nav-link text-white">

                    Pending Approval

                </router-link>
            </li>

            <li class="nav-item">
                <router-link
                    to="/cost-calculation"
                    class="nav-link text-white">

                    Cost Calculation

                </router-link>
            </li>

            <li class="nav-item">
                <router-link
                    to="/bill"
                    class="nav-link text-white">

                    Bill

                </router-link>
            </li>

        </ul>

        <hr>

        <div class="px-3">

            <button
                class="btn btn-danger w-100"
                @click="logout">

                Logout

            </button>

        </div>

    </div>

    <!-- PAGE CONTENT -->

    <div
        :class="
            showSidebar
                ? 'main-content'
                : ''
        ">

        <router-view />

    </div>

</div>

</template>