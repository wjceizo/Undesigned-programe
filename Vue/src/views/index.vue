<template>
  <div class="fater-body-show">
    <el-row :gutter="15">
      <el-col :span="24">
        <el-carousel
          height="55px"
          indicator-position="none"
          direction="vertical"
        >
          <el-carousel-item v-for="(item, index) in list_notice" :key="index">
            <el-card shadow="never">
              <div>
                <span style="color:#FFCC00" class="fa fa-bullhorn"></span>
                【{{ item.title }}】{{ item.cont }}
              </div>
            </el-card>
          </el-carousel-item>
        </el-carousel>
      </el-col>
    </el-row>
    <el-row :gutter="15">
      <el-col :span="10">
        <el-card shadow="never">
          <el-upload
            class="upload-demo"
            ref="upload"
            action="https://jsonplaceholder.typicode.com/posts/"
            accept=".jpg,.jpeg,.png,.JPG,.JPEG"
            :auto-upload="false"
            :on-change="getFile"
          >
            <div>
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
              slot="trigger"
              style="margin-left: 10px;"
              size="small"
              type="primary"
              @click="submitUpload">上传到服务器</el-button>
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
          <div>请上传图片进行人脸验证！</div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="15">
      <el-col :span="6">
        <el-card shadow="never">
          <div slot="header">
            <span class="fa fa-balance-scale"></span>
            青春寄语
          </div>
          <div class="fater-index-panle">
            <p class="fater-index-panle-info">
              世界是你们的，也是我们的，但归根结底是你们的。
              你们青年人是早晨八九点钟的太阳，希望寄托在你们身上
            </p>
            <p class="fater-index-panle-foot">---毛泽东</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="never">
          <div slot="header">
            <span class="fa fa-area-chart"></span>
            学生人数
          </div>
          <div class="fater-index-panle">
            <div class="fater-index-panle-num">{{ total_stu }}</div>
            <div class="fater-index-panle-desc">当前在校人数</div>
            <div class="fater-index-panle-unit">人</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="never">
          <div slot="header">
            <span class="fa fa-area-chart"></span>
            教师人数
          </div>
          <div class="fater-index-panle">
            <div class="fater-index-panle-num">{{ total_tea }}</div>
            <div class="fater-index-panle-desc">校园任课教师</div>
            <div class="fater-index-panle-unit">位</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="never">
          <div slot="header">
            <span class="fa fa-area-chart"></span>
            教室数目
          </div>
          <div class="fater-index-panle">
            <div class="fater-index-panle-num">{{ total_rooms }}</div>
            <div class="fater-index-panle-desc">当前校园各类教室</div>
            <div class="fater-index-panle-unit">间</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="15">
      <el-col :span="10">
        <el-card shadow="never">
          <div slot="header">
            <span class="fa fa-info-circle"></span>
            系统信息
          </div>
          <div>
            <el-descriptions :column="1" border>
              <el-descriptions-item>
                <template slot="label">系统名称 </template>
                智慧教室管理系统 V2.0.0
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">开发环境 </template>
                JDK8 + MySQL8.0 + Maven + IDEA
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">开发技术 </template>
                SpringBoot + MyBatisPlus + Vue + Element UI
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">运行环境 </template>
                JDK8 + MySQL8.0 + Tomcat
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">开发团队 </template>
                404NotFound
              </el-descriptions-item>
            </el-descriptions>
          </div>
        </el-card>
      </el-col>
      <el-col :span="14">
        <el-card shadow="never">
          <div slot="header">
            <span class="fa fa-flag"></span>
            校园活动
          </div>
          <div>
            <el-timeline>
              <el-timeline-item
                v-for="(item, index) in list_activity"
                :key="index"
                :timestamp="item.createTime"
              >
                【{{ item.title }}】{{ item.intro }}
              </el-timeline-item>
            </el-timeline>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { show } from "../api";
import axios from "axios";
import qs from "qs";

export default {
  data() {
    return {
      total_stu: 0,
      total_tea: 0,
      total_rooms: 0,
      list_activity: [],
      list_notice: [],
      imageUrl: "",
      imgData: "",
      src:
        "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
    };
  },
  mounted() {
    show().then((resp) => {
      this.total_stu = resp.data.total_stu;
      this.total_tea = resp.data.total_tea;
      this.total_rooms = resp.data.total_rooms;
      this.list_activity = resp.data.list_activity;
      this.list_notice = resp.data.list_notice;
    });
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
      console.log(file);
      this.getBase64(file.raw).then((res) => {
        this.imgData = res;
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
          this.console //具体操作
            .log(response);
        })
        .catch(function(error) {
          console.log(error);
        });
    },
  },
};
</script>

<style>
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
