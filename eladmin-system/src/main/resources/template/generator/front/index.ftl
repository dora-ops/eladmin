<#--noinspection ALL-->
<template>
  <div class="app-container">
    <eHeader :query="query"/>
    <!--表格渲染-->
    <el-table v-loading="loading" :data="data" size="small" style="width: 100%;">
      <#if columns??>
          <#list columns as column>
          <#if column.columnShow = 'true'>
              <#if column.columnType != 'Timestamp'>
      <el-table-column prop="${column.changeColumnName}" label="<#if column.columnComment != ''>${column.columnComment}<#else>${column.changeColumnName}</#if>"/>
              <#else>
      <el-table-column prop="${column.changeColumnName}" label="<#if column.columnComment != ''>${column.columnComment}<#else>${column.changeColumnName}</#if>">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.${column.changeColumnName}) }}</span>
        </template>
      </el-table-column>
              </#if>
          </#if>
          </#list>
      </#if>
      <el-table-column label="操作" width="150px" align="center">
        <template slot-scope="scope">
          <edit v-if="checkPermission(['ADMIN'])" :data="scope.row" :sup_this="sup_this"/>
          <el-popover
            v-if="checkPermission(['ADMIN'])"
            :ref="scope.row.id"
            placement="top"
            width="180">
            <p>确定删除本条数据吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.id].doClose()">取消</el-button>
              <el-button :loading="delLoading" type="primary" size="mini" @click="subDelete(scope.row._id)">确定</el-button>
            </div>
            <el-button slot="reference" type="danger" size="mini">删除</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <!--分页组件-->
    <el-pagination
      :total="total"
      style="margin-top: 8px;"
      layout="total, prev, pager, next, sizes"
      @size-change="sizeChange"
      @current-change="pageChange"/>
  </div>
</template>

<script>
import checkPermission from '@/utils/permission'
import initData from '@/mixins/initData'
import { del } from '@/api/${changeClassName}'
<#if hasTimestamp>
import { parseTime } from '@/utils/index'
</#if>
import eHeader from './module/header'
import edit from './module/edit'
import axios from "axios";
export default {
  components: { eHeader, edit },
  mixins: [initData],
  data() {
    return {
      delLoading: false, sup_this: this
    }
  },
  created() {
    this.$nextTick(() => {
      this.init()
    })
  },
  methods: {
  <#if hasTimestamp>
    parseTime,
  </#if>
    checkPermission,
    beforeInit() {
      this.url = '${tableName}'
      const sort = 'id,desc'
      this.params = { page: this.page, size: this.size, sort: sort }
      <#if hasQuery>
      const query = this.query
      const type = query.type
      const value = query.value
      if (type && value) { this.params[type] = value }
      </#if>
        this.params = { sql: "db.collection('${tableName}').get()", type: "query" };
      return true
    },
    subDelete(id) {
      this.delLoading = true
      var sql = "db.collection('${tableName}').doc('?').remove()";
      sql = sql.replace("?", id);
      this.params = { sql: sql, type: "delete" };
      axios.post("http://localhost:3000/api/base/vx", this.params).then(res => {
        this.delLoading = false
        this.init()
        this.$notify({
          title: '删除成功',
          type: 'success',
          duration: 2500
        })
      }).catch(err => {
        this.delLoading = false
        console.log(err.response.data.message)
      })
    },
      formatBoolean: function (row, column, cellValue) {
          var ret = ''
          if (cellValue) {
              ret = "true"
          } else {
              ret = "false"
          }
          if (cellValue instanceof Object){
              ret=JSON.stringify(cellValue)
          }
          return ret;
      },
  }
}
</script>

<style scoped>

</style>
