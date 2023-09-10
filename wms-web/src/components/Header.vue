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
      <span>{{ user.name }}</span
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

  data() {
    return {
      user: JSON.parse(sessionStorage.getItem('CurUser'))
    }
  },

  methods: {
    toUser() {
      console.log("toUser");
      this.$router.push("/Home")
    },

    logout() {
      console.log("logout");
      this.$confirm('ログアウトしますか？','ログアウト確認', {
        confirmButtonText: 'はい',
        cancelButtonText: 'いいえ',
        type: 'warning',
        center: true,
      })
      .then(() => {
        this.$message({
          type: 'success',
          message: 'ログアウトしました'
        })
        this.$router.push("/")
        sessionStorage.clear()
      })
      .catch(() => {
        this.$message({
          type: 'info',
          message: '取り消しました'
        })
      })
    },

    collapse() {
      this.$emit("doCollapse");
    },
  },
  created() {
    this.$router.push("/Home")
  },


  props: {
    icon: String,
  },
};
</script>
  
  <style>
</style>