package com.gemini.business.admin.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.gemini.business.admin.entity.SubjectData;
import com.gemini.business.admin.po.DictPo;
import com.gemini.business.admin.service.PlatformDictService;

public class ExcelListener extends AnalysisEventListener<SubjectData> {

    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    //不能实现数据库操作
    public PlatformDictService service;

    public ExcelListener() {
    }

    public ExcelListener(PlatformDictService service) {
        this.service = service;
    }

    //读取excel内容，一行一行进行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {


        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断一级分类是否重复
        DictPo existOneSubject = this.existOneSubject(service, subjectData.getOneSubjectName());
        if (existOneSubject == null) { //没有相同一级分类，进行添加
            existOneSubject = new DictPo();
            existOneSubject.setPid(null);
            existOneSubject.setName(subjectData.getOneSubjectName());//一级分类名称
            service.insertAsync(existOneSubject, true);
        }

        //获取一级分类id值
        Long pid = existOneSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        DictPo existTwoSubject = this.existTwoSubject(service, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            existTwoSubject = new DictPo();
            existTwoSubject.setPid(pid);
            existTwoSubject.setName(subjectData.getTwoSubjectName());//二级分类名称
            service.insertAsync(existTwoSubject, true);
        }
    }

    //判断一级分类不能重复添加
    private DictPo existOneSubject(PlatformDictService service, String name) {
        DictPo po = new DictPo();
        po.setName(name);
        po.setPid(null);

        return service.getByParam(po);
    }

    //判断二级分类不能重复添加
    private DictPo existTwoSubject(PlatformDictService service, String name, Long pid) {
        DictPo po = new DictPo();
        po.setName(name);
        po.setPid(pid);
        return service.getByParam(po);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
