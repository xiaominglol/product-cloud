/**
 * 增删查改工具类
 * @author 小明不读书
 * @date 2018-10-31
 */
// 分页表格
function renderTable(param) {
    layui.use(['table'], function () {
        var table = layui.table;
        var dom = param.dom == null ? "table" : param.dom;
        table.render({
            elem: '#' + dom
            , height: param.height == null ? 'full-220' : param.height
            , url: param.url
            , limit: param.limit == null ? 10 : param.limit
            , data: param.data
            , page: param.page == null ? true : param.page      //默认开启分页
            , cols: param.cols
            , where: param.where
            , done: function (res, curr, count) {
                // 明细表有没有下拉框
                if (param.hasSelect) {
                    var tableData = table.cache.detailTable;
                    for (var i in param.hasSelect) {
                        // 组织架构类型
                        getDictByCode({
                            dom: param.hasSelect[i].dom
                            , pid: param.hasSelect[i].pid
                            , async: false
                        });
                        for (var j in tableData) {
                            $("#detailTable").parent().find("tbody tr").eq(j).find(param.hasSelect[i].dom).val(res.data[j][param.hasSelect[i].dataName]);
                        }
                    }
                }
                // 是否多选
                if (param.isMultiSelect) {
                    checkboxMultiSelect($, dom);
                } else {
                    checkboxRadio($, dom);
                }
            }
            , request: {
                pageName: 'pageNum'             //页码的参数名称，默认：page
                , limitName: 'pageSize'         //每页数据量的参数名，默认：limit
            }
            , parseData: function (res) {       //res 即为原始返回的数据
                return {
                    "code": 0,                  //解析接口状态
                    "msg": res.message,         //解析提示文本
                    "count": res.data.total,    //解析数据长度
                    "data": res.data.records       //解析数据列表
                };
            }
        });
    });
}

// 树形表格
function renderTreeTable(param) {
    layui.use(['treetable'], function () {
        var treeTable = layui.treetable;
        var dom = param.dom == null ? "table" : param.dom;
        treeTable.render({
            treeColIndex: 1,            // 树形图标显示在第几列（必填）
            treeSpid: null,             // 顶级pid（必填）
            treeIdName: 'id',           // id字段名
            treePidName: 'pid',         // pid字段名
            treeDefaultClose: true,     // 是否默认折叠
            treeLinkage: false,         // 父级展开时是否自动展开所有子级
            elem: '#' + dom,
            url: param.url,
            where: param.data,
            cols: param.cols
            , done: function (res, curr, count) {
                // 是否多选
                if (param.isMultiSelect) {
                    checkboxMultiSelect($, dom);
                } else {
                    checkboxRadio($, dom);
                }
            }
        });
    });
}

// 刷新表格
function refreshTable(param) {
    layui.use(['table'], function () {
        var table = layui.table;
        var dom = param.dom == null ? "table" : param.dom
        table.reload(dom, {
            where: param.where
            , data: param.data
            , page: param.page == null ? {curr: 1} : ""     //默认从第一页开始
            , done: param.done == null ? "" : param.done
        });
    });
}

// 打开 弹出框
function openPopup(param) {
    //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
    var type;
    var content;
    var title = param.title;
    var area = param.area == null ? ['900px', '550px'] : param.area;
    if (param.type == 'html') {
        type = 2;
        content = param.html;
    } else {//param.type == 'id'
        type = 1;
        content = $('#' + (param.id == null ? 'addOrUpdate' : param.id));
    }
    layui.use(['layer'], function () {
        var layer = layui.layer;
        layer.open({
            type: type
            , title: title
            , content: content
            //,skin: 'layui-layer-molv'
            , maxmin: true
            , area: area
            , btn: ['保存', '重置']
            , yes: function (index, layero) {
                layero.find('.save').click();
            }
            , btn2: function (index, layero) {
                layero.find('.reset').click();
                return false;
            }
            , success: function (layero) {
                //终于完美解决遮罩层这个问题了
                //https://blog.csdn.net/q646926099/article/details/78797091?%3E
                $(".layui-layer-shade").appendTo(layero.parent());
            }
            , cancel: function (index, layero) {
                layer.close(index);
                $('.reset').click();
                return false;
            }
        })

    });
}

// 渲染树
function renderTree(param) {
    var data = getSysData({
        url: param.url
    });
    initTree({
        dom: param.dom == null ? "tree" : param.dom
        , data: data
        , id: "id"
        , pid: "pid"
        , name: "name"
        , click: param.click
    });
}

