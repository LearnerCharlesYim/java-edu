package com.charles.guli.edu.domain.dto.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.charles.guli.edu.domain.pojo.Subject;
import com.charles.guli.edu.repository.SubjectRepository;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public SubjectRepository subjectRepository;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        Subject subject = saveOneSubject(subjectData.getOneSubjectName());
        saveTwoSubject(subjectData.getTwoSubjectName(), subject.getId());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    private Subject saveOneSubject(String title) {
        Subject subjectOne = subjectRepository.findByTitleAndParentId(title, 0);
        if (subjectOne == null) {
            subjectOne = new Subject();
            subjectOne.setTitle(title);
            subjectOne.setParentId(0);
            return subjectRepository.save(subjectOne);
        }
        return subjectOne;
    }

    private void saveTwoSubject(String title, Integer parentId) {
        Subject subjectTwo = subjectRepository.findByTitleAndParentId(title, parentId);
        if (subjectTwo == null) {
            subjectTwo = new Subject();
            subjectTwo.setTitle(title);
            subjectTwo.setParentId(parentId);
            subjectRepository.save(subjectTwo);
        }
    }
}
