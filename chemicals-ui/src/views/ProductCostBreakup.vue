<script setup>

import { ref , onMounted } from 'vue'

import ProductService
from '../services/ProductService'

import ProductCostBreakupService
from '../services/ProductCostBreakupService'

const products = ref([])

const costBreakups = ref([])

const breakup = ref({

    breakupId:null,

    productId:'',

    costName:'',

    amount:0

})

function loadProducts(){

    ProductService
    .getAll()
    .then(response => {

        products.value =
        response.data

    })

}

function loadBreakups(){

    if(!breakup.value.productId)
        return

    ProductCostBreakupService
    .getByProductId(
        breakup.value.productId
    )
    .then(response => {

        costBreakups.value =
        response.data

    })

}

function saveBreakup(){

    ProductCostBreakupService
    .save(breakup.value)
    .then(() => {

        alert(
            "Saved Successfully"
        )

        loadBreakups()

        clearForm()

    })

}

function editBreakup(item){

    breakup.value =
    {...item}

}

function updateBreakup(){

    ProductCostBreakupService
    .update(breakup.value)
    .then(() => {

        alert(
            "Updated Successfully"
        )

        loadBreakups()

        clearForm()

    })

}

function deleteBreakup(id){

    ProductCostBreakupService
    .delete(id)
    .then(() => {

        alert(
            "Deleted Successfully"
        )

        loadBreakups()

    })

}

function clearForm(){

    breakup.value = {

        breakupId:null,

        productId:
        breakup.value.productId,

        costName:'',

        amount:0

    }

}

onMounted(() => {

    loadProducts()

})

</script>

<template>

<div>

<h2>
Product Cost Breakup
</h2>

<div class="card">

<div class="card-body">

<div class="mb-3">

<label>
Product
</label>

<select
class="form-control"
v-model="breakup.productId"
@change="loadBreakups">

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

<div class="mb-3">

<label>
Cost Name
</label>

<input
class="form-control"
v-model="breakup.costName">

</div>

<div class="mb-3">

<label>
Amount
</label>

<input
type="number"
class="form-control"
v-model="breakup.amount">

</div>

<button
v-if="!breakup.breakupId"
class="btn btn-primary"
@click="saveBreakup">

Save

</button>

<button
v-if="breakup.breakupId"
class="btn btn-success"
@click="updateBreakup">

Update

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
Action
</th>

</tr>

</thead>

<tbody>

<tr
v-for="item in costBreakups"
:key="item.breakupId">

<td>
{{ item.costName }}
</td>

<td>
{{ item.amount }}
</td>

<td>

<button
class="btn btn-warning btn-sm me-2"
@click="editBreakup(item)">

Edit

</button>

<button
class="btn btn-danger btn-sm"
@click="deleteBreakup(item.breakupId)">

Delete

</button>

</td>

</tr>

</tbody>

</table>

</div>

</template>