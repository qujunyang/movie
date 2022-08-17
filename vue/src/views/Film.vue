<template >
<!--  style=" height: 90vh;"-->
  <div >
    <!--      搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search"  placeholder="请输入关键字" style="width: 320px" clearable size="normal"></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load" size="normal">查询</el-button>
    </div>
    <el-table :data="tableData "  stripe style="width: 100%">
<!--      film_id`, `film_name`, `film_location`, `film_kind`, `film_language`, `film_info`, `film_link`, `film_img-->
      <el-table-column prop="filmId" label="序号" sortable="true" width="60px"/>
      <el-table-column prop="filmName" label="名称"  />
      <el-table-column prop="filmLocation" label="产地"  />
      <el-table-column prop="filmKind" label="类型"  />
      <el-table-column prop="filmLanguage" label="语言" />
<!--      <el-table-column prop="filmInfo" label="名称"  />-->
<!--      <el-table-column prop="filmImg" label="封面" />-->
<!--      <el-table-column prop="filmLink" label="链接" @click="handleCopy"/>-->
      <el-table-column prop="do"  label="操作" width="280px">

        <template #default="scope">
<!--          <el-button size="small" @click="handleEdit(scope.row)">查看</el-button>-->
          <el-button size="small" @click="handleCopy(scope.row)">获取链接</el-button>

          <el-popconfirm title="确定删除吗？"  @confirm="handleDelete(scope.row.filmId)">
            <template #reference>
              <el-button size="small" type="danger">不感兴趣</el-button>
            </template>
          </el-popconfirm>
        </template>

      </el-table-column>

    </el-table>

    <div>


    </div>

  </div>
  <div style="margin: 10px 0px;" >
    <div class="demo-pagination-block">
      <div class="demonstration"></div>
      <el-pagination
          v-model:currentPage="currentPage3"
          v-model:page-size="pageSize3"
          v-model:total="total"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />

    </div>
  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'Film',
  components: {

  },
  data(){
    return{
      form:{},
      search: '',
      currentPage3: 1,
      pageSize3: 10,
      total: 20,
      dialogVisible: false,
      tableData: [],
      link1:'',
      // with-header: false
    }
  },
  created(){
    this.load()
  },
  methods: {

    load(){

      request.get("/api/film/search",{
        params: {
          pageNum: this.currentPage3,
          pageSize: this.pageSize3,
          search: this.search,
        }
      }).then(res =>{
        console.log(res)
        this.tableData=res.data.records
        this.total=res.data.total
      })
    },
    add(){
      this.dialogVisible=true
      this.form={}
    },

    handleEdit(row){
      this.drawer=true
      // this.link1=filmLink
      // this.location.href=this.link
      // this.location.link = this.link1
      // console.log(filmLink)
      // self.location.href=this.link1
      console.log(row.filmImg)
      console.log(row.filmInfo)
    },
    handleDelete(filmId){
      console.log(filmId)
      request.delete("/api/film/delete/"+filmId).then(res=>{
        if(res.code==='0'){

          this.$message({
            type: "success",
            message: "删除成功"
          })
        }else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()

      })
    },
    handleSizeChange(){
      this.load()
    },
    handleCurrentChange(){
      this.load()
    },
    // 复制
    handleCopy(row) {
      console.log('row', JSON.parse(JSON.stringify(row)))
      this.copyData = row.filmLink
      this.copy(this.copyData)
    },
    copy(data) {
      const url = data;
      const oInput = document.createElement('input');
      oInput.value = url;
      document.body.appendChild(oInput);
      oInput.select(); // 选择对象;
      console.log(oInput.value)
      document.execCommand('Copy'); // 执行浏览器复制命令
      this.$message({
        message: '复制成功',
        type: 'success'
      });
      oInput.remove()
    },
  }
}
</script>
