<template>
    <el-dialog :append-to-body="true" :visible.sync="dialog" :title="isAdd ? '新增' : '编辑'" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">

                        <el-form-item label="password" prop="password">
                        <el-input v-model="form.password" style="width: 370px;"/>
                    </el-form-item>
                <el-form-item label="nickname" prop="nickname">
                        <el-input v-model="form.nickname" style="width: 370px;"/>
                    </el-form-item>
                <el-form-item label="mobile" prop="mobile">
                        <el-input v-model="form.mobile" style="width: 370px;"/>
                    </el-form-item>
                <el-form-item label="bio" prop="bio">
                        <el-input v-model="form.bio" style="width: 370px;"/>
                    </el-form-item>
                <el-form-item label="photo" prop="photo">
                        <el-input v-model="form.photo" style="width: 370px;"/>
                    </el-form-item>
                <el-form-item label="followers" prop="followers">
                        <el-input v-model="form.followers" style="width: 370px;"/>
                    </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="text" @click="cancel">取消</el-button>
            <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import {add, edit} from '@/api/users'
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
                    id: '',
                    password: '',
                    nickname: '',
                    mobile: '',
                    bio: '',
                    photo: '',
                    followers: ''
                },
                rules: {
                        id: [
                            {required: true, message: '不能为空', trigger: 'blur'}
                        ],
                        password: [
                            {required: true, message: '不能为空', trigger: 'blur'}
                        ],
                        nickname: [
                            {required: true, message: '不能为空', trigger: 'blur'}
                        ],
                        mobile: [
                            {required: true, message: '不能为空', trigger: 'blur'}
                        ],



                },
                options:[]
            }
        },
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
                id: '',
                password: '',
                nickname: '',
                mobile: '',
                bio: '',
                photo: '',
                followers: ''
            }
            }
        }
    }
</script>

<style scoped>

</style>
