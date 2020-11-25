import Vue from 'vue'
import VueRouter from 'vue-router'
//引入login组件
import Login from "@/components/Login";
import Home from "@/components/Home";
import Welcome from "@/components/Welcome";
import UserList from "@/components/admin/UserList";
Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        redirect: "/login"
    },
    {
        path: "/login",
        component: Login
    },
    {
        path: "/home",
        component: Home,
        redirect: "/welcome",
        children:[
            {path: "/welcome", component: Welcome,},
            {path: "/user", component: UserList,}

        ]
    },
]

const router = new VueRouter({
    routes
})

//出现问题的时候使用
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalPush.call(this, location, onResolve, onReject)
    return originalPush.call(this, location).catch((err) => err)
}


//挂在路由导航守卫  全局前置守卫
router.beforeEach((to,from,next)=>{
    //to 将要访问
    //from 从哪访问
    //next 接着干的事 next(url) 跳转或者重定向到url上next()继续访问to路径
    if(to.path=='/login') return next();

    //获取user
    const userFlag=window.sessionStorage.getItem("user");//取出当前用户
    if(!userFlag) return next('/login');//无值返回登录页
    next();//符合要求 放行

})

export default router
