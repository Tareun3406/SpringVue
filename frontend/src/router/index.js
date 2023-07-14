import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "@/views/LoginView.vue";
import JoinView from "@/views/JoinView.vue";
import MyPageView from "@/views/MyPageView.vue";
import store from "@/store";
import BoardListView from "@/views/BoardListView.vue";
import BoardPostingView from "@/views/BoardPostingView.vue";
import BoardContentView from "@/views/BoardContentView.vue";

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
  },
  {
    path: '/boardList',
    name: 'boardList',
    component: BoardListView
  },
  {
    path: '/boardPosting',
    name: 'boardPosting',
    component: BoardPostingView
  },
  {
    path: '/boardContent/:no',
    name: 'boardContent',
    component: BoardContentView
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

router.beforeEach((to, from, next) =>{

  switch (to.name){
    case "myPage" :
      store.dispatch("myPageForm/getMyUserInfo").then(() => next());
      break;
    case "boardList":
      store.dispatch("boardList/getBoardList").then(() => next());
      break;
    case "boardContent":
      store.dispatch("boardContent/getContents", to.params.no)
          .then(() => next())
          .catch((error) => {
            if (error.response.status === 404) {
              router.push("/boardList");
            }});
      break;
    default:
      next();
  }
})

export default router