// 初始化树
function initTree(param) {
    $('#' + param.dom).html("");
    var nodes = buildTree(param.data, param.id, param.pid, param.name);
    for (var i = 0; i < nodes.length; i++) {
        //主目录是否默认打开
        nodes[i].spread = true;
        if (nodes[i].children && nodes[i].children.length > 0) {
            for (var j = 0; j < nodes[i].children.length; j++) {
                //子目录是否默认打开
                nodes[i].children[j].spread = false;
            }
        }
    }
    layui.use(['tree'], function () {
        var tree = layui.tree;
        tree({
            elem: '#' + param.dom //传入元素选择器
            , nodes: nodes
            , click: param.click
        });
    });
}

//下拉树
function downpanel() {
    $(".downpanel").on("click", ".layui-select-title", function (e) {
        $(".layui-form-select").not($(this).parents(".layui-form-select")).removeClass("layui-form-selected");
        $(this).parents(".downpanel").toggleClass("layui-form-selected");
        layui.stope(e);
    }).on("click", "dl i", function (e) {
        layui.stope(e);
    });
    $(document).on("click", function (e) {
        $(".downpanel").removeClass("layui-form-selected");
    });
}

// 构建树
function buildTree(nodes, id, pid, name) {
    var tree = new Array();
    if (null != nodes) {
        for (var i = 0; i < nodes.length; i++) {
            var nodeItem = nodes[i];
            var json = nodeItem;
            if (!nodeItem[pid]) {
                var child = buildChildTree(nodeItem[id], nodes, id, pid, name);
                // 递归增加子节点
                if (child.length > 0) {
                    json.children = child;
                }
                json.id = nodeItem[id];
                json.name = nodeItem[name];
                tree.push(json);
            }
        }
    }
    return tree;
}

// 构建树
function buildChildTree(key, nodes, id, pid, name) {
    var child = new Array();

    for (var i = 0; i < nodes.length; i++) {
        var nodeItem = nodes[i];
        var json = nodeItem;
        if (key == nodeItem[pid]) {
            var cchild = buildChildTree(nodeItem[id], nodes, id, pid, name);
            if (cchild.length > 0) {
                json.children = cchild;
            }
            json.id = nodeItem[id];
            json.name = nodeItem[name];
            child.push(json);
        }
    }
    return child;
}

// 添加一行
function addRowButton(param) {
    layui.use(['table'], function () {
        var table = layui.table;

        var data = {};
        var id = "";//判断唯一字段,一般就是第一个，除开checkbox
        for (var i in param.cols) {
            if (param.cols[i] && param.cols[i].hasOwnProperty("field")) {
                if (!id) {
                    id = param.cols[i].field;
                }
                data[param.cols[i].field] = '';
            }
        }

        var tableData = table.cache[param.table];
        if (tableData && tableData.length > 0) {
            for (var i = 0; i < tableData.length; i++) {
                if (!tableData[i][id]) {
                    layer.msg("必填项不能为空！", {
                        icon: 5
                    });
                    return false;
                }
            }
        }

        tableData.unshift(data);

        table.render({
            elem: '#' + param.table
            , data: tableData
            , id: param.table
            , cols: [param.cols]
            , page: true
            , done: function (res, curr, count) {
                checkboxMultiSelect($, "detailTable");
            }
        });
    });
}

// 批量删除
function batchDelButton(param) {
    layui.use(['table'], function () {
        var table = layui.table;
        var tableData = table.cache[param.table];
        var selectedData = table.checkStatus([param.table]);
        if (!tableData || tableData.length < 1) {
            layer.msg("表格为空！", {
                icon: 5
            });
            return false;
        }
        if (!selectedData.data || selectedData.data.length < 1) {
            layer.msg("请至少选择一条数据！", {
                icon: 5
            });
            return false;
        }

        var id = "";//判断唯一字段,一般就是第一个，除开checkbox
        for (var i in param.cols) {
            if (param.cols[i] && param.cols[i].hasOwnProperty("field")) {
                if (!id) {
                    id = param.cols[i].field;
                }
            }
        }

        for (var i = 0; i < selectedData.data.length; i++) {
            for (var j = 0; j < tableData.length; j++) {
                if (selectedData.data[i][id] == tableData[j][id]) {
                    tableData.splice(j, 1);
                }
            }
        }
        table.render({
            elem: '#' + param.table
            , data: tableData
            , id: param.table
            , cols: [param.cols]
            , done: function (res, curr, count) {
                checkboxMultiSelect($, "detailTable");
            }
        });
    });
}
