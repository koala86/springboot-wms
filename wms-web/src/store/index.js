import Vue from 'vue'
import Vuex from 'vuex'
import router, { resetRouter } from '@/router'
Vue.use(Vuex)

function addNewRouter(menuList) {
    let routes = router.options.routes
    routes.forEach(routeItem=>{
        if (routeItem.path=='/Index') {
            console.log(menuList)
            menuList.forEach(menu=>{
                let childRoute = {
                    path: '/' + menu.menuclick,
                    name: menu.menuname,
                    meta: {
                        title: menu.menuname
                    },
                    component:()=>import('../components/' + menu.menucomponent)
                }
                routeItem.children.push(childRoute)
            })
        }
        // router.addRoute(routeItem)
    })
    resetRouter()
    router.addRoutes(routes)
}

export default new Vuex.Store({
    state: {
        menu: []
    },
    mutations: {
        setMenu(state,menuList) {
            state.menu = menuList
            addNewRouter(menuList)
        }
    },
    getters: {
        getMenu(state) {
            return state.menu
        }
    }
})