<template>
    <el-dialog :append-to-body="true" :visible.sync="dialog" :title="isAdd ? '新增' : '编辑'" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">

        <#assign sel=false/>
        <#if columns??>
            <#list columns as column>
                <#if column.changeColumnName != '_id'>
                <el-form-item label="<#if column.columnComment != ''>${column.columnComment}<#else>${column.changeColumnName}</#if>" prop="${column.changeColumnName}">
                    <#if column.columnType == 'Timestamp'>
                        <el-date-picker
                                v-model="form.${column.changeColumnName}"
                                type="date"
                                style="width: 370px;"
                                placeholder="选择日期">
                        </el-date-picker>
                    <#else >
                        <el-input v-model="form.${column.changeColumnName}" style="width: 370px;"/>
                    </#if>
                    </el-form-item>
                </#if>
            </#list>
        </#if>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="text" @click="cancel">取消</el-button>
            <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import {add, edit} from '@/api/${changeClassName}'
    import axios from "axios";
    export default {
        props: {
            isAdd: {
                type: Boolean,
                required: true
            },
            sup_this: {
                type: Object,
                default: null
            }
        },
        data() {
            return {
                loading: false, dialog: false,
                form: {
                <#if columns??>
                    <#list columns as column>
                    ${column.changeColumnName}: ''<#if column_has_next>,</#if>
                    </#list>
                </#if>
                },
                rules: {
                <#if columns??>
                    <#list columns as column>
                        <#if column.isNullable == 'NO'>
                        ${column.changeColumnName}: [
                            {required: true, message: '${column.columnComment}不能为空', trigger: 'blur'}
                        ]<#if column_has_next>,</#if></#if>
                    </#list>
                </#if>
                },
                options:[]
            }
        },
    <#if sel>
        created() {
    queryAll().then(res => {
        this.options=res.content
    })
        },
    </#if>
        methods: {
            cancel() {
                this.resetForm()
            },
            doSubmit() {
                this.loading = true
                if (this.isAdd) {
                    this.doAdd()
                } else this.doEdit()
            },
            doAdd() {
                var sql = "db.collection('${tableName}').add({data: [?]})";
                delete this.form._id
                this.dealForm(this.form)
                this.form._openid='oI4uu4ovj150Zi7ZSD-039RmIOcE'
                sql = sql.replace("?", JSON.stringify(this.form));
                this.params = { sql: sql, type: "add" };
                axios.post("http://localhost:3000/api/base/vx", this.params).then(res => {
                    this.resetForm()
                this.$notify({
                    title: '添加成功',
                    type: 'success',
                    duration: 2500
                })
                this.loading = false
                this.$parent.$parent.init()
            }).
                catch(err => {
                    this.loading = false
                console.log(err.response.data.message)
            })
            },
            doEdit() {
                var sql = "db.collection('${tableName}').doc('?').update({data: ?})";
                this.form._openid='oI4uu4ovj150Zi7ZSD-039RmIOcE'
                this.dealForm(this.form)
                sql = sql
                        .replace("?", this.form._id)
                        .replace("?", JSON.stringify(this.form));
                this.params = { sql: sql, type: "update" };
                axios.post("http://localhost:3000/api/base/vx", this.params).then(res => {
                    this.resetForm()
                this.$notify({
                    title: '修改成功',
                    type: 'success',
                    duration: 2500
                })
                this.loading = false
                this.sup_this.init()
            }).
                catch(err => {
                    this.loading = false
                console.log(err.response.data.message)
            })
            },
            resetForm() {
                this.dialog = false
                this.$refs['form'].resetFields()
                this.form = {
            <#if columns??>
                <#list columns as column>
                ${column.changeColumnName}: ''<#if column_has_next>,</#if>
                </#list>
            </#if>
            }
            },
            dealForm(form){

            }

    }
    }
</script>

<style scoped>

</style>
