<template>
  <div>
    <div style="margin-bottom: 5px">
      <el-input
        v-model="name"
        placeholder="名前を入力してください"
        suffix-icon="el-icon-search"
        style="width: 200px"
        @keyup.enter.native="loadPost"
      ></el-input>

      <el-select
        v-model="sex"
        filterable
        placeholder="性別"
        style="margin-left: 5px"
      >
        <el-option
          v-for="item in sexs"
          :key="item.value"
          :label="item.label"
          :value="item.value"
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

      <el-table-column prop="account" label="アカウント" width="150">
      </el-table-column>

      <el-table-column prop="name" label="名前" width="120"> </el-table-column>

      <el-table-column prop="age" label="年齢" width="100"> </el-table-column>

      <el-table-column prop="sex" label="性別" width="100">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.sex === 1 ? 'primary' : 'success'"
            disable-transitions
            >{{ scope.row.sex === 1 ? "男" : "女" }}</el-tag
          >
        </template>
      </el-table-column>

      <el-table-column prop="roleId" label="ロール" width="150">
        <template slot-scope="scope">
          <el-tag
            :type="
              scope.row.roleId === 0
                ? 'danger'
                : scope.row.roleId === 1
                ? 'primary'
                : 'success'
            "
            disable-transitions
            >{{
              scope.row.roleId === 0
                ? "ルート管理員"
                : scope.row.roleId === 1
                ? "管理員"
                : "ユーザー"
            }}</el-tag
          >
        </template>
      </el-table-column>

      <el-table-column prop="phone" label="電話" width="180"> </el-table-column>

      <el-table-column prop="operate" label="操作">
        <el-button size="small" type="success">編集</el-button>

        <el-button size="small" type="danger">削除</el-button>
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
      title="ユーザー追加"
      :visible.sync="centerDialogVisible"
      width="30%"
      center
    >
      <el-form ref="form" :rules="rules" :model="form" label-width="80px">
        <el-form-item label="アカウント" prop="account">
          <el-input v-model="form.account"></el-input>
        </el-form-item>
        <el-form-item label="名前" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="パスワード" prop="password">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="年齢" prop="age">
          <el-input v-model="form.age" prop="age"></el-input>
        </el-form-item>
        <el-form-item label="性別">
          <el-radio-group v-model="form.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="2">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="携帯" prop="phone">
          <el-input v-model="form.phone"></el-input>
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
    let checkAge = (rule, value, callback) => {
      if (value > 150) {
        callback(new Error('年齢は多すぎる'));
      } else {
        callback();
      }
    };
    return {
      tableData: [],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      name: "",
      sex: "",
      sexs: [
        {
          value: "1",
          label: "男",
        },
        {
          value: "2",
          label: "女",
        },
      ],
      centerDialogVisible: false,
      form: {
        account: "",
        name: "",
        password: "",
        age: "",
        sex: 2,
        phone: "",
      },
      rules: {
          account: [
            { required: true, message: 'アカウントを入力してください', trigger: 'blur' },
            { min: 3, max: 8, message: '3~8文字', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '名前を入力してください', trigger: 'blur' },
            { min: 3, max: 8, message: '3~8文字', trigger: 'blur' }
          ],
          password: [
            { required: true, message: 'パスワードを入力してください', trigger: 'blur' },
            { min: 3, max: 8, message: '3~8文字または数字', trigger: 'blur' }
          ],
          age: [
            { required: true, message: '年齢を入力してください', trigger: 'blur' },
            { min: 1, max: 3, message: '正しく入力してください', trigger: 'blur'},
            { pattern: /^([1-9][0-9]*){1,3}$/, message: '年齢を正しく入力してください', trigger: 'blur'},
            { validator: checkAge, trigger: 'blur'}
          ],
          phone: [
            { required: true, message: '携帯番号を入力してください', trigger: 'blur' },
            { pattern: /^0[7-9]0\d{8}$/, message: '携帯番号を正しく入力してください', trigger: 'blur'},
          ],
        }
    };
  },
  methods: {
    add() {
      this.centerDialogVisible = true;
    },
    save() {
      this.$axios
        .post(this.$httpUrl + "/user/save", this.form)
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
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
    handleSizeChange(val) {
      this.pageNum = 1;
      this.pageSize = val;
      this.loadPost();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.loadPost();
    },
    loadGet() {
      this.$axios.get(this.$httpUrl + "user/list").then((res) => {
        console.log(res);
      });
    },
    loadPost() {
      this.$axios
        .post(this.$httpUrl + "/user/pageCustom", {
          pageSize: this.pageSize,
          pageNum: this.pageNum,
          param: {
            name: this.name,
            sex: this.sex,
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