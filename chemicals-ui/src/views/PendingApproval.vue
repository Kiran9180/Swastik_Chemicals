<script setup>

import { ref, onMounted } from 'vue'

import CustomerOrderService
from '../services/CustomerOrderService'

import OrderItemService
from '../services/OrderItemService'

const orders = ref([])

const selectedOrderItems = ref([])

const selectedOrderId = ref(null)

function loadOrders() {

    CustomerOrderService
        .getPending()
        .then(response => {

            orders.value = response.data

        })
        .catch(error => {

            console.log(error)

        })
}

function viewItems(orderId) {

    selectedOrderId.value = orderId

    OrderItemService
        .getByOrderId(orderId)
        .then(response => {

            selectedOrderItems.value =
                response.data

        })
        .catch(error => {

            console.log(error)

        })
}

function approveOrder(orderId) {

    if(!confirm(
        'Approve this order ?'
    )){
        return
    }

    CustomerOrderService
        .approve(orderId)
        .then(() => {

            alert(
                'Order Approved Successfully'
            )

            loadOrders()

        })
        .catch(error => {

            console.log(error)

        })
}

function rejectOrder(orderId) {

    if(!confirm(
        'Reject this order ?'
    )){
        return
    }

    CustomerOrderService
        .reject(orderId)
        .then(() => {

            alert(
                'Order Rejected Successfully'
            )

            loadOrders()

        })
        .catch(error => {

            console.log(error)

        })
}

onMounted(() => {

    loadOrders()

})

</script>

<template>

<div>

    <h2>
        Pending Approval
    </h2>

    <div class="card">

        <div class="card-body">

            <table
                class="table table-bordered table-striped">

                <thead class="table-dark">

                    <tr>

                        <th>
                            Order Id
                        </th>

                        <th>
                            Taluka
                        </th>

                        <th>
                            Village
                        </th>

                        <th>
                            District
                        </th>

                        <th>
                            Status
                        </th>

                        <th width="300">
                            Actions
                        </th>

                    </tr>

                </thead>

                <tbody>

                    <tr
                        v-for="order in orders"
                        :key="order.orderId">

                        <td>
                            {{ order.orderId }}
                        </td>

                        <td>
                            {{ order.taluka }}
                        </td>

                        <td>
                            {{ order.village }}
                        </td>

                        <td>
                            {{ order.district }}
                        </td>

                        <td>
                            {{ order.status }}
                        </td>

                        <td>

                            <button
                                class="btn btn-info btn-sm me-2"
                                @click="
                                viewItems(
                                order.orderId
                                )">

                                View

                            </button>

                            <button
                                class="btn btn-success btn-sm me-2"
                                @click="
                                approveOrder(
                                order.orderId
                                )">

                                Approve

                            </button>

                            <button
                                class="btn btn-danger btn-sm"
                                @click="
                                rejectOrder(
                                order.orderId
                                )">

                                Reject

                            </button>

                        </td>

                    </tr>

                    <tr v-if="orders.length === 0">

                        <td
                            colspan="6"
                            class="text-center">

                            No Pending Orders

                        </td>

                    </tr>

                </tbody>

            </table>

        </div>

    </div>

    <br>

    <div
        v-if="selectedOrderId"
        class="card">

        <div class="card-header">

            Order Items -
            Order #{{ selectedOrderId }}

        </div>

        <div class="card-body">

            <table
                class="table table-bordered">

                <thead>

                    <tr>

                        <th>
                            Product Id
                        </th>

                        <th>
                            Quantity
                        </th>

                    </tr>

                </thead>

                <tbody>

                    <tr
                        v-for="item in selectedOrderItems"
                        :key="
                        item.orderItemId
                        ">

                        <td>
                            {{ item.productId }}
                        </td>

                        <td>
                            {{ item.quantity }}
                        </td>

                    </tr>

                    <tr
                        v-if="
                        selectedOrderItems.length === 0
                        ">

                        <td
                            colspan="2"
                            class="text-center">

                            No Items Found

                        </td>

                    </tr>

                </tbody>

            </table>

        </div>

    </div>

</div>

</template>