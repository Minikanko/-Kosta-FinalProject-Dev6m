package org.kosta.watflix.controller;

import javax.annotation.Resource;

import org.kosta.watflix.model.service.ReportService;
import org.kosta.watflix.model.vo.CommentsListVO;
import org.kosta.watflix.model.vo.CommentsVO;
import org.kosta.watflix.model.vo.MemberVO;
import org.kosta.watflix.model.vo.ReportListVO;
import org.kosta.watflix.model.vo.ReportTypeVO;
import org.kosta.watflix.model.vo.ReportVO;
import org.kosta.watflix.model.vo.ReviewListVO;
import org.kosta.watflix.model.vo.ReviewVO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReportController {
	@Resource
	private ReportService reportService;
	
	// 신고 from(리뷰, 평점)으로 이동
	@RequestMapping("reportForm.do")
	public ModelAndView reportReviewForm() {
		return new ModelAndView("report/report_form");
	}

	// 신고글 등록(리뷰, 평점)
	@PostMapping("reportRegister.do")
	public String reportReviewRegister(ReviewVO reviewVO, CommentsVO commentsVO, int reportTypeNo, String reportContents) {
		MemberVO mvo = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id = mvo.getId();
		// 신고 VO
		ReportVO reportVO = new ReportVO();
		// 아이디
		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		reportVO.setMemberVO(memberVO);
		// 신고 타입
		ReportTypeVO reportTypeVO = new ReportTypeVO();
		reportTypeVO.setReportTypeNo(reportTypeNo);
		reportVO.setReportTypeVO(reportTypeVO);
		// 신고 내용
		reportVO.setReportContents(reportContents);
		// 신고 데이터 저장(리뷰 No, 평점 No 확인)
		if(Integer.toString(reviewVO.getReviewNo()).contentEquals("0")) {
			reportVO.setCommentsVO(commentsVO);
			reportService.sReportWriteComments(reportVO);
		}else {
			reportVO.setReviewVO(reviewVO);
			reportService.sReportWriteReview(reportVO);
		}
		// 트랜잭션 처리후 수정이 필요함
		return "report/report_ok";
	}
	
	
	// 삭제버튼으로 삭제(ajax방식 / adminReportList.jsp)
	@PostMapping("reportDeleteAjax.do")
	@ResponseBody
	public ReportListVO reportDeleteAjax(int reportNo, String pageNo, boolean reportType) {
		reportService.sReportDelete(reportNo);
		// reportType(true: comments / false: review)
		if(reportType) {
		System.out.println(reportType);
		return reportService.sGetReportCommentsList(pageNo);
		} else {
			System.out.println(reportType);
		return reportService.sGetReportReviewList(pageNo);
		}
	}
}



























