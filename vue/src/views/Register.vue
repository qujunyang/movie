<template>
  <div style="width: 100%; height: 100vh;background-color: darkslateblue; overflow: hidden">
    <div style="width: 400px; margin: 150px auto">
      <div style="color: #cccccc; font-size: 30px; text-align: center">欢迎注册</div>
      <el-form ref="form" :model="form" size="normal" :rules="rules">
        <el-form-item prop="userName">

          <el-input v-model="form.userName"></el-input>
        </el-form-item>
        <el-form-item prop="userPwd">
          <el-input v-model="form.userPwd" show-password></el-input>
        </el-form-item>
        <el-form-item prop="confirm">
        <el-input v-model="form.confirm" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%;" type="primary" @click="register">注 册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script >
import request from "@/utils/request";

export default {
  name: "Register",
  data(){
    return{
      form: {},
      rules:{
        userName:[
          {required: true,message:'请输入用户名',trigger:'blur'},
        ],
        userPwd:[
          {required: true,message:'请输入密码',trigger:'blur'},
        ],
        confirm:[
          {required: true,message:'请确认密码',trigger:'blur'},
        ]
      }
    }
  },
  methods: {
    register(){
      // console.log(this.form.password)
      // console.log(this.form.confirm)
      if(this.form.userPwd!=this.form.confirm){
        this.$message({
          type: "error",
          message: "两次密码输入不一致"
        })
        return
      }
      this.$refs['form'].validate((valid)=> {
        if (valid) {
          request.post("/api/user/register",this.form).then(res=>{
            if(res.code==='0'){
              this.$message({
                type: "success",
                message: "注册成功"
              })
              this.$router.push("/")
            }else {
              this.$message({
                type: "err",
                message: res.msg
              })
            }
          })
        }
      })



    }
  }
}

</script>

<style scoped>

</style>