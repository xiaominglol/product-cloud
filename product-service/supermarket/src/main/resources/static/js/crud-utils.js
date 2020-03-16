/**
 * 增删查改工具类
 * @author 小明不读书
 * @date 2018-10-31
 */

/**
 * 新增
 * @param param
 * @returns {string}
 */
function add(param) {
    var requestType = 'POST';
    layui.use(['table'], function () {
        var table = layui.table;

        $("#addOrUpdate [name='id']").val("");
        // 如果有明细表格，则显示，因为有可能只是单表
        if (param.baseDetailCols) {
            table.render({
                elem: '#detailTable'
                , data: []
                , id: 'detailTable'
                , cols: [param.baseDetailCols]
                , page: true
                , done: function (res, curr, count) {
                    checkboxMultiSelect($, "detailTable");
                }
            });
        }
        // 打开添加框
        openPopup({
            title: param.title,
            id: param.id
        });
    });
    return requestType;
}

/**
 * 修改
 * @param param
 * @returns {string}
 */
function edit(param) {
    var requestType = 'PUT';
    layui.use(['form', 'table'], function () {
        var form = layui.form;
        var table = layui.table;
        var selectedData = table.checkStatus('table');
        if (selectedData.data.length == 1) {
            $("#addOrUpdate [name='id']").val(selectedData.data[0].id);
            for (var i in param.fields) {
                if (param.fields[i].type == 'text') {
                    $("#addOrUpdate [name='" + param.fields[i].field + "']").val(selectedData.data[0][param.fields[i].field]);
                } else if (param.fields[i].type == 'select') {
                    $("#addOrUpdate [name='" + param.fields[i].field + "']").val(selectedData.data[0][param.fields[i].field]);
                } else if (param.fields[i].type == 'radio') {
                    $("#addOrUpdate [name='" + param.fields[i].field + "'][value='" + selectedData.data[0][param.fields[i].field] + "']").prop("checked", true);
                }
            }
            // 如果有明细表格
            if (param.baseDetailCols) {
                renderTable({
                    dom: "detailTable"
                    , url: param.url
                    , height: ''
                    , page: false
                    , where: {"pid": selectedData.data[0].id}
                    , cols: [param.baseDetailCols]
                    , hasSelect: param.hasSelect
                });
            }

            form.render();
            // 打开修改框
            openPopup({
                title: param.title
            });
        } else {
            layer.msg('请选择一条数据', {icon: 0});
        }

    });
    return requestType;
}


/**
 * 添加/修改 保存
 * @param param
 * @returns {*}
 */
function saveOrUpdate(param) {
    var close = param.close == null ? true : param.close;
    $.ajax({
        url: param.url
        , type: param.type
        , data: JSON.stringify(param.data)
        , async: param.async == null ? true : false
        , contentType: 'application/json;charset=utf-8'
        , success: function (data) {
            if (data.success) {
                success(param);
                layer.msg('保存成功', {icon: 1});
                if (close) {
                    layer.closeAll('page');
                }
            } else {
                layer.msg(data.message, {icon: 5});
            }

            $('.reset').click();
        },
        error: function (data) {
            layer.msg('保存失败', {icon: 5});
        }
    });
}

/**
 * 删除
 * @param param
 */
function del(param) {
    layer.msg('确定删除？', {
        time: 0
        , btn: ['确定', '取消']
        , yes: function (index) {
            $.ajax({
                url: param.url
                , type: 'DELETE'
                , async: param.async == null ? true : false
                , success: function (data) {

                    if (data.success) {
                        success(param);

                        layer.msg('删除成功', {icon: 1});
                        if (param.close) {
                            layer.closeAll('page');
                        }
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }

                    $('.reset').click();

                }
                , error: function (data) {
                    layer.msg("删除异常", {icon: 5});
                }
            });
        }
    });
}


/**
 * 是否确定更新
 * @param param
 */
function confirmUpdate(param) {
    var close = param.close == null ? true : param.close;
    layer.msg('确定' + param.msg + '？', {
        time: 0
        , btn: ['确定', '取消']
        , yes: function (index) {
            $.ajax({
                url: param.url
                , data: JSON.stringify(param.data)
                , type: 'PUT'
                , contentType: 'application/json;charset=utf-8'
                , success: function (data) {
                    if (data.success) {
                        success(param);
                        layer.msg(param.msg + '成功', {icon: 1});
                        if (close) {
                            layer.close(index);
                        }
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                }
                , error: function (data) {
                    layer.msg(param.msg + "异常", {icon: 5});
                }
            });
        }
    });
}

function success(param) {
    if (param.isTable) {
        renderTable({
            url: param.baseUrl
            , dom: param.dom
            , page: param.page
            , height: param.height
            , limit: param.limit
            , cols: param.baseCols
            , where: param.where
        });
    } else {
        renderTree({
            url: param.baseUrl
            , click: function (node) {
                renderTreeTable({
                    url: param.baseUrl
                    , data: {id: node.id}
                    , cols: param.baseCols
                });
            }
        });
        renderTreeTable({
            url: param.baseUrl
            , cols: param.baseCols
        });
    }
}