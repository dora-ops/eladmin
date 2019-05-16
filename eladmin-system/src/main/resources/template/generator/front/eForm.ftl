<template>
    <el-dialog :append-to-body="true" :visible.sync="dialog" :title="isAdd ? '新增' : '编辑'" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">

        <#assign sel=false/>
        <#if columns??>
            <#list columns as column>
                <#if column.changeColumnName != 'id'>
                <el-form-item label="<#if column.columnComment != ''>${column.columnComment}<#else>${column.changeColumnName}</#if>" prop="${column.changeColumnName}">
                    <#if column.columnType == 'Timestamp'>
                        <el-date-picker
                                v-model="form.${column.changeColumnName}"
                                type="date"
                                style="width: 370px;"
                                placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                    <#elseif column.columnName?ends_with("_id")>
                        <#assign sel=true/>
                        <#assign selName=column.columnName?substring(0,column.columnName?last_index_of("_id"))/>
                        <el-select v-model="form.${column.changeColumnName}" placeholder="请选择" style="width: 370px;">
                            <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <#else>
                        <el-input v-model="form.${column.changeColumnName}" style="width: 370px;"/>
                    </el-form-item>
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
    <#if sel>
    import { queryAll } from '@/api/selName'
    </#if>
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
                add(this.form).then(res => {
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
                edit(this.form).then(res => {
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
            }
        }
    }
</script>

<style scoped>

</style>
