<script setup>

import { ref, computed, onMounted } from 'vue'

import CustomerOrderService from '../services/CustomerOrderService'
import ProductService from '../services/ProductService'
import OrderItemService from '../services/OrderItemService'
import OrderCostBreakupService from '../services/OrderCostBreakupService'
import AdditionalCostService from '../services/AdditionalCostService'
import { Modal } from 'bootstrap'

let costModal = null

const approvedOrders = ref([])

const products = ref([])

const selectedOrder = ref(null)

const orderItems = ref([])

const orderBreakups = ref([])

const additionalCosts = ref([])



const newAdditionalCost = ref({
    costName: '',
    amount: 0,
    remarks: ''
})

function loadApprovedOrders() {

    CustomerOrderService
        .getApproved()
        .then(response => {

            approvedOrders.value =
                response.data

        })
        .catch(error => {

            console.log(error)

        })
}

function loadProducts() {

    ProductService
        .getAll()
        .then(response => {

            products.value =
                response.data

        })
        .catch(error => {

            console.log(error)

        })
}

function getProductName(productId) {

    const product =
        products.value.find(
            p => p.productId == productId
        )

    return product
        ? product.productName
        : productId
}

function getOrderItem(orderItemId) {

    return orderItems.value.find(
        item => item.orderItemId == orderItemId
    )

}

function getProductBreakupTotal(orderItemId) {

    return orderBreakups.value
        .filter(
            breakup =>
                breakup.orderItemId == orderItemId
        )
        .reduce(
            (total,row) =>
                total + Number(row.amount),
            0
        )

}


async function selectOrder(order) {

    selectedOrder.value = order

    try {

        const orderItemsResponse =
            await OrderItemService
                .getByOrderId(
                    order.orderId
                )

        orderItems.value =
            orderItemsResponse.data

        const breakupResponse =
            await OrderCostBreakupService
                .getByOrderId(
                    order.orderId
                )

        orderBreakups.value =
            breakupResponse.data

        const additionalResponse =
            await AdditionalCostService
                .getByOrderId(
                    order.orderId
                )

        additionalCosts.value =
            additionalResponse.data

            costModal.show()

    }
    catch(error) {

        console.log(error)

    }
}



function updateBreakup(breakup) {

    OrderCostBreakupService
        .update(breakup)
        .then(() => {

            alert(
                'Breakup Updated'
            )

        })
}

function deleteBreakup(id) {

    if(!confirm(
        'Delete Cost Breakup ?'
    )) {

        return
    }

    OrderCostBreakupService
        .delete(id)
        .then(() => {

            selectOrder(
                selectedOrder.value
            )

        })
}

function addAdditionalCost() {

    if(
        !newAdditionalCost
            .value
            .costName
            .trim()
    ) {

        alert(
            'Enter Cost Name'
        )

        return
    }

    if(
        newAdditionalCost
            .value
            .amount <= 0
    ) {

        alert(
            'Enter Amount'
        )

        return
    }

    AdditionalCostService
        .save({

            orderId:
                selectedOrder
                    .value
                    .orderId,

            costName:
                newAdditionalCost
                    .value
                    .costName,

            amount:
                newAdditionalCost
                    .value
                    .amount,

            remarks:
                newAdditionalCost
                    .value
                    .remarks

        })
        .then(() => {

            selectOrder(
                selectedOrder.value
            )

            newAdditionalCost.value = {

                costName: '',
                amount: 0,
                remarks: ''

            }

        })
}

function deleteAdditionalCost(id) {

    if(
        !confirm(
            'Delete Additional Cost ?'
        )
    ) {

        return
    }

    AdditionalCostService
        .delete(id)
        .then(() => {

            selectOrder(
                selectedOrder.value
            )

        })
}

const productCostTotal =
computed(() => {

    return orderBreakups.value.reduce(

        (total,row) =>

            total +
            Number(row.amount),

        0

    )

})

const additionalCostTotal =
computed(() => {

    return additionalCosts.value.reduce(

        (total,row) =>

            total +
            Number(row.amount),

        0

    )

})

const grandTotal =
computed(() => {

    return productCostTotal.value +

           additionalCostTotal.value

})

onMounted(() => {

    loadApprovedOrders()

    loadProducts()

      costModal = new Modal(
        document.getElementById('costCalculationModal')
    )

})

</script>

<template>

<div>

    <h2>
        Cost Calculation
    </h2>

    <div class="card">

        <div class="card-header">

            Approved Orders

        </div>

        <div class="card-body">

            <table
                class="table table-bordered">

                <thead>

                    <tr>

                        <th>Order Id</th>
                        <th>Taluka</th>
                        <th>Village</th>
                        <th>District</th>
                        <th>Action</th>

                    </tr>

                </thead>

                <tbody>

                    <tr
                        v-for="order in approvedOrders"
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

                            <button
                                class="btn btn-primary btn-sm"
                                @click="selectOrder(order)">

                                Calculate

                            </button>

                        </td>

                    </tr>

                </tbody>

            </table>

        </div>

    </div>

    


















