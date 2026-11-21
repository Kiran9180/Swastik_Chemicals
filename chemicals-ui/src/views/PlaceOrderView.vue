<script setup>
import { ref, onMounted, watch } from 'vue'
import ProductService from '../services/ProductService'
import CustomerOrderService from '../services/CustomerOrderService'
import OrderItemService from '../services/OrderItemService'

const products = ref([])

const existingCustomer = ref(false)
const existingUser = ref(null)
const customerLocked = ref(false)

const showExistingCustomerModal = ref(false)

const customerOrder = ref({
    userId: null,
    customerName: '',
    mobile: '',
    email: '',
    shopName: '',
    gstNo: '',
    taluka: '',
    village: '',
    district: '',
    fullAddress: '',
    pincode: '',
    remarks: ''
})

const orderItems = ref([])

const editIndex = ref(-1)

const item = ref({
    productId: '',
    quantity: 0
})

function addOrUpdateItem() {

    if (!item.value.productId) {

        alert('Please Select Product')
        return
    }

    if (item.value.quantity <= 0) {

        alert('Quantity Must Be Greater Than Zero')
        return
    }

    const duplicateProduct =
        orderItems.value.some(
            row =>
                row.productId == item.value.productId &&
                editIndex.value === -1
        )

    if (duplicateProduct) {

        alert('Product Already Added')
        return
    }

    const product =
        products.value.find(
            p => p.productId == item.value.productId
        )

    const rowData = {
        productId: item.value.productId,
        productName: product.productName,
        quantity: item.value.quantity
    }

    if (editIndex.value >= 0) {

        orderItems.value[editIndex.value] =
            rowData

        editIndex.value = -1

    } else {

        orderItems.value.push(rowData)
    }

    resetForm()
}

function editItem(index) {

    const selectedItem =
        orderItems.value[index]

    item.value = {
        productId: selectedItem.productId,
        quantity: selectedItem.quantity
    }

    editIndex.value = index
}

function deleteItem(index) {

    orderItems.value.splice(index, 1)

    if (editIndex.value === index) {

        cancelEdit()
    }
}

function cancelEdit() {

    editIndex.value = -1

    resetForm()
}

function resetForm() {

    item.value = {
        productId: '',
        quantity: 0
    }
}

async function checkCustomer() {

 if (
  !customerOrder.value.mobile ||
  !customerOrder.value.email
 ) {
  return
 }

 try {

  const response =
   await CustomerOrderService.checkCustomer(
    customerOrder.value.mobile,
    customerOrder.value.email
   )

  if (response.data.exists) {

   existingCustomer.value = true
   existingUser.value = response.data.user
   showExistingCustomerModal.value = true

  } else {

   existingCustomer.value = false
   existingUser.value = null
   customerOrder.value.userId = null

  }

 } catch (error) {

  console.error(error)

 }

}


watch(
 () => [
  customerOrder.value.mobile,
  customerOrder.value.email
 ],
 async ([mobile, email]) => {

  if (
   !mobile ||
   !email ||
   mobile.trim() === '' ||
   email.trim() === ''
  ) {
   return
  }

  await checkCustomer()
 }
)
    
    try {

        const response =
            await CustomerOrderService.checkCustomer(
                customerOrder.value.mobile,
                customerOrder.value.email
            )

        if (response.data.exists) {

            existingCustomer.value = true

            existingUser.value =
                response.data.user

            showExistingCustomerModal.value =
                true

        } else {

            existingCustomer.value = false

            existingUser.value = null

            customerOrder.value.userId =
                null

           // alert('New Customer')
        }

    } catch (error) {

        console.error(error)

        alert(
            'Error Checking Customer'
        )
    }
}

