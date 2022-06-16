<template>
  <div id="app">

        <el-card shadow="never">
          <el-upload
            action=""
            class="upload-demo"
            ref="upload"
            accept=".jpg,.jpeg,.png,.JPG,.JPEG"
            :auto-upload="false"
            :on-change="getFile"
          >
            <div>
              <h1 v-if="countVisible">当前上课人数：{{stuCount}}</h1>
              <div class="demo-image__placeholder">
                <div class="block">
                  <!-- <el-image :src="'data:image/png;base64,'+imgurl"> -->
                  <el-image :src="imgData" class="image-slot">
                    <div slot="placeholder" class="image-slot">
                      加载中<span class="dot">...</span>
                    </div>
                  </el-image>
                </div>
              </div>
            </div>
            <!-- <el-button  size="small" type="primary">选取文件</el-button> -->
            <el-button
              style="margin-left: 10px;"
              size="small"
              type="primary"
              >上传到服务器</el-button
            >
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过500kb
            </div>
          </el-upload>

          <el-dialog>
            <img width="100%" :src="imgData" alt="" />
          </el-dialog>
          <div slot="header">
            <span class="fa fa-balance-scale"></span>
            人脸识别
          </div>
          <div>请上传图片进行学生人数识别！</div>
        </el-card>

  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      countVisible:false,
      stuCount:0,
      imageUrl: "",
      imgData: require('./assets/img/add.png'),
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      console.log(res);
    },
    handlePreview() {},
    handleRemove() {},
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
      this.imgData = "",
      this.imageUrl="",
      this.countVisible = false,
      console.log(file);
      this.getBase64(file.raw).then(res => {
        this.imgData = res;
        console.log(this.imgData);
      }).then(res=>{
        this.submitUpload()
      }

      );
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
        .post("http://127.0.0.1:5000/posts", {
          data: this.imgData
        })
        .then(response=> {
          console.log(this.imgData);
          this.stuCount = response.data.students;
          this.countVisible = true;
          this.imgData="data:image/jpeg;base64,"+response.data.img;
        
        })
        .catch(function(error) {
          console.log(error);
        });
    }
  },
  name: "App"
};
</script>

<style>
#app {
  align-items: center;
  justify-content: center;
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.upload-demo {
  display: grid;
  text-align: center;
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
