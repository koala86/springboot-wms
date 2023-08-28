<template>

    <el-table :data="tableData">
        <el-table-column prop="date" label="日付" width="140">
        </el-table-column>
        <el-table-column prop="name" label="名前" width="120">
        </el-table-column>
        <el-table-column prop="address" label="住所"> </el-table-column>
    </el-table>

</template>

<script>
export default {
    data() {
        const item = {
            date: "2023-05-02",
            name: "桃太郎",
            address: "東京都千代田区 1-1",
        };
        return {
            tableData: Array(10).fill(item),
        };
    },
}
</script>

<style>
</style>