/**
 * 问题：
 *      由于异步原因，如果调用getOrgSelect()方法，会导致getOrg()还没执行完，返回的data为null
 * 解决：
 *      1、ajax请求改为同步 async: false
 */

/**
 * 系统模块工具类
 * @author 小明不读书
 * @date 2018-10-31
 */

/**
 * 获取系统模块数据
 * @param param.url   请求对应数据的url
 */
function getSysData(param) {
    var data = null;
    $.ajax({
        url: param.url,
        type: 'GET',
        async: false,
        success: function (result) {
            data = result.data;
        }
    });
    return data;
}

/**
 * 通过系统模块数据获取对应的下拉框
 * @param param.url   请求对应数据的url
 * @param param.dom   dom选择器
 */
function getSelect(param) {
    layui.use(['form'], function () {
        //后期打算直接传参进来
        var form = layui.form;
        var data = getSysData(param);
        if (data) {
            var selectHtml = "";
            //如果是顶级，默认为0
            if (param.isParent) {
                selectHtml += "<option value='0'>请选择</option>";
            } else {
                selectHtml += "<option value=''>请选择</option>";
            }
            for (var i in data) {
                selectHtml += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
            }
            $(param.dom).html(selectHtml);
            form.render();
        }
    });
}

/**
 * 获取下拉树（弃用）
 * @param param.id          指定某个ID选择器
 * @param param.url         请求url
 * @param param.selected    选中
 */
function getTreeSelect(param) {
    layui.use(['treeSelect'], function () {
        var treeSelect = layui.treeSelect;
        treeSelect.render({
            // 选择器
            elem: '#' + param.id,
            // 数据
            data: param.url,
            // 异步加载方式：get/post，默认get
            type: 'get',
            // 占位符
            placeholder: '请选择',
            // 是否开启搜索功能：true/false，默认false
            search: false,
            // 点击回调
            click: function (data) {
                $("#" + param.id).val(data.current.id);
            },
            // 加载完成后的回调函数
            success: function (d) {
                //如果大于1，则删除第一个
                if ($("#" + param.id).parent().find(".layui-form-select").length > 1) {
                    $("#" + param.id).parent().find(".layui-form-select").eq(0).remove();
                }
                //选中节点，根据id筛选
                if (param.selected) {
                    treeSelect.checkNode(param.id, param.selected);
                }
                //获取zTree对象，可以调用zTree方法
                /*var treeObj = treeSelect.zTree('tree');
                console.log(treeObj);*/

                //刷新树结构
                treeSelect.refresh();
            }
        });


    });
}

/**
 * 获取角色下拉多选框
 * @param dom   指定某个name选择器
 */
function getMultiSelect(dom) {
    var data = getSysData({
        url: "/sys/role"
    });
    if (data) {
        var multiSelect = [];
        for (var i in data) {
            multiSelect.push({
                "name": data[i].name,
                "value": data[i].id
            });
        }
        layui.use(['formSelects'], function () {
            var formSelects = layui.formSelects;
            formSelects.data(dom, 'local', {
                arr: multiSelect
            });
        });
    }
}

/**
 * 通过Code获取字典，默认查询有效的
 */
function getDictByCode(param) {
    layui.use(['form'], function () {
        var form = layui.form;
        $.ajax({
            url: '/sys/dict'
            , type: 'GET'
            , async: param.async
            , data: {
                pid: param.pid
            }
            , success: function (data) {
                if (data.success) {
                    var data = data.data;
                    var html = "";
                    //是否单选框
                    if (param.isRadio) {
                        for (var i in data) {
                            html += "<input type='radio' lay-filter='" + param.name + "' name='" + param.name + "' value='" + data[i].code + "' title='" + data[i].name;
                            //是否默认选中
                            if (data[i].isDefault == 1) {
                                html += "' checked>";
                            } else {
                                html += "'>";
                            }
                        }

                    } else {
                        //否则下拉框
                        html += "<option value=''>请选择</option>";
                        for (var i in data) {
                            html += "<option value='" + data[i].id;
                            //是否默认选中
                            if (data[i].isDefault == 1) {
                                html += "' selected>" + data[i].name + "</option>";
                            } else {
                                html += "'>" + data[i].name + "</option>";
                            }
                        }
                    }
                    $(param.dom).html(html);
                    form.render();
                }
            }
        });
    });
}