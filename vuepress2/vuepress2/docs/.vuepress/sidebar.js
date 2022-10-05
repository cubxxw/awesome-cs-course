
export default defineSidebarConfig({
  "/mall/":[
    {
      text: "序章",
      icon: "note",
      collapsable: true,
      prefix: "foreword/",
      children: ["mall_foreword_01", "mall_foreword_02"],
    },
    {
      text: "架构篇",
      icon: "note",
      collapsable: true,
      prefix: "architect/",
      children: ["mall_arch_01", "mall_arch_02","mall_arch_03"],
    },
    {
      text: "业务篇",
      icon: "note",
      collapsable: true,
      prefix: "database/",
      children: ["mall_database_overview", "mall_pms_01","mall_pms_02"],
    },
    {
      text: "技术要点篇",
      icon: "note",
      collapsable: true,
      prefix: "technology/",
      children: ["mybatis_mapper", "aop_log"],
    },
    {
      text: "部署篇",
      icon: "note",
      collapsable: true,
      prefix: "deploy/",
      children: ["mall_deploy_windows", "mall_deploy_docker"],
    }
  ],
  "/springcloud":["springcloud", "eureka", "ribbon"]
});