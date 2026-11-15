<script setup>

import { ref, computed, onMounted } from 'vue'
import { Modal } from 'bootstrap'


import CustomerOrderService from '../services/CustomerOrderService'
import ProductService from '../services/ProductService'
import OrderItemService from '../services/OrderItemService'
import OrderCostBreakupService from '../services/OrderCostBreakupService'
import AdditionalCostService from '../services/AdditionalCostService'
import BillService from '../services/BillService'

const approvedOrders = ref([])

const products = ref([])

const orderItems = ref([])

const costBreakups = ref([])

const additionalCosts = ref([])

const bills = ref([])

const selectedOrder = ref(null)

const activeTab = ref('orders')

let billPreviewModal = null

function loadApprovedOrders() {

    CustomerOrderService
        .getApproved()
        .then(response => {

            approvedOrders.value =
                response.data

        })

}

function loadProducts() {

    ProductService
        .getAll()
        .then(response => {

            products.value =
                response.data

        })

}

function loadBills() {

    BillService
        .getAll()
        .then(response => {

            bills.value =
                response.data

        })

}

function getProductName(productId) {

    const product =
        products.value.find(
            p => p.productId == productId
        )

    return product
        ? product.productName
        : ''

}

function getUnitCost(orderItemId) {

    return costBreakups.value
        .filter(
            breakup =>
                breakup.orderItemId ==
                orderItemId
        )
        .reduce(

            (total,row) =>

                total +
                Number(row.amount),

            0

        )

}

function getItemAmount(item) {

    return getUnitCost(
        item.orderItemId
    ) *
    Number(item.quantity)

}

async function selectOrder(order) {

    selectedOrder.value = order

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

    costBreakups.value =
        breakupResponse.data

    const additionalResponse =
        await AdditionalCostService
            .getByOrderId(
                order.orderId
            )

    additionalCosts.value =
        additionalResponse.data

    billPreviewModal.show()
}

const productTotal =
computed(() => {

    return orderItems.value
        .reduce(

            (total,item) =>

                total +
                getItemAmount(item),

            0

        )

})

const additionalTotal =
computed(() => {

    return additionalCosts.value
        .reduce(

            (total,cost) =>

                total +
                Number(cost.amount),

            0

        )

})

const grandTotal =
computed(() => {

    return productTotal.value +
           additionalTotal.value

})

function generateBill() {

    if(!selectedOrder.value){

        alert('Select Order')

        return

    }

    const bill = {

        orderId:
            selectedOrder.value.orderId,

        productTotal:
            productTotal.value,

        additionalTotal:
            additionalTotal.value,

        grandTotal:
            grandTotal.value

    }

    BillService
        .save(bill)
       .then(() => {

    alert(
        'Bill Generated Successfully'
    )

    loadBills()

    billPreviewModal.hide()

})


}

function downloadBill(bill){

    alert(
        'PDF download will be implemented later'
    )

}

function sendEmail(bill){

    alert(
        'Email functionality will be implemented later'
    )

}

onMounted(() => {

    loadApprovedOrders()

    loadProducts()

    loadBills()

    billPreviewModal =
        new Modal(
            document.getElementById(
                'billPreviewModal'
            )
        )

})

</script>

<template>

<div>

    <h2 class="page-title">

        Generate Bill

    </h2>
<ul class="nav nav-tabs mb-3">

    <li class="nav-item">

        <button
            class="nav-link"
            :class="{
                active:
                activeTab === 'orders'
            }"
            @click="
                activeTab = 'orders'
            "
        >

            Orders Ready For Billing

        </button>

    </li>

    <li class="nav-item">

        <button
            class="nav-link"
            :class="{
                active:
                activeTab === 'bills'
            }"
            @click="
                activeTab = 'bills'
            "
        >

            Generated Bills

        </button>

    </li>

</ul>
    <!-- APPROVED ORDERS -->

   <div
    v-if="
        activeTab === 'orders'
    "
    class="card erp-card"
>

        <div class="card-header erp-card-header">

            Orders Ready For Billing

        </div>

        <div class="card-body">

            <table
                class="table table-bordered erp-table">

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
                                @click="
                                selectOrder(order)
                                ">

                                Preview Bill

                            </button>

                        </td>

                    </tr>

                </tbody>

            </table>

        </div>

    </div>

  

    <!-- GENERATED BILLS -->

    <div
    v-if="
        activeTab === 'bills'
    "
    class="card erp-card"
>

        <div class="card-header erp-card-header">

            Generated Bills

        </div>

        <div class="card-body">

            <table
                class="table table-bordered">

                <thead>

                    <tr>

                        <th>
                            Bill Id
                        </th>

                        <th>
                            Order Id
                        </th>

                        <th>
                            Product Total
                        </th>

                        <th>
                            Additional Total
                        </th>

                        <th>
                            Grand Total
                        </th>

                        <th>
                            Bill Date
                        </th>

                        <th>
                            Action
                        </th>

                    </tr>

                </thead>

                <tbody>

                    <tr
                        v-for="bill in bills"
                        :key="bill.billId">

                        <td>

                            {{ bill.billId }}

                        </td>

                        <td>

                            {{ bill.orderId }}

                        </td>

                        <td>

                            ₹ {{ bill.productTotal }}

                        </td>

                        <td>

                            ₹ {{ bill.additionalTotal }}

                        </td>

                        <td>

                            ₹ {{ bill.grandTotal }}

                        </td>

                        <td>

                            {{ bill.billDate }}

                        </td>

                        <td>

                            <button
                                class="btn btn-primary btn-sm me-2"
                                @click="
                                downloadBill(
                                    bill
                                )
                                ">

                                Download

                            </button>

                            <button
                                class="btn btn-info btn-sm"
                                @click="
                                sendEmail(
                                    bill
                                )
                                ">

                                Email

                            </button>

                        </td>

                    </tr>

                </tbody>

            </table>

        </div>

    </div>

</div>

<div
    class="modal fade"
    id="billPreviewModal"
    tabindex="-1"
>

    <div
        class="modal-dialog modal-xl"
    >

        <div class="modal-content">

            <div
                class="modal-header"
            >

                <h5
                    class="modal-title"
                >

                    Invoice Preview

                </h5>

                <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                >
                </button>

            </div>

            <div
                class="modal-body"
                style="
                    max-height:80vh;
                    overflow-y:auto;
                "
            >

                <table
                    class="table table-bordered"
                >

                    <thead>

                        <tr>

                            <th>
                                Product
                            </th>

                            <th>
                                Qty
                            </th>

                            <th>
                                Rate
                            </th>

                            <th>
                                Amount
                            </th>

                        </tr>

                    </thead>

                    <tbody>

                        <tr
                            v-for="
                            item
                            in
                            orderItems
                            "
                            :key="
                            item.orderItemId
                            "
                        >

                            <td>

                                {{
                                    getProductName(
                                        item.productId
                                    )
                                }}

                            </td>

                            <td>

                                {{
                                    item.quantity
                                }}

                            </td>

                            <td>

                                {{
                                    getUnitCost(
                                        item.orderItemId
                                    )
                                }}

                            </td>

                            <td>

                                {{
                                    getItemAmount(
                                        item
                                    )
                                }}

                            </td>

                        </tr>

                    </tbody>

                </table>

                <hr>

                <h5>

                    Product Total :

                    ₹ {{ productTotal }}

                </h5>

                <h5>

                    Additional Cost :

                    ₹ {{ additionalTotal }}

                </h5>

                <h3
                    class="text-success"
                >

                    Grand Total :

                    ₹ {{ grandTotal }}

                </h3>

            </div>

            <div
                class="modal-footer"
            >

                <button
                    class="
                    btn btn-success
                    "
                    @click="
                    generateBill
                    "
                >

                    Generate Bill

                </button>

                <button
                    type="button"
                    class="
                    btn btn-secondary
                    "
                    data-bs-dismiss="modal"
                >

                    Close

                </button>

            </div>

        </div>

    </div>

</div>

</template>