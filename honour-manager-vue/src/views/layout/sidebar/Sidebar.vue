<template>
    <scroll-bar>
        <el-menu mode="vertical"
                 :show-timeout="200"
                 :default-active="$route.path"
                 :collapse="isCollapse"
                 background-color="#304156"
                 text-color="#bfcbd9"
                 active-text-color="#409EFF">
            <sidebar-item :routes="routes"></sidebar-item>
        </el-menu>
    </scroll-bar>
</template>

<script>
	import {mapGetters} from 'vuex'

	import ScrollBar from '@/components/ScrollBar'
	import SidebarItem from './SidebarItem'

	export default {
		name: "Sidebar",
		computed: {
			...mapGetters(['sidebar']),
			routes() {
				return this.$router.options.routes
			},
			isCollapse() {
				return !this.sidebar.opened
			}
		},
		components: {SidebarItem, ScrollBar}
	}
</script>