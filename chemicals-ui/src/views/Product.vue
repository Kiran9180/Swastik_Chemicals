<script setup>

import { ref , onMounted } from 'vue'
import ProductService from '../services/ProductService'

const products = ref([])

const product = ref({

    productId:null,

    productName:'',

    finalCost:0,

    active:true

})

function loadProducts(){

    ProductService
    .getAll()
    .then(response => {

        products.value =
        response.data

    })
}

function saveProduct(){

    ProductService
    .save(product.value)
    .then(() => {

        alert(
            "Saved Successfully"
        )

        clearForm()

        loadProducts()

    })

}

function editProduct(p){

    product.value =
    {...p}

}

function updateProduct(){

    ProductService
    .update(product.value)
    .then(() => {

        alert(
            "Updated Successfully"
        )

        clearForm()

        loadProducts()

    })

}

function deleteProduct(id){

    ProductService
    .delete(id)
    .then(() => {

        alert(
            "Deleted Successfully"
        )

        loadProducts()

    })

}

function clearForm(){

    product.value = {

        productId:null,

        productName:'',

        finalCost:0,

        active:true

    }

}

onMounted(() => {

    loadProducts()

})

</script>

<template>

<div>

<h2>
    Product Management
</h2>

<div class="card mt-3">

<div class="card-body">

<div class="mb-2">

<label>
Product Name
</label>

<input
class="form-control"
v-model="product.productName">

</div>

<div class="mb-2">

<label>
Final Cost
</label>

<input
type="number"
class="form-control"
v-model="product.finalCost">

</div>

<div class="mb-2">

<label>
Active
</label>

<select
class="form-control"
v-model="product.active">

<option :value="true">
Active
</option>

<option :value="false">
Inactive
</option>

</select>

</div>

<button
class="btn btn-primary me-2"
v-if="!product.productId"
@click="saveProduct">

Save

</button>

<button
class="btn btn-success"
v-if="product.productId"
@click="updateProduct">

Update

</button>

</div>

</div>

<hr>

<table
class="table table-bordered">

<thead>

<tr>

<th>ID</th>
<th>Name</th>
<th>Cost</th>
<th>Status</th>
<th>Action</th>

</tr>

</thead>

<tbody>

<tr
v-for="p in products"
:key="p.productId">

<td>
{{ p.productId }}
</td>

<td>
{{ p.productName }}
</td>

<td>
{{ p.finalCost }}
</td>

<td>

<span
v-if="p.active">

Active

</span>

<span
v-else>

Inactive

</span>

</td>

<td>

<button
class="btn btn-warning btn-sm me-2"
@click="editProduct(p)">

Edit

</button>

<button
class="btn btn-danger btn-sm"
@click="deleteProduct(p.productId)">

Delete

</button>

</td>

</tr>

</tbody>

</table>

</div>

</template>