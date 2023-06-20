package com.tw.contact.service.Impl;

import com.tw.contact.QuestionReportRequestDTO;
import com.tw.contact.modelJPA.QuestionReport;
import com.tw.contact.modelJPA.dao.QuestionReportRepository;
import com.tw.contact.service.QuestionReportService;
import com.tw.member.model.dao.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class QuestionReportServiceImpl implements QuestionReportService {
    private QuestionReportRepository questionReportRepository;
    private MemberRepository memberRepository;

    @Autowired
    public QuestionReportServiceImpl(QuestionReportRepository questionReportRepository, MemberRepository memberRepository) {
        this.questionReportRepository = questionReportRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void createQuestionReport(QuestionReportRequestDTO questionReportRequestDTO) {
        QuestionReport questionReport = new QuestionReport();
        questionReport.setMember(memberRepository.findByMemberId(questionReportRequestDTO.getMemberId()));
        questionReport.setQuestionContent(questionReportRequestDTO.getQuestionContent());
        questionReport.setStartTime(LocalDateTime.now());
        save(questionReport);

    }
    @Override
    public void save(QuestionReport questReport) {
        questionReportRepository.save(questReport);
    }

    @Override
    public List<QuestionReport> showQuestReportById(int memberId) {
        return questionReportRepository.findByMemberMemberId(memberId);
    }

    @Override
    public void deleteQuestionReport(int id) {
        questionReportRepository.deleteById(id);
    }
}