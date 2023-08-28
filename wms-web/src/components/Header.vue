<template>
    <div style="display: flex; line-height: 60px">
      <div style="margin-top: 8px">
        <i
          :class="icon"
          style="font-size: 20px; cursor: pointer"
          @click="collapse"
        ></i>
      </div>
  
      <div style="flex: 1; text-align: center; font-size: 34px">
        <span>ようこそ、倉庫管理システム</span>
      </div>
  
      <el-dropdown style="margin-top: 8px">
        <span>桃太郎</span
        ><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
  
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="toUser">プロフィール</el-dropdown-item>
  
          <el-dropdown-item @click.native="logout">ログアウト</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </template>
  
  <script>
      export default {
      methods: {
          toUser() {
          console.log("toUser");
          },
  
          logout() {
          console.log("logout");
          },
  
          collapse() {
          this.$emit("doCollapse");
          },
      },
  
      props: {
          icon: String,
      },
      };
  </script>
  
  <style>
  </style>