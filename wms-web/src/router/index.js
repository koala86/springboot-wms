import VueRouter from "vue-router";

const routes = [
    {
        path: '/',
        name: 'login',
        component: ()=> import('../components/Login')
    },
    {
        path: '/Index',
        name: 'index',
        component: ()=> import('../components/Index'),
        children: [
            {
                path: '/Home',
                name: 'home',
                meta: {
                    title: 'ホーム'
                },
                component: ()=>import('../components/Home')
            },
            {
                path: '/Admin',
                name: 'admin',
                meta: {
                    title: '管理員管理'
                },
                component: ()=>import('../components/admin/AdminManage.vue')
            },
            {
                path: '/User',
                name: 'user',
                meta: {
                    title: 'ユーザー管理'
                },
                component: ()=>import('../components/user/UserManage.vue')
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
}

export default router;