<template>
    <div>
      <div style="margin-bottom: 5px">
        <el-input
          v-model="name"
          placeholder="商品分類名を入力してください"
          suffix-icon="el-icon-search"
          style="width: 200px"
          @keyup.enter.native="loadPost"
        ></el-input>
  
        <el-button type="primary" style="margin-left: 5px" @click="loadPost"
          >search</el-button
        >
  
        <el-button type="success" @click="resetParam">reset</el-button>
        <el-button type="success" @click="add">add</el-button>
      </div>
  
      <el-table
        :data="tableData"
        :header-cell-style="{ background: '#f2f5fc', color: 'black' }"
      >
        <el-table-column prop="id" label="ID" width="60"> </el-table-column>
        <el-table-column prop="name" label="分類名" width="120"> </el-table-column>
        <el-table-column prop="remark" label="注釈"> </el-table-column>
  
        <el-table-column prop="operate" label="操作">
          <template slot-scope="scope">
            <el-button
              size="small"
              type="success"
              style="margin: 5px"
              @click="update(scope.row)"
              >編集</el-button
            >
            <el-popconfirm
              title="削除しますか？"
              @onConfirm="del(scope.row.id)"
              confirm-button-text="削除"
              cancel-button-text="いいえ"
            >
              <el-button slot="reference" size="small" type="danger">削除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
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
  
      <el-dialog
        title="商品分類追加"
        :visible.sync="centerDialogVisible"
        width="30%"
        center
      >
        <el-form ref="form" :rules="rules" :model="form" label-width="100px">
          <el-form-item label="分類名" prop="name">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="注釈" prop="remark">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="centerDialogVisible = false">戻る</el-button>
          <el-button type="primary" @click="save">追加</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        tableData: [],
        pageSize: 10,
        pageNum: 1,
        total: 0,
        name: "",
        centerDialogVisible: false,
        form: {
          id: "",
          name: "",
          remark: "",
        },
        rules: {
          name: [
            {
              required: true,
              message: "分類名を入力してください",
              trigger: "blur",
            },
          ],
        },
      };
    },
    methods: {
      resetForm() {
        this.$refs.form.resetFields();
      },
      del(id) {
        console.log(id)
        this.$axios
          .get(this.$httpUrl + "/goods-type/delete?id=" + id, this.form)
          .then((res) => res.data)
          .then((res) => {
            if (res.code == 200) {
              this.centerDialogVisible = false;
              this.$message({
                message: "削除成功",
                type: "success",
              });
            } else {
              this.$message({
                message: "削除失敗",
                type: "fail",
              });
            }
          });
        this.loadPost();
      },
      update(row) {
        this.centerDialogVisible = true;
        this.$nextTick(() => {
          this.form.id = row.id;
          this.form.name = row.name;
          this.form.remark = row.remark;
        });
      },
      add() {
        this.centerDialogVisible = true;
        this.$nextTick(() => {
          this.resetForm();
        });
      },
      doSave() {
        this.$axios
          .post(this.$httpUrl + "/goods-type/save", this.form)
          .then((res) => res.data)
          .then((res) => {
            if (res.code == 200) {
              this.centerDialogVisible = false;
              this.$message({
                message: "追加成功",
                type: "success",
              });
            } else {
              this.$message({
                message: "追加失敗",
                type: "fail",
              });
            }
          });
      },
      doUpdate() {
        this.$axios
          .post(this.$httpUrl + "/goods-type/update", this.form)
          .then((res) => res.data)
          .then((res) => {
            if (res.code == 200) {
              this.centerDialogVisible = false;
              this.$message({
                message: "追加成功",
                type: "success",
              });
            } else {
              this.$message({
                message: "追加失敗",
                type: "fail",
              });
            }
          });
      },
      save() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            if (this.form.id) {
              this.doUpdate();
            } else {
              this.doSave();
            }
            this.loadPost();
          } else {
            console.log("error submit!");
            return false;
          }
        });
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
          .post(this.$httpUrl + "/goods-type/pageCustom", {
            pageSize: this.pageSize,
            pageNum: this.pageNum,
            param: {
              name: this.name,
              sex: this.sex,
              roleId: '1',
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
        (this.name = ""), (this.sex = "");
      },
    },
    beforeMount() {
      this.loadPost();
    },
  };
  </script>
  
  <style>
  </style>