package com.cms.web.task;

import com.cms.model.sys.SysConfig;
import com.cms.service.sys.LogService;
import com.cms.service.sys.SysConfigService;
import com.cms.utils.Logger;
import com.cms.utils.SystemInfo;
import com.xuan.utils.DateUtils;
import com.xuan.utils.Validators;
import org.hyperic.sigar.Sigar;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 方法描述:定时发送邮件
 * 发送邮件的任务调度
 * author 小刘
 * version v1.0
 * date 2017/4/18 16:19
 */
@Component
public class EmailTaskController {

    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private LogService logService;

    // 系统资源监控
    public void emailTask() {
        // 当前系统资源使用率
        Map<String, Object> usage = SystemInfo.usage(new Sigar());
        String cpuUsage = usage.get("cpuUsage") + "";// CPU使用率
        String serverUsage = usage.get("ramUsage") + "";// 系统内存使用率
        String JvmUsage = usage.get("jvmUsage") + "";// 计算JVM内存使用率

        // 设置报警使用率
        SysConfig cpu = sysConfigService.getConfig(1);   // CPU
        SysConfig ram = sysConfigService.getConfig(2);   // RAM
        SysConfig jvm = sysConfigService.getConfig(3);   // JVM
        SysConfig email = sysConfigService.getConfig(4); // Email

        // 当系统消耗内存大于或等于用户设定的内存时，发送邮件
        String cpubool = "";
        String jvmbool = "";
        String rambool = "";
        String mark = "<font color='red'>";
        if (Double.parseDouble(cpuUsage) > Double.parseDouble(cpu.getVal())) {
            cpubool = "style=\"color: red;font-weight: 600;\"";
            mark += "CPU当前：" + cpuUsage + "%,超出预设值  " + cpu.getVal() + "%<br>";
        }
        if (Double.parseDouble(JvmUsage) > Double.parseDouble(jvm.getVal())) {
            jvmbool = "style=\"color: red;font-weight: 600;\"";
            mark += "JVM当前：" + JvmUsage + "%,超出预设值 " + jvm.getVal() + "%<br>";
        }
        if (Double.parseDouble(serverUsage) > Double.parseDouble(ram.getVal())) {
            rambool = "style=\"color: red;font-weight: 600;\"";
            mark += "内存当前：" + serverUsage + "%,超出预设值  " + ram.getVal() + "%";
        }
        mark += "</font>";

        // 邮件内容
        String title = "服务器预警提示 - " + DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
        String centent = "当前时间是：" + DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "<br/><br/>" + "<style type=\"text/css\">" + ".common-table{" + "-moz-user-select: none;" + "width:100%;" + "border:0;" + "table-layout : fixed;" + "border-top:1px solid #dedfe1;" + "border-right:1px solid #dedfe1;" + "}" +
                "/*header*/" + ".common-table thead td,.common-table thead th{" + "    height:23px;" + "   background-color:#e4e8ea;" + "   text-align:center;" + "   border-left:1px solid #dedfe1;" + "}" +
                ".common-table thead th, .common-table tbody th{" + "padding-left:7px;" + "padding-right:7px;" + "width:15px;" + "text-align:center;" + "}" +
                ".common-table tbody td,  .common-table tbody th{" + "    height:25px!important;" + "border-bottom:1px solid #dedfe1;" + "border-left:1px solid #dedfe1;" + "cursor:default;" + "word-break: break-all;" + "-moz-outline-style: none;" + "_padding-right:7px;" + "text-align:center;" + "}</style>"
                + "<table class=\"common-table\">" + "<thead>" + "<tr>" + "<td width=\"100\">名称</td>" + "<td width=\"100\">参数</td>" + "<td width=\"275\">预警设置</td>" + "</tr>" + "</thead>" + "<tbody id=\"tbody\">" + "<tr " + cpubool + "><td>CPU</td><td style=\"text-align: left;\">当前使用率：" + cpuUsage
                + "%</td><td>使用率超出  " + cpu.getVal() + "%,,发送邮箱提示 </td></tr>" + "<tr " + rambool + "><td>服务器内存</td><td style=\"text-align: left;\">当前使用率：" + serverUsage + "%</td><td>使用率超出  " + ram.getVal() + "%,发送邮箱提示 </td></tr>" + "<tr " + jvmbool + "><td>JVM内存</td><td style=\"text-align: left;\">当前使用率：" + JvmUsage
                + "%</td><td>使用率超出  " + jvm.getVal() + "%,,发送邮箱提示 </td></tr>" + "</tbody>" + "</table>";
        mark = mark.replaceAll("'", "\"");
        if (!Validators.isEmpty(cpubool) || !Validators.isEmpty(jvmbool) || !Validators.isEmpty(rambool)) {
            try {
                // 发送警告邮件
                Email emailer = new EmailBuilder()
                        .from("后台系统监控", "liuzhihai520@163.com")
                        .to("亲爱的管理员", email.getVal())
                        .subject(title)
                        .textHTML(centent)
                        .build();
                new Mailer("smtp.163.com", 25, "liuzhihai520@163.com", "liuzhihai520").sendMail(emailer);

                // 保存预警信息
                usage.put("setCpuUsage", cpu.getVal());
                usage.put("setJvmUsage", jvm.getVal());
                usage.put("setRamUsage", ram.getVal());
                usage.put("email", email.getVal());
                usage.put("mark", mark);
                logService.serverLog(usage);
                System.err.println("===================发送邮件！=================");
            } catch (Exception e) {
                Logger.error(e);
                e.printStackTrace();
                System.err.println("============发送邮件失败！============");
            }
        }
    }
}
