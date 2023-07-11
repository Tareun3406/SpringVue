import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "@/views/LoginView.vue";
import JoinView from "@/views/JoinView.vue";
import MyPageView from "@/views/MyPageView.vue";
import store from "@/store";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/join',
    name: 'join',
    component: JoinView
  },
  {
    path: '/myPage',
    name: 'myPage',
    component: MyPageView,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

router.beforeEach((to, from, next) =>{
  if (to.name === "myPage") {
    store.dispatch("myPageForm/getMyUserInfo").then(()=> next());
  }else
    next();

})

export default router
