$(document).ready(function() {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();


})
var TableInit = function() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function() {
        $('#table').bootstrapTable({
            url : "manage/listuser.action", //请求后台的URL（*）
            method : "post", //请求方式（*）
            //toolbar : '#toolbar', //工具按钮用哪个容器
            striped : true, //是否显示行间隔色
            queryParams : oTableInit.queryParams,//传递参数（*）
            sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
            contentType : "application/x-www-form-urlencoded",
            cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            responseHandler: function(res) {
                return {

                    "total": res.total,//总页数
                    "rows": res.rows.content //数据
                }
            },
            pagination : true, //是否显示分页（*）
            sortable : false, //是否启用排序
            sortOrder : "asc", //排序方式
            pageNumber : 1, //初始化加载第一页，默认第一页
            pageSize : 10, //每页的记录行数（*）
            pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
            search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch : true,
            showColumns : true, //是否显示所有的列
            showRefresh : true, //是否显示刷新按钮
            minimumCountColumns : 2, //最少允许的列数
            clickToSelect : false, //是否启用点击选中行
            /* height : 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度 */
            uniqueId : "id", //每一行的唯一标识，一般为主键列
            showToggle : true, //是否显示详细视图和列表视图的切换按钮
            cardView : false, //是否显示详细视图
            detailView : false, //是否显示父子表
            columns: [
                {
                    checkbox: true
                },

                {
                    field: 'id',
                    title: '编号'
                },
                {
                    field: 'username',
                    title: '用户名'/*,editable: {
                        type: 'text',
                        title: '用户名'
                    }*/
                }, {
                    field: 'password',
                    title: '密码',editable: {
                        type: 'password',
                        title: '密码'
                    }
                },
                {
                    field: 'name',
                    title: '真实姓名',editable: {
                        type: 'text',
                        title: '真实姓名'
                    }
                }, {
                    field: 'tel',
                    title: '电话',editable: {
                        type: 'text',
                        title: '电话'
                    }
                }, {
                    field: 'email',
                    title: '邮件',editable: {
                        type: 'text',
                        title: '邮件'
                    }
                },	{
                    field: 'address',
                    title: '地址',editable: {
                        type: 'text',
                        title: '地址'
                    }
                }, {
                    field: 'sex',
                    title: '性别',
                    /*formatter : function(value) {
                        if(value == 1){
                            return "男"
                        }else{
                            return "女"
                        }
                    },*/editable: {
                        type: 'select',
                        title: '性别',
                        source:[{value:"1",text:"男"},{value:"2",text:"女"}]
                    }
                }, {
                    field: 'birthday',
                    title: '生日',
                    formatter : function(value,row, index) {
                        if(value == null){
                            return '-'
                        }else{
                            return $.myTime.UnixToDate2(value);
                        }
                    }
                },{
                    field: 'description',
                    title: '个人描述',editable: {
                        type: 'text',
                        title: '个人描述'
                    }
                },{
                    field: 'createTime',
                    title: '创建时间',
                    formatter : function(value,row, index) {
                        if(value == null){
                            return '-'
                        }else{
                            return $.myTime.UnixToDate2(value);
                        }
                    }
                }, {
                    field: 'modifyTime',
                    title: '修改时间',
                    formatter : function(value,row, index) {
                        if(value == null){
                            return '-'
                        }else{
                            return $.myTime.UnixToDate2(value);
                        }
                    }
                }, {
                    field: 'state',
                    title: '状态',
                    /*formatter : function(value) {
                        if(value == 2){
                            return "禁用"
                        }else{
                            return "激活"
                        }
                    },*/editable: {
                            type: 'select',
                            title: '状态',
                            source:[{value:"2",text:"禁用"},{value:"1",text:"激活"}]
                    }
                }
            ],
            onEditableSave: function (field, row, oldValue, $el) {
                console.log(field + "," + row[field] + "," + oldValue + "," + $el)
                var strData = "{\"id\":"+row.id+",\""+field+"\":\""+row[field]+"\"}"

                var oData =JSON.parse(strData)
                console.log(oData)
                $.ajax({
                    type: "post",
                    url: "manage/updateuser.action",
                    data: oData,
                    dataType: 'json',
                    success: function (data) {
                        alert(data.message)
                    },
                    error: function () {
                        alert.msg("失败")
                    },
                    complete: function () {

                    }

                });
            }

        });
    };
    //得到查询的参数
    oTableInit.queryParams = function(params) {
        var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            rows: params.limit, 			//页面大小
            page : params.offset/params.limit	//页号
        };
        return temp;
    };
    return oTableInit;
};