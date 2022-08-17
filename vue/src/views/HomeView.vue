<template>
  <div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
<!--      <el-button type="primary">导入</el-button>-->
<!--      <el-button type="primary">导出</el-button>-->
    </div>
    <!--      搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search"  placeholder="请输入关键字" style="width: 320px" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData "  stripe style="width: 100%">
<!--      movie_id  movie_name  movie_kind  movie_link  movie_moment-->
      <el-table-column prop="movieId" label="序号" sortable="true"/>
      <el-table-column prop="movieName" label="名称"  />
      <el-table-column prop="movieKind" label="类型" />
<!--      <el-table-column prop="movieLink" label="链接" />-->
      <el-table-column prop="movieMoment" label="电影评分" />

      <el-table-column prop="do"  label="操作" width="450px">

        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="small" @click="handleCopy(scope.row)">获取链接</el-button>

          <el-popconfirm title="确定删除吗？"  @confirm="handleDelete(scope.row.movieId)">
            <template #reference>
              <el-button size="small" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>

      </el-table-column>

    </el-table>
    <div style="margin: 10px 0px">
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


        <el-dialog v-model="dialogVisible" title="提示" width="30%">
            <el-form :inline="true" :model="form" class="demo-form-inline" label-width="120px">
              <el-form-item label="电影名称">
                <el-input v-model="form.movieName" placeholder="添加的电影名称" />
              </el-form-item>
              <el-form-item label="电影类型">
                <el-select v-model="form.movieKind" placeholder="选择类型">
                  <el-option label="爱情" value="爱情" />
                  <el-option label="科幻" value="科幻" />
                </el-select>
              </el-form-item>
              <el-form-item label="电影链接">
                <el-input v-model="form.movieLink" placeholder="添加的电影链接" />
              </el-form-item>
              <el-form-item label="电影感想">
                <el-input v-model="form.movieMoment" placeholder="添加的电影感想" />
              </el-form-item>
            </el-form>

          <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="save">确认</el-button>
              </span>
          </template>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'HomeView',
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
      tableData: []
    }
  },
  created(){
    this.load()
  },
  methods: {

    load(){

      request.get("/api/goods/search",{
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
    save(){
     if(this.form.movieId){ //更新
       request.put("/api/goods/update",this.form).then(res =>{
         console.log(res)
        if(res.code==='0'){

          this.$message({
            type: "success",
            message: "更新"
          })
        }else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
         this.form={}
          this.load()
          this.dialogVisible=false
       })
     }else {  //新增
       request.post("/api/goods/save",this.form).then(res =>{
         console.log(res)
         this.$message({
           type: "success",
           message: "新增成功"
         })
       })
       this.form={}
       this.load()
       this.dialogVisible=false
     }
    },
    handleEdit(row){
        this.form=JSON.parse(JSON.stringify(row))
        this.dialogVisible=true;
    },
    handleDelete(movieId){
      console.log(movieId)
      request.delete("/api/goods/delete/"+movieId).then(res=>{
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
    handleCopy(row) {
      console.log('row', JSON.parse(JSON.stringify(row)))
      this.copyData = row.movieLink
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
