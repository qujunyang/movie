import {Location,document,Menu as IconMenu,Setting,} from '@element-plus/icons',
components: {Location,Document,Setting,IconMenu,}
import {document} from '@element-plus/icons-vue'
<template>

  <div>
    <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="el-menu-vertical-demo"
        default-active="like"
        router
        text-color="#fff"
        style="width: 200px; height: 100vh">
      <el-sub-menu index="/like">
        <template #title>
          我的
        </template>
<!--        <el-menu-item index="/fiction">小说</el-menu-item>-->
        <el-menu-item index="/login">登入</el-menu-item>
        <el-menu-item index="/register">注册</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="/film">热门电影</el-menu-item>

    </el-menu>

  </div>

</template>

<script>
export default {
  name: "Aside",

}

</script>

<style scoped>

</style>