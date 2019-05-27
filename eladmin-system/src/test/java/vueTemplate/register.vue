<template>
    <div id="register">
        <router-link to="/"><img class="logo" src="../assets/image/logo.png" /></router-link>
        <section id="login-content">
            <div class="login-centre">
                <div class="login-switch clearfix">
                    <p class="fr">我已经注册，现在就
                        <router-link to="/Login">登录</router-link>
                    </p>
                </div>
                <div class="login-back">
                    <div class="H-over">
                        <form>
                            <div class="login-input">
                                <label>
                                    <i class="heart">*</i>用户名：</label>
                                <input type="text" v-model="form.nickname" class="list-input1" id="username" name="info[username]" placeholder="">
                            </div>
                            <!-- <div class="login-input">
                                <label>
                                    <i class="heart">*</i>邮箱：</label>
                                <input type="text" v-model="form.nickname" class="list-input1" id="email" name="info[username]" placeholder="">
                            </div> -->
                            <div class="login-input">
                                <label>
                                    <i class="heart">*</i>请设置密码：</label>
                                <input type="password" v-model="form.password" class="list-input" id="password" name="info[password]" placeholder="">
                            </div>
                            <div class="login-input">
                                <label>
                                    <i class="heart">*</i>请确认密码：</label>
                                <input type="password" class="list-input" id="password1" name="info[password]" placeholder="">
                            </div>
                            <div class="login-input">
                                <label>
                                    <i class="heart">*</i>手机号：</label>
                                <input type="text" v-model="form.mobile" class="list-iphone" id="iphone" name="info[password]" placeholder="">
                                <!-- <a href="#" class="obtain">获取短信验证码</a> -->
                            </div>
                            <!-- <div class="login-input">
                                <label>
                                    <i class="heart">*</i>短信验证码：</label>
                                <input type="text" class="list-notes" id="message" name="info[password]" placeholder="">
                            </div> -->
                            <div class="item-ifo">
                                <input type="checkbox" id="readme" checked="checked" class="checkbox">
                                <label for="protocol">我已阅读并同意
                                    <a id="protocol" class="blue" href="#">《插画网站用户协议》</a>
                                </label>
                                <span class="clr"></span>
                            </div>
                            <div class="login-button">
                                <a href="#" @click="register()">立即注册</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

    </div>
</template>

<script>
export default {
  data() {
    return {
      form:{}
    };
  },
  methods: {
    register() {
       this.$http.post("insert",{table:'users',data:this.form}).then(res => {
            
             
              this.$router.push('/Login');
            
          });
    
    },
   
  }
};
</script>

<style scoped>
@import "../assets/css/register.css";
</style>