<div
        class="modal fade"
        id="costCalculationModal"
        tabindex="-1"
        data-bs-backdrop="static"
    >
        <div class="modal-dialog modal-xl">
            <div class="modal-content">

                <div class="modal-header">
                    <h5 class="modal-title">
                        Cost Calculation
                    </h5>

                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal">
                    </button>
                </div>

                <div
                    class="modal-body"
                    style="max-height:80vh;overflow-y:auto;"
                >

                    <div
        v-if="selectedOrder"
        class="card mt-3">

        <div class="card-header">

            Order Products

        </div>

        <div class="card-body">

            <table
                class="table table-bordered">

                <thead>

                    <tr>

                        <th>Product</th>
                        <th>Quantity</th>

                    </tr>

                </thead>

                <tbody>

                    <tr
                        v-for="item in orderItems"
                        :key="item.orderItemId">

                        <td>

                            {{
                                getProductName(
                                    item.productId
                                )
                            }}

                        </td>

                        <td>

                            {{ item.quantity }}

                        </td>

                    </tr>

                </tbody>

            </table>

        </div>

    </div>

    

    <div
        v-if="selectedOrder"
        class="card mt-3">

        <div class="card-header">

            Cost Breakups

        </div>

        <div class="card-body">

            <table
                class="table table-bordered">

                <thead class="table-dark">

<tr>

    <th>Product</th>

    <th>Cost Name</th>

    <th>Amount</th>

    <th>Action</th>

</tr>

</thead>

                <tbody>

                    <tr
    v-for="breakup in orderBreakups"
    :key="breakup.orderCostBreakupId">

    <td>

        {{
            getProductName(
                getOrderItem(
                    breakup.orderItemId
                )?.productId
            )
        }}

    </td>

    <td>

        {{ breakup.costName }}

    </td>

    <td>

        <input
            type="number"
            class="form-control"
            v-model="breakup.amount">

    </td>

    <td>

        <button
            class="btn btn-warning btn-sm me-2"
            @click="updateBreakup(breakup)">

            Save

        </button>

        <button
            class="btn btn-danger btn-sm"
            @click="
                deleteBreakup(
                    breakup.orderCostBreakupId
                )
            ">

            Delete

        </button>

    </td>

</tr>
                </tbody>

            </table>

        </div>

    </div>


    <div
    v-if="selectedOrder"
    class="card mt-3">

    <div class="card-header">

        Product Cost Summary

    </div>

    <div class="card-body">

        <table
            class="table table-bordered table-striped">

            <thead class="table-dark">

                <tr>

                    <th>Product</th>

                    <th>Quantity</th>

                    <th>Unit Cost</th>

                </tr>

            </thead>

            <tbody>

                <tr
                    v-for="item in orderItems"
                    :key="item.orderItemId">

                    <td>

                        {{
                            getProductName(
                                item.productId
                            )
                        }}

                    </td>

                    <td>

                        {{ item.quantity }}

                    </td>

                    <td>

                        ₹
                        {{
                            getProductBreakupTotal(
                                item.orderItemId
                            )
                        }}

                    </td>

                </tr>

            </tbody>

        </table>

    </div>

</div>

    <div
        v-if="selectedOrder"
        class="card mt-3">

        <div class="card-header">

            Additional Costs

        </div>

        <div class="card-body">

            <div class="row">

                <div class="col-md-3">

                    <input
                        class="form-control"
                        placeholder="Cost Name"
                        v-model="
                        newAdditionalCost.costName">

                </div>

                <div class="col-md-2">

                    <input
                        type="number"
                        class="form-control"
                        placeholder="Amount"
                        v-model="
                        newAdditionalCost.amount">

                </div>

                <div class="col-md-5">

                    <input
                        class="form-control"
                        placeholder="Remarks"
                        v-model="
                        newAdditionalCost.remarks">

                </div>

                <div class="col-md-2">

                    <button
                        class="btn btn-success"
                        @click="
                        addAdditionalCost">

                        Add

                    </button>

                </div>

            </div>

            <hr>

            <table
                class="table table-bordered">

                <thead>

                    <tr>

                        <th>
                            Cost Name
                        </th>

                        <th>
                            Amount
                        </th>

                        <th>
                            Remarks
                        </th>

                        <th>
                            Action
                        </th>

                    </tr>

                </thead>

                <tbody>

                    <tr
                        v-for="
                            cost
                            in
                            additionalCosts
                        "
                        :key="
                        cost.additionalCostId
                        ">

                        <td>
                            {{ cost.costName }}
                        </td>

                        <td>
                            {{ cost.amount }}
                        </td>

                        <td>
                            {{ cost.remarks }}
                        </td>

                        <td>

                            <button
                                class="btn btn-danger btn-sm"
                                @click="
                                deleteAdditionalCost(
                                cost.additionalCostId
                                )">

                                Delete

                            </button>

                        </td>

                    </tr>

                </tbody>

            </table>

        </div>

    </div>

    <div
        v-if="selectedOrder"
        class="card mt-3">

        <div class="card-header">

            Cost Summary

        </div>

        <div class="card-body">

            <h5>

                Product Cost Total :

                ₹ {{ productCostTotal }}

            </h5>

            <h5>

                Additional Cost Total :

                ₹ {{ additionalCostTotal }}

            </h5>

            <h4
                class="text-success">

                Grand Total :

                ₹ {{ grandTotal }}

            </h4>

        </div>

    </div>



                </div>

            </div>
        </div>
    </div>







</div>

</template>