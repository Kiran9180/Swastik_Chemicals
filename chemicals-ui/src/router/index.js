import { createRouter, createWebHistory } from 'vue-router'

import Login from '../views/Login.vue'
import PlaceOrderView from '../views/PlaceOrderView.vue'

import Dashboard from '../views/Dashboard.vue'
import Product from '../views/Product.vue'
import ProductCostBreakup from '../views/ProductCostBreakup.vue'
import CustomerOrder from '../views/CustomerOrder.vue'
import PendingApproval from '../views/PendingApproval.vue'
import CostCalculation from '../views/CostCalculation.vue'
import Bill from '../views/Bill.vue'

const routes = [

  {
    path: '/login',
    component: Login
  },

  {
    path: '/place-order',
    component: PlaceOrderView
  },

  {
    path: '/',
    component: Dashboard,
    meta: {
      requiresAuth: true
    }
  },

  {
    path: '/product',
    component: Product,
    meta: {
      requiresAuth: true
    }
  },

  {
    path: '/product-cost-breakup',
    component: ProductCostBreakup,
    meta: {
      requiresAuth: true
    }
  },

  {
    path: '/customer-order',
    component: CustomerOrder,
    meta: {
      requiresAuth: true
    }
  },

  {
    path: '/pending-approval',
    component: PendingApproval,
    meta: {
      requiresAuth: true
    }
  },

  {
    path: '/cost-calculation',
    component: CostCalculation,
    meta: {
      requiresAuth: true
    }
  },

  {
    path: '/bill',
    component: Bill,
    meta: {
      requiresAuth: true
    }
  }

]

const router = createRouter({

  history: createWebHistory(),

  routes

})

router.beforeEach((to, from, next) => {

    const user =
        sessionStorage.getItem('user')

    const publicRoutes = [
        '/login',
        '/place-order'
    ]

    if (
        !publicRoutes.includes(to.path)
        &&
        !user
    ) {

        next('/login')

        return

    }

    next()

})

export default router