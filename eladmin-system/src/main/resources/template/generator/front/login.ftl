<template>
    <div class="home-page">
        <div class="container">
            <div class="content-title">欢迎来到插画世界</div>
            <div class="latest">
                <div class="latest-title">最新作品推荐
                    <span class="change fr">换一批</span>
                </div>
                <ul class="latest-content" v-for="itemList in pubList">
                    <li class="latest-list fl" v-for="item in itemList" :key="item.id">
                        <!-- 大图 -->
                        <router-link :to="'/Details/'+item.id"><img class="b-pic" :src="item.url" alt="插画" /></router-link>
                        <div class="l-t">{{item.${title}}}</div>
                        <!-- 小图 -->
                        <a href="#"><img class="u-pic fl" :src="item.url" /></a>
                        <div class="l-member fl">
                            <span>{{item.${param}}}</span>
                        </div>
                        <div class="s-member fl">
                            <span>{{item.${param1}}}</span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
    import {${tableName}} from "../sqlMap.js";
    export default {
        data() {
            return {
                pubList: []
            };
        },
        created() {
            var sql = ${tableName}.getAll;
            this.$http.post("action", { sql: sql }).then(res => {
                var data = res.data;
            var li = [],
                    j = 0;
            for (let i = 0; i < data.length; i++) {
                const element = data[i];
                if (4 * j + i < data.length) {
                    li.push(element);
                } else {
                    this.pubList[j] = li;
                    j++;
                    li = [];
                }
            }
            this.pubList[j] = li;
            this.$forceUpdate();
        });
        }
    };
</script>
<style scoped>
    @import "../assets/css/reset.css";
    @import "../assets/css/head-page.css";
</style>
