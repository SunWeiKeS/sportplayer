<template>
    <!--引入布局-->
    <el-container class="home_container">
        <!--头部-->
        <el-header>
            <div>
                <img src="../assets/logo.jpg" alt=""/>
                <span>个人运动平台</span>
            </div>

            <el-button type="info" @click="logout">安全退出</el-button>
        </el-header>
        <!--主体-->
        <el-container>
            <!--侧边栏-->
            <el-aside :width="isCollapse?'64px':'200px'">
                <div class="toggle-button" @click="toggleCollapase">|||</div>
                <el-menu background-color="#545c64" text-color="#fff" active-text-color="#409eff"
                unique-opened
                         :collapse="isCollapse"
                         :collapse-transition="false"
                         :router="true" :default-active="activePath">
                    <!--一级菜单-->
                    <el-submenu :index="item.id+''" v-for="item in menuList":key="item.id">
                        <template slot="title">
                            <i :class="iconsObject[item.id]"></i>
                            <span>{{item.title}}</span>
                        </template>
                        <!--二级菜单-->
                        <el-menu-item :index="it.path" v-for="it in item.sList":key="it.id" @click="saveNavState(it.path)">
                            <template slot="title">
                                <i :class="iconsObject[it.id]"></i>
                                <span>{{it.title}}</span>
                            </template>
                        </el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <!--主体内容-->
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
export default {
    data() {
        return {
            //菜单列表
            menuList: [],
            isCollapse:false,//伸缩属性
            iconsObject: {
                '100':'iconfont icon-guanliyuan',
                '200':'iconfont icon-ziyuan',
                '101':'iconfont icon-denglu',
                '102':'iconfont icon-mima',
                '103':'iconfont icon-ziyuan',
                '104':'iconfont icon-shangpin',
                '201':'iconfont icon-shangpin',
                '202':'iconfont icon-qialuli',
                '203':'iconfont icon-shiwu',
                '204':'iconfont icon-denglu',
            },
            activePath:'/welcome',//默认路径
        }
    },
    //onload 事件
    created() {
        //查询我的 menuList
        this.getMenuList();
        this.activePath=window.sessionStorage.getItem("activePath");//取出session里的path 动态修改activePath
    },
    methods: {
        logout() {
            window.sessionStorage.clear();//安全退出需要销毁信息清除session
            this.$router.push("/login");//回到首页
        },
        //获取导航菜单方法
        async getMenuList() {
            const {data: res} = await this.$http.get("menus");
            if (res.flag != 200) return this.$message.error("获取列表失败");//访问失败的错误信息
            //访问成功则数据回填
            this.menuList = res.menus;
        },
        //控制伸缩
        toggleCollapase(){
            this.isCollapse=!this.isCollapse;
        },
        //保存路径
        saveNavState(activePath){
            window.sessionStorage.setItem('activePath',activePath);//存放在session里
            this.activePath=activePath;

        }
    }

};
</script>

<style lang="less" scoped>
//布局器样式
.home_container {
    height: 100%;
}

//头样式
.el-header {
    background-color: #373d41;
    display: flex;
    justify-content: space-between; //左右贴边
    padding-left: 0%; //左边界
    color: #fffdef;
    font-size: 20px;
    align-items: center;
    > div {
        display: flex;
        align-items: center;

        span {
            margin-left: 15px;
        }
    }
}

//侧边样式
.el-aside {
    background-color: #333744;
    .el-menu{
        border-right: none;
    }
}

//主体样式
.el-main {
    background-color: #eaedf1;
}

img {
    width: 55px;
    height: 55px;
}
// |||按钮样式
.toggle-button{
    background-color: #4A5064;
    font-size: 10px;
    line-height: 24px;
    color: #fff;
    text-align: center;
    letter-spacing: 0.2em;
    cursor: pointer;//显示小手
}
</style>
