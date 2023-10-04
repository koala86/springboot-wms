<template>
    <div>
      <div style="margin-bottom: 5px">
        <el-input
          v-model="name"
          placeholder="商品名を入力してください"
          suffix-icon="el-icon-search"
          style="width: 200px"
          @keyup.enter.native="loadPost"
        ></el-input>
  
        <el-select
          v-model="storage"
          placeholder="倉庫選択"
          style="margin-left: 5px"
        >
          <el-option
            v-for="item in storageData"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
  
        <el-select
          v-model="goodstype"
          placeholder="分類選択"
          style="margin-left: 5px"
        >
          <el-option
            v-for="item in goodstypeData"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
  
        <el-button type="primary" style="margin-left: 5px" @click="loadPost"
          >search</el-button
        >
  
        <el-button type="success" @click="resetParam">reset</el-button>
      </div>
  
      <el-table
        :data="tableData"
        :header-cell-style="{ background: '#f2f5fc', color: 'black' }"
      >
        <el-table-column prop="id" label="ID" width="60"> </el-table-column>
        <el-table-column prop="goodsname" label="商品名" width="120">
        </el-table-column>
        <el-table-column
          prop="storagename"
          label="倉庫名"
          width="120"
        >
        </el-table-column>
        <el-table-column
          prop="goodstypename"
          label="分類名"
          width="120"
        >
        </el-table-column>
        <el-table-column prop="adminname" label="承認者" width="120"> </el-table-column>
        <el-table-column prop="username" label="操作人" width="120"> </el-table-column>
        <el-table-column prop="count" label="数量" width="120"> </el-table-column>
        <el-table-column prop="createtime" label="操作時間" width="180"> </el-table-column>
        <el-table-column prop="remark" label="注釈"> </el-table-column>
      </el-table>
  
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </template>
    
    <script>
  export default {
    data() {
      return {
        user: JSON.parse(sessionStorage.getItem('CurUser')),
        storageData: [],
        goodstypeData: [],
        tableData: [],
        pageSize: 10,
        pageNum: 1,
        total: 0,
        name: "",
        storage: "",
        goodstype: "",
        centerDialogVisible: false,
        form: {
          id: "",
          name: "",
          storage: "",
          goodsType: "",
          count: "",
          remark: "",
        },
      };
    },
    methods: {
      formatStorage(row) {
        let temp = this.storageData.find((item) => {
          return item.id == row.storage;
        });
        return temp && temp.name;
      },
      formatGoodstype(row) {
        let temp = this.goodstypeData.find((item) => {
          return item.id == row.goodsType;
        });
        return temp && temp.name;
      },
      resetForm() {
        this.$refs.form.resetFields();
      },
      handleSizeChange(val) {
        this.pageNum = 1;
        this.pageSize = val;
        this.loadPost();
      },
      handleCurrentChange(val) {
        this.pageNum = val;
        this.loadPost();
      },
      loadPost() {
        this.$axios
          .post(this.$httpUrl + "/record/pageCustom", {
            pageSize: this.pageSize,
            pageNum: this.pageNum,
            param: {
              name: this.name,
              goodstype: this.goodstype + "",
              storage: this.storage + "",
              roleId: this.user.roleId + "",
              userId: this.user.id + ""
            },
          })
          .then((res) => res.data)
          .then((res) => {
            if (res.code == 200) {
              this.tableData = res.data;
              this.total = res.total;
            } else {
              alert("データ取得失敗");
            }
          });
      },
      resetParam() {
        this.name = "";
        this.storage = "";
        this.goodstype = "";
      },
      loadStorage() {
      this.$axios
        .get(this.$httpUrl + "/storage/list")
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            this.storageData = res.data;
          } else {
            alert("データ取得失敗");
          }
        });
    },
    loadGoodstype() {
      this.$axios
        .get(this.$httpUrl + "/goods-type/list")
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            this.goodstypeData = res.data;
          } else {
            alert("データ取得失敗");
          }
        });
    },
    },
    beforeMount() {
      this.loadGoodstype();
      this.loadStorage();
      this.loadPost();
    },
  };
  </script>
    
    <style>
  </style>