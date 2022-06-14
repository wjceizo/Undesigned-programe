<template>
  <div class="login-container">
    <div class="login-bgp"></div>
    <div class="login-win">
      <div class="login-body">
        <div class="login-title">
          系统登录
        </div>
        <div class="login-form">
          <el-form :model="loginForm" :rules="rules" ref="loginForm">
            <el-form-item prop="userName">
              <el-input
                type="text"
                v-model="loginForm.userName"
                placeholder="请输入您的账号"
              ></el-input>
            </el-form-item>
            <el-form-item prop="passWord">
              <el-input
                type="password"
                v-model="loginForm.passWord"
                placeholder="请输入您的密码"
              ></el-input>
            </el-form-item>
            
            <el-form-item>
              <el-button
                style="margin-top: 15px; width: 100%;"
                @click="submitForm('loginForm')"
                type="primary"
                >用户登录</el-button
              >
            </el-form-item>
          </el-form>
          <div>{{loginForm.userName}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.login-container {
  background-color: #409eff;
  position: fixed;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  display: flex;
}
.login-bgp {
  background: url(https://bing.ioliu.cn/v1/rand?&t=json&w=1920&h=1080) no-repeat;

  height: 100%;
  width: 70%;
  background-position: center center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  background-color: #464646;
}
.login-win {
  position: relative;
  top: 50%;
  right: 0;
  transform: translate(0%, -50%);
  width: 50%;
  height: 100vh;
  padding: 15px;
  border-radius: 5px;
  background-color: white;
  background-size: cover;
  min-width: 350px;
}
.login-body {
  position: absolute;
  right: 25%;
  top: 20%;
  width: 50%;
}
.login-title {
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  color: #409eff;
  margin-bottom: 45px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<script>
import initMenu from "../utils/menu.js";
import { login } from "../api/index.js";
import axios from 'axios';

export default {
  data() {
    return {
      loginForm: {
        userName: "",
        passWord: "",
		    imageUrl: "",
      	imgData:"",
		    imgStr:"",
        count:1,
      },
      rules: {
        userName: [
          {
            required: true,
            message: "用户账号必须输入",
            trigger: "blur",
          },
        ],
        passWord: [
          {
            required: true,
            message: "用户密码必须输入",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {

      this.$refs[formName].validate((valid) => {
        if (valid) {
          login(this.loginForm).then((res) => {
            this.$store.commit("setToken", res.data);
            sessionStorage.setItem("token", res.data);
            initMenu(this.$router, this.$store);
            this.$router.push("/index");
          });
        } else {
          return false;
        }
      });
    },


	handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      console.log(res);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    getFile(file, fileList) {
      console.log(file);
      this.getBase64(file.raw).then((res) => {
        this.imgData = res
        console.log(this.imgData);
      });
    },
    getBase64(file) {
      return new Promise(function(resolve, reject) {
        let reader = new FileReader();
        let imgResult = "";
        reader.readAsDataURL(file);
        reader.onload = function() {
          imgResult = reader.result;
        };
        reader.onerror = function(error) {
          reject(error);
        };
        reader.onloadend = function() {
          resolve(imgResult);
          
        };
      });
    },
    submitUpload() {
      axios
        .post("https://jsonplaceholder.typicode.com/posts/", {
          data: this.imgData,
        })
        .then(function(response) {
          //具体操作
          console.log(response);
        })
        .catch(function(error) {
          console.log(error);
        });
    },
  },
};
</script>
