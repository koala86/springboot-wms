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
      <el-button type="success" @click="add">add</el-button>
    </div>

    <el-table
      :data="tableData"
      :header-cell-style="{ background: '#f2f5fc', color: 'black' }"
    >
      <el-table-column prop="id" label="ID" width="60"> </el-table-column>
      <el-table-column prop="name" label="商品名" width="120">
      </el-table-column>
      <el-table-column
        prop="storage"
        label="倉庫名"
        width="120"
        :formatter="formatStorage"
      >
      </el-table-column>
      <el-table-column
        prop="goodstype"
        label="分類名"
        width="120"
        :formatter="formatGoodstype"
      >
      </el-table-column>
      <el-table-column prop="count" label="数量" width="120"> </el-table-column>
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
            <el-button slot="reference" size="small" type="danger"
              >削除</el-button
            >
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
      title="商品追加"
      :visible.sync="centerDialogVisible"
      width="30%"
      center
    >
      <el-form ref="form" :rules="rules" :model="form" label-width="100px">
        <el-form-item label="商品名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="倉庫名" prop="storage">
          <el-select
            v-model="form.storage"
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
        </el-form-item>
        <el-form-item label="分類名" prop="goodsType">
          <el-select
            v-model="form.goodsType"
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
        </el-form-item>
        <el-form-item label="商品数" prop="count">
          <el-input v-model="form.count"></el-input>
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
    let checkCount = (rule, value, callback) => {
      if (value > 9999) {
        callback(new Error("数量が多すぎる"));
      } else {
        callback();
      }
    };
    return {
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
      rules: {
        name: [
          {
            required: true,
            message: "商品名を入力してください",
            trigger: "blur",
          },
        ],
        storage: [
          {
            required: true,
            message: "倉庫を選択してください",
            trigger: "blur",
          },
        ],
        goodsType: [
          {
            required: true,
            message: "分類を選択してください",
            trigger: "blur",
          },
        ],
        count: [
          {
            required: true,
            message: "数量を入力してください",
            trigger: "blur",
          },
          {
            pattern: /^([1-9][0-9]*){1,4}$/,
            message: "数字を入力してください",
            trigger: "blur",
          },
          { validator: checkCount, trigger: "blur" },
        ],
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
    del(id) {
      console.log(id);
      this.$axios
        .get(this.$httpUrl + "/goods/delete?id=" + id, this.form)
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
        this.form.storage = row.storage;
        this.form.goodsType = row.goodsType;
        this.form.count = row.count;
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
        .post(this.$httpUrl + "/goods/save", this.form)
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
        .post(this.$httpUrl + "/goods/update", this.form)
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
        .post(this.$httpUrl + "/goods/pageCustom", {
          pageSize: this.pageSize,
          pageNum: this.pageNum,
          param: {
            name: this.name,
            goodstype: this.goodstype + "",
            storage: this.storage + "",
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
    this.loadStorage();
    this.loadGoodstype();
    this.loadPost();
  },
};
</script>
  
  <style>
</style>