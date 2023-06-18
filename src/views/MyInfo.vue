<template>
    <div>
        <!-- <h1>页面1</h1> -->
        <!-- <el-radio-group v-model="size">
          <el-radio label="">默认</el-radio>
          <el-radio label="medium">中等</el-radio>
          <el-radio label="small">小型</el-radio>
          <el-radio label="mini">超小</el-radio>
        </el-radio-group> -->

        <el-descriptions class="margin-top" title="个人信息" :column="3" :size="size" border>
          <template slot="extra">
            <el-button type="primary" size="">修改信息</el-button>
          </template>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              管理员姓名
            </template>
            {{ name }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-mobile-phone"></i>
              手机号
            </template>
            {{ phone }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              居住地
            </template>
            {{ city }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-tickets"></i>
              备注
            </template>
            <el-tag size="small">{{ identity }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-office-building"></i>
              联系地址
            </template>
            {{ address }}
          </el-descriptions-item>
        </el-descriptions>
    </div>
</template>

<!-- <script>
export default {
    name: "MyInfo"
}
</script> -->

<script>
  export default {
      data () {
          return {
              size: '',
              name: '沈天边',
              phone: '1145143218',
              identity: '超级管理员',
              city: '北京市',
              address: '北京市海淀区北京交通大学鸽子笼钢架床',

          }
      }
  }
</script>


<style>

</style>
