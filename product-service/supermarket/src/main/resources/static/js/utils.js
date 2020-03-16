/**
 * 常用工具类
 * @author 小明不读书
 * @date 2018-10-31
 */
// 单选框 行选中
function radioRowSelect($, id) {
    $('#' + id).next().find('.layui-table tr').click(function () {
        //操作   例如行变色
        //$(this).css('background', 'yellow');
        var checkCellT = $(this).parent().find("td div.laytable-cell-radio div.layui-form-radioed I");
        for (var i = 0; i < checkCellT.length; i++) {
            checkCellT[i].click();
        }
        var checkCell = $(this).find("td div.laytable-cell-radio div.layui-form-radioed I");
        if (checkCell.length > 0) {
            checkCell[0].click();
        }
    });
}

// 多选框 单选 行选中
function checkboxRadio($, id) {
    $('#' + id).next().find('.layui-table tr').click(function () {
        //操作   例如行变色
        //$(this).css('background', 'yellow');
        var checkCellT = $(this).parent().find("td div.laytable-cell-checkbox div.layui-form-checked I");
        for (var i = 0; i < checkCellT.length; i++) {
            checkCellT[i].click();
        }
        var checkCell = $(this).find("td div.laytable-cell-checkbox div.layui-form-checkbox I");
        if (checkCell.length > 0) {
            checkCell[0].click();
        }
    });
}

// 多选框 多选 行选中
function checkboxMultiSelect($, id) {
    $('#' + id).next().find('.layui-table tr').click(function () {
        var checkCell = $(this).find("td div.laytable-cell-checkbox div.layui-form-checkbox I");
        for (var i = 0; i < checkCell.length; i++) {
            checkCell[i].click();
        }
    });
}

// 清空表单数据
function clearFrom() {
    $(".modal form input").each(function () {
        // 缺点，不能初始化原来input的value值，待解决
        $(this).val('');
    });
    $(".modal form select").each(function () {
        // 默认选中第一个
        $(this).prop('selectedIndex', 0);
    });
}
