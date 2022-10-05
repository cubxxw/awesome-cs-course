export default defineNavbarConfig([
    "/",
    "/home",
    {
        text: "mall学习教程",
        icon: "launch",
        prefix: "/mall/",
        children: [
            {
                text: "序章",
                icon: "note",
                link: "foreword/mall_foreword_01",
            },
            {
                text: "架构篇",
                icon: "note",
                link: "architect/mall_arch_01",
            },
            {
                text: "业务篇",
                icon: "note",
                link: "database/mall_database_overview",
            },
            {
                text: "技术要点篇",
                icon: "note",
                link: "technology/mybatis_mapper",
            },
            {
                text: "部署篇",
                icon: "note",
                link: "deploy/mall_deploy_windows",
            }
        ],
    },
    {
        text: "SpringCloud学习教程",
        icon: "hot",
        link: "/springcloud/springcloud",
    },
    {
        text: "项目地址",
        icon: "stack",
        children: [
            {
                text: "后台项目",
                link: "https://github.com/macrozheng/mall",
            },
            {
                text: "前端项目",
                link: "https://github.com/macrozheng/mall-admin-web",
            },
            {
                text: "学习教程",
                link: "https://github.com/macrozheng/mall-learning",
            },
            {
                text: "项目骨架",
                link: "https://github.com/macrozheng/mall-tiny",
            }
        ],
    },
]);