$(function () {
    //修改站点信息
    $('#updateWebsiteButton').click(function () {
        $("#updateWebsiteButton").attr("disabled", true);
        //ajax提交数据
        //var params = $("#websiteForm").serialize();
        var websitename=$("#websiteName").val();
        var websitedescription=$("#websiteDescription").val();
        var websitelogo=$("#websiteLogo").val();
        var websiteicon=$("#websiteIcon").val();
        var json={
        		"websitename":websitename,
        		"websitedescription":websitedescription,
        		"websitelogo":websitelogo,
        		"websiteicon":websiteicon
        };
        $.ajax({
            type: "POST",
            url: "/admin/configurations/website",
            data: json,
            dataType:"json",
            success: function (result) {
                if (result.resultCode == 200 && result.data) {
                    swal("保存成功", {
                        icon: "success",
                    });
                }
                else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });
    });
    //个人信息
    $('#updateUserInfoButton').click(function () {
        $("#updateUserInfoButton").attr("disabled", true);
        //var params = $("#userInfoForm").serialize();
        var youravatar=$("#yourAvatar").val();
        var yourname=$("#yourName").val();
        var youremail=$("#yourEmail").val();
        var json={
        		youravatar:youravatar,
        		yourname:yourname,
        		youremail:youremail
        };
        $.ajax({
            type: "POST",
            url: "/admin/configurations/userInfo",
            data: json,
            dataType:"json",
            success: function (result) {
                if (result.resultCode == 200&& result.data) {
                    swal("保存成功", {
                        icon: "success",
                    });
                }
                else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });
    });
    //修改底部设置
    $('#updateFooterButton').click(function () {
        $("#updateFooterButton").attr("disabled", true);
        var params = $("#footerForm").serialize();
        var footerabout=$("#footerAbout").val();
        var footericp=$("#footerICP").val();
        var footercopyright=$("#footerCopyRight").val();
        var footerpoweredby=$("#footerPoweredBy").val();
        var footerpoweredbyurl=$("#footerPoweredByURL").val();
        var json={
        		footerabout:footerabout,
        		footericp:footericp,
        		footercopyright:footercopyright,
        		footerpoweredby:footerpoweredby,
        		footerpoweredbyurl:footerpoweredbyurl
        };
        $.ajax({
            type: "POST",
            url: "/admin/configurations/footer",
            data: json,
            dataType:"json",
            success: function (result) {
                if (result.resultCode == 200&& result.data) {
                    swal("保存成功", {
                        icon: "success",
                    });
                }
                else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });
    });

})