function selectSameShop() {

 customerOrder.value.userId =
  existingUser.value.id

 customerOrder.value.customerName =
  existingUser.value.name

 customerOrder.value.mobile =
  existingUser.value.mobile

 customerOrder.value.email =
  existingUser.value.email

 customerOrder.value.shopName =
  existingUser.value.shopName

 customerOrder.value.gstNo =
  existingUser.value.gstNo || ''

 customerOrder.value.taluka =
  existingUser.value.taluka

 customerOrder.value.village =
  existingUser.value.village

 customerOrder.value.district =
  existingUser.value.district

 customerOrder.value.fullAddress =
  existingUser.value.address

 customerOrder.value.pincode =
  existingUser.value.pincode

 customerLocked.value = true

 showExistingCustomerModal.value = false
}
function selectDifferentShop() {

 customerOrder.value.userId =
  existingUser.value.id

 customerOrder.value.customerName =
  existingUser.value.name

 customerOrder.value.mobile =
  existingUser.value.mobile

 customerOrder.value.email =
  existingUser.value.email

 customerOrder.value.shopName = ''
 customerOrder.value.gstNo = ''
 customerOrder.value.taluka = ''
 customerOrder.value.village = ''
 customerOrder.value.district = ''
 customerOrder.value.fullAddress = ''
 customerOrder.value.pincode = ''

 customerLocked.value = false

 showExistingCustomerModal.value = false
}
function validateOrder() {

    if (
        !customerOrder.value.customerName.trim()
    ) {

        alert('Customer Name Is Required')
        return false
    }

    if (
        !customerOrder.value.mobile.trim()
    ) {

        alert('Mobile Is Required')
        return false
    }


    if (
 !customerOrder.value.email.trim()
) {
 alert('Email Is Required')
 return false
}

    if (
        !customerOrder.value.shopName.trim()
    ) {

        alert('Shop Name Is Required')
        return false
    }

    if (
        !customerOrder.value.taluka.trim()
    ) {

        alert('Taluka Is Required')
        return false
    }

    if (
        !customerOrder.value.village.trim()
    ) {

        alert('Village Is Required')
        return false
    }

    if (
        !customerOrder.value.district.trim()
    ) {

        alert('District Is Required')
        return false
    }

    if (
        !customerOrder.value.fullAddress.trim()
    ) {

        alert('Address Is Required')
        return false
    }

    if (
        orderItems.value.length === 0
    ) {

        alert(
            'Please Add At Least One Product'
        )

        return false
    }

    return true
}

async function submitOrder() {

    if (!validateOrder()) {

        return
    }

    try {

        const response =
            await CustomerOrderService.save(
                customerOrder.value
            )

        const orderId =
            response.data

        for (const row of orderItems.value) {

            const orderItem = {

                orderId: orderId,

                productId: row.productId,

                quantity: row.quantity
            }

            await OrderItemService.save(
                orderItem
            )
        }

        alert(
            'Order Saved Successfully'
        )

        customerOrder.value = {

            userId: null,

            customerName: '',

            mobile: '',

            email: '',

            shopName: '',

            gstNo: '',

            taluka: '',

            village: '',

            district: '',

            fullAddress: '',

            pincode: '',

            remarks: ''
        }

        orderItems.value = []

        customerLocked.value = false
existingUser.value = null
existingCustomer.value = false

        editIndex.value = -1

        resetForm()

    } catch (error) {

        console.error(error)

        alert(
            'Error Saving Order'
        )
    }
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

onMounted(() => {

    loadProducts()
})
</script>


<template>
    <div class="container-fluid">

        <h2 class="mb-3">
            Customer Order
        </h2>

        <!-- Customer Information -->

        <div class="card mb-3">

            <div class="card-header">
                Customer Information
            </div>

            <div class="card-body">

                <div class="row">

                    <div class="col-md-3 mb-3">

                        <label class="form-label">
                            Customer Name *
                        </label>
<input
 type="text"
 class="form-control"
 v-model="customerOrder.customerName"
 :disabled="customerLocked">

                    </div>

                    <div class="col-md-3 mb-3">

                        <label class="form-label">
                            Mobile *
                        </label>

                       <input
 type="text"
 class="form-control"
 v-model="customerOrder.mobile"
 :disabled="customerLocked">

                    </div>

                    <div class="col-md-3 mb-3">

                        <label class="form-label">
                            Email
                        </label>

                     <input
 type="email"
 class="form-control"
 v-model="customerOrder.email"
 :disabled="customerLocked">

                    </div>



                </div>

            </div>

        </div>

        <!-- Shop Information -->

        <div class="card mb-3">

            <div class="card-header">
                Shop Information
            </div>

            <div class="card-body">

                <div class="row">

                    <div class="col-md-4 mb-3">

                        <label class="form-label">
                            Shop Name *
                        </label>

                        <input
 type="text"
 class="form-control"
 v-model="customerOrder.shopName"
 :disabled="customerLocked">

                    </div>

                    <div class="col-md-4 mb-3">

                        <label class="form-label">
                            GST No
                        </label>

                       <input
 type="text"
 class="form-control"
 v-model="customerOrder.gstNo"
 :disabled="customerLocked">

                    </div>

                    <div class="col-md-4 mb-3">

                        <label class="form-label">
                            Pincode
                        </label>

                       <input
 type="text"
 class="form-control"
 v-model="customerOrder.pincode"
 :disabled="customerLocked">

                    </div>

                </div>

            </div>

        </div>

        <!-- Address -->

        <div class="card mb-3">

            <div class="card-header">
                Delivery Address
            </div>

            <div class="card-body">

                <div class="row">

                    <div class="col-md-3 mb-3">

                        <label class="form-label">
                            Taluka *
                        </label>

                      <input
 type="text"
 class="form-control"
 v-model="customerOrder.taluka"
 :disabled="customerLocked">

                    </div>

                    <div class="col-md-3 mb-3">

                        <label class="form-label">
                            Village *
                        </label>

                     <input
 type="text"
 class="form-control"
 v-model="customerOrder.village"
 :disabled="customerLocked">

                    </div>

                    <div class="col-md-3 mb-3">

                        <label class="form-label">
                            District *
                        </label>

                       <input
 type="text"
 class="form-control"
 v-model="customerOrder.district"
 :disabled="customerLocked">

                    </div>

                    <div class="col-md-3 mb-3">

                        <label class="form-label">
                            Full Address *
                        </label>

                       <input
 type="text"
 class="form-control"
 v-model="customerOrder.fullAddress"
 :disabled="customerLocked">

                    </div>

                </div>

                <div class="row">

                    <div class="col-md-12">

                        <label class="form-label">
                            Remarks
                        </label>

                        <textarea
                            rows="3"
                            class="form-control"
                            v-model="customerOrder.remarks">
                        </textarea>

                    </div>

                </div>

            </div>

        </div>

        <!-- Product Entry -->

        <div class="card mb-3">

            <div class="card-header">
                Add Products
            </div>

            <div class="card-body">

                <div class="row">

                    <div class="col-md-5">

                        <label class="form-label">
                            Product *
                        </label>

                        <select
                            class="form-select"
                            v-model="item.productId">

                            <option value="">
                                Select Product
                            </option>

                            <option
                                v-for="p in products"
                                :key="p.productId"
                                :value="p.productId">

                                {{ p.productName }}

                            </option>

                        </select>

                    </div>

                    <div class="col-md-3">

                        <label class="form-label">
                            Quantity *
                        </label>

                        <input
                            type="number"
                            min="1"
                            class="form-control"
                            v-model="item.quantity">

                    </div>

                    <div class="col-md-4">

                        <label class="form-label">
                            &nbsp;
                        </label>

                        <div>

                            <button
                                class="btn btn-primary me-2"
                                @click="addOrUpdateItem">

                                {{ editIndex >= 0 ? 'Update' : 'Add' }}

                            </button>

                            <button
                                v-if="editIndex >= 0"
                                class="btn btn-secondary"
                                @click="cancelEdit">

                                Cancel

                            </button>

                        </div>

                    </div>

                </div>

            </div>

        </div>

        <!-- Order Items -->

        <div class="card mb-3">

            <div class="card-header">
                Order Items
            </div>

            <div class="card-body">

                <table
                    class="table table-bordered table-striped">

                    <thead class="table-dark">

                        <tr>

                            <th width="80">
                                Sr No
                            </th>

                            <th>
                                Product
                            </th>

                            <th width="150">
                                Quantity
                            </th>

                            <th width="220">
                                Action
                            </th>

                        </tr>

                    </thead>

                    <tbody>

                        <tr
                            v-for="(i,index) in orderItems"
                            :key="index">

                            <td>
                                {{ index + 1 }}
                            </td>

                            <td>
                                {{ i.productName }}
                            </td>

                            <td>
                                {{ i.quantity }}
                            </td>

                            <td>

                                <button
                                    class="btn btn-warning btn-sm me-2"
                                    @click="editItem(index)">

                                    Edit

                                </button>

                                <button
                                    class="btn btn-danger btn-sm"
                                    @click="deleteItem(index)">

                                    Delete

                                </button>

                            </td>

                        </tr>

                        <tr
                            v-if="orderItems.length === 0">

                            <td
                                colspan="4"
                                class="text-center">

                                No Products Added

                            </td>

                        </tr>

                    </tbody>

                </table>

            </div>

        </div>

        <div class="text-end">

            <button
                class="btn btn-success"
                @click="submitOrder">

                Submit Order

            </button>

        </div>

        <!-- Existing Customer Modal -->

        <div
            v-if="showExistingCustomerModal"
            class="modal fade show d-block"
            style="background: rgba(0,0,0,0.5)">

            <div class="modal-dialog">

                <div class="modal-content">

                    <div class="modal-header">

                        <h5 class="modal-title">

                            Existing Customer Found

                        </h5>

                    </div>

                    <div class="modal-body">

                        <p>

                            <strong>
                                Customer :
                            </strong>

                            {{ existingUser?.name }}

                        </p>

                        <p>

                            <strong>
                                Mobile :
                            </strong>

                            {{ existingUser?.mobile }}

                        </p>

                        <p>

                            <strong>
                                Email :
                            </strong>

                            {{ existingUser?.email }}

                        </p>

                        <p>

                            <strong>
                                Existing Shop :
                            </strong>

                            {{ existingUser?.shopName }}

                        </p>

                        <hr>

                        <p>
                            Are you ordering for the same shop?
                        </p>

                    </div>

                    <div class="modal-footer">

                        <button
                            class="btn btn-success"
                            @click="selectSameShop">

                            Same Shop

                        </button>

                        <button
                            class="btn btn-warning"
                            @click="selectDifferentShop">

                            Different Shop

                        </button>

                    </div>

                </div>

            </div>

        </div>

    </div>
</template>
