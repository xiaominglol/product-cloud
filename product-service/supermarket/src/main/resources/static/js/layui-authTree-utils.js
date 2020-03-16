/**
 * 权限树工具类
 * @author 小明不读书
 * @date 2018-10-31
 */
function initAuthTree(param) {
    layui.use(['authTree'], function () {
        var authTree = layui.authTree;
        var trees = authTree.listConvert(param.data, {
            primaryKey: 'id'
            , startPid: 0
            , parentKey: 'pid'
            , nameKey: 'name'
            , valueKey: 'id'
            , checkedKey: param.checkedKey
        });

        authTree.render('#authTree', trees, {
            inputname: 'ids[]',
            layfilter: 'lay-check-auth',
            autowidth: true
        });

    });
}