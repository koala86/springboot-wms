<template>
    <div style="...">
        <h1>{{ 'ようこそ' + user.name }}</h1>
        <el-descriptions>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-s-custom"></i>
                    アカウント
                </template>
                {{ user.account }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-mobile-phone"></i>
                    携帯
                </template>
                {{ user.phone }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-location-outline"></i>
                    性別
                </template>
                <el-tag :type="user.sex === '1' ? 'primary' : 'danger'"
                        disable-transitions><i :class="user.sex==1?'el-icon-male':'el-icon-female'"></i>{{ user.sex==1?"男":"女" }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-tickets"></i>
                    ロール
                </template>
                <el-tag type="success"
                        disable-transitions>{{ user.roleId==0?"ルート管理員":(user.roleId==1?"管理員":"ユーザー") }}</el-tag>
            </el-descriptions-item>
        </el-descriptions>
        <DateUtils></DateUtils>
    </div>
</template>

<script>
    import DateUtils from "./DateUtils";
    export default {
        components: {DateUtils},
        data() {
            return {
                user:{}
            }
        },
        computed: {

        },
        methods: {
            init() {
                this.user = JSON.parse(sessionStorage.getItem('CurUser'))
            }
        }
    }
</script>