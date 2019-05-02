<template>
    <div class="app-wrapper" :class="classObj">
    </div>
</template>

<script>
	export default {
		name: "Layout",
		computed: {
			sidebar() {
				return this.$store.getters.sidebar
			},
			device() {
				return this.$store.getters.device
			},
			classObj() {
				return {
					hideSidebar: !this.sidebar.opened,
					withoutAnimation: this.sidebar.withoutAnimation,
					mobile: this.device === 'mobile'
				}
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
    @import "../../styles/mixin.scss";

    .app-wrapper {
        @include clearfix;
        position: relative;
        height: 100%;
        width: 100%;
    }
</style>