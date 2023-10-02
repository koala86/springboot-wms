<template>
    <div class="loginBody">
        <div class="loginDiv">
            <div class="login-content">
                <h1 class="login-title">ユーザーログイン</h1>
                <el-form :model="loginForm" label-width="100px"
                         :rules="rules" ref="loginForm">
                    <el-form-item label="アカウント" prop="account">
                        <el-input style="..." type="text" v-model="loginForm.account"
                                  autocomplete="off" size="small"></el-input>
                    </el-form-item>
                    <el-form-item label="パスワード" prop="password">
                        <el-input style="..." type="password" v-model="loginForm.password"
                                  show-password autocomplete="off" size="small" @keyup.enter.native="confirm"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="confirm" :disabled="confirm_disabled">確定</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                confirm_disabled: false,
                loginForm: {
                    account: '',
                    password: ''
                },
                rules: {
                    account: [
                        { required: true, message: 'アカウントを入力してください', trigger: 'blur'}
                    ],
                    password: [
                        { required: true, message: 'パスワードを入力してください', trigger: 'blur'}
                    ],
                }
            }
        },
        methods: {
            confirm() {
                this.confirm_disabled=true;
                this.$refs.loginForm.validate((valid) => {
                    if(valid) {
                        this.$axios.post(this.$httpUrl + '/user/login',this.loginForm).then(res=>res.data).then(res=>{
                            if(res.code==200) {
                                console.log()
                                sessionStorage.setItem("CurUser",JSON.stringify(res.data.user))
                                // console.log(res.data.menu)
                                this.$store.commit("setMenu",res.data.menu);
                                this.$router.replace('/Index');
                            } else {
                                this.confirm_disabled=false;
                                alert('アカウントまたはパスワードが間違い');
                                return false;
                            }
                        });
                    } else {
                        this.confirm_disabled=false;
                        console.log('ログインできない');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .loginBody {
        position: absolute;
        width: 100%;
        height: 100%;
        background-color: #B3C0D1;
    }
    .loginDiv {
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -200px;
        margin-left: -250px;
        width: 450px;
        height: 330px;
        background: #fff;
        border-radius: 5%;
    }
    .login-title {
        margin: 20px 0;
        text-align: center;
    }
    .login-content {
        width: 400px;
    }
</style>