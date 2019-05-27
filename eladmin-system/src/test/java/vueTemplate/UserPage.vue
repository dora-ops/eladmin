<template>
    <div id="userPage">
        <v-nav></v-nav>
        <div class="us-container">
            <div class="us-section fl">
                <div class="sideNav">
                    <ul>
                        <li class="active">
                            <router-link to="/UserPage">
                                <i class="_2th"></i>关注更新</router-link>
                        </li>
                        <li>
                            <router-link to="/UserPage02">
                                <i class="_3th"></i>作品管理</router-link>
                        </li>
                        <li>
                            <router-link to="/UserPage03">
                                <i class="_5th"></i>我的喜欢</router-link>
                        </li>
                        <li>
                            <router-link to="/UserPage04">
                                <i class="_6th"></i>我的关注</router-link>
                        </li>
                        <li>
                            <router-link to="/UserPage05">
                                <i class="_7th"></i>修改资料</router-link>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="the-works fl">
                <div class="us-title">关注作者更新</div>
                <ul class="se-content">
                    <li class="se-list fl" v-for="item in pubList" :key="item.id">
                        <router-link :to="'/Details/'+item.id"><img class="b-pic" :src="item.url" alt="插画" /></router-link>
                        <div class="l-t">{{item.title}}</div>
                        <a href="#"><img class="u-pic fl" :src="item.url" /></a>
                        <div class="l-member fl">
                            <span>{{item.like_count}}</span>
                        </div>
                        <div class="s-member fl">
                            <span>{{item.com_count}}</span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
import nav from "./head-nav.vue";
import VNav from "./head-nav";
import { user_pub, resource, users, user_follow } from "../sqlMap.js";
export default {
  components: {
    VNav,
    "v-nav": nav
  },
  data() {
    return {
      pubList: []
    };
  },
  created() {
    var currentUser = JSON.parse(localStorage.getItem("user"));
    var sql = user_follow.find.replace("?", currentUser.id);
    this.$http
      .post("action", {
        sql: sql
      })
      .then(res => {
        var data = res.data;
        this.pubList = data;
        console.log(this.pubList);
      });
  }
};
</script>

<style scoped>
@import "../assets/css/reset.css";
@import "../assets/css/user-page.css";
</style>
