function DateRange(n, t) {
    var i = this;
    i.StartDate = "";
    i.EndDate = "";
    i.Options = {
        autoApply: !1,
        autoUpdateInput: !0,
        applyClass: "btn btn-primary",
        cancelClass: "btn btn-default",
        showDropdowns: !0,
        showWeekNumbers: !0,
        ranges: {
            "今天": [moment().startOf("day"), moment()],
            "本周": [moment().startOf("isoWeek"), moment()],
            "本月": [moment().startOf("month"), moment()],
            "本季度": [moment().startOf("quarter"), moment()],
            "本年度": [moment().startOf("year"), moment()]
        },
        locale: {
            format: "YYYY/MM/DD",
            separator: "至",
            applyLabel: "确定",
            cancelLabel: "清除",
            fromLabel: "从",
            toLabel: "到",
            customRangeLabel: "自定义",
            weekLabel: "星期",
            daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            firstDay: 1
        }
    };
    i.Create = function () {
        i.minDate && (i.Options.minDate = i.minDate);
        i.maxDate && (i.Options.maxDate = i.maxDate);
        i.singleDatePicker != null && (i.Options.singleDatePicker = i.singleDatePicker);
        $(n).daterangepicker(i.Options, function (n, t) {
            i.StartDate = n.format("YYYY/MM/DD HH:mm:ss");
            i.EndDate = t.format("YYYY/MM/DD HH:mm:ss")
        });
        $(n).on("apply.daterangepicker", function (n, t) {
            i.StartDate = t.startDate.format("YYYY/MM/DD HH:mm:ss");
            i.EndDate = t.endDate.format("YYYY/MM/DD HH:mm:ss")
        });
        $(n).on("cancel.daterangepicker", function () {
            $(n).val("");
            i.StartDate = "";
            i.EndDate = ""
        });
        i.Init()
    };
    i.Init = function () {
        if (t) switch (t) {
            case"today":
                $(n).data("daterangepicker").setStartDate(moment().startOf("day"));
                i.StartDate = moment().startOf("day").format("YYYY/MM/DD HH:mm:ss");
                i.EndDate = moment().endOf("day").format("YYYY/MM/DD HH:mm:ss");
                break;
            case"yesterday":
                $(n).data("daterangepicker").setStartDate(moment().subtract(1, "days").startOf("day"));
                $(n).data("daterangepicker").setEndDate(moment().subtract(1, "days").startOf("day"));
                i.StartDate = moment().subtract(1, "days").startOf("day").format("YYYY/MM/DD HH:mm:ss");
                i.EndDate = moment().subtract(1, "days").endOf("day").format("YYYY/MM/DD HH:mm:ss");
                break;
            case"week":
                $(n).data("daterangepicker").setStartDate(moment().startOf("isoWeek"));
                i.StartDate = moment().startOf("isoWeek").format("YYYY/MM/DD HH:mm:ss");
                i.EndDate = moment().endOf("day").format("YYYY/MM/DD HH:mm:ss");
                break;
            case"month":
                $(n).data("daterangepicker").setStartDate(moment().startOf("month"));
                i.StartDate = moment().startOf("month").format("YYYY/MM/DD HH:mm:ss");
                i.EndDate = moment().endOf("day").format("YYYY/MM/DD HH:mm:ss");
                break;
            case"quarter":
                $(n).data("daterangepicker").setStartDate(moment().startOf("quarter"));
                i.StartDate = moment().startOf("quarter").format("YYYY/MM/DD HH:mm:ss");
                i.EndDate = moment().endOf("day").format("YYYY/MM/DD HH:mm:ss")
        } else $(n).val(""), i.StartDate = "", i.EndDate = ""
    }
}