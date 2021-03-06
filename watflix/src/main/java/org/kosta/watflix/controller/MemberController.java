package org.kosta.watflix.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.kosta.watflix.model.service.CommentsService;
import org.kosta.watflix.model.service.MemberService;
import org.kosta.watflix.model.service.PagingBean;
import org.kosta.watflix.model.service.PointHistoryService;
import org.kosta.watflix.model.service.ProductOrderService;
import org.kosta.watflix.model.service.ReportService;
import org.kosta.watflix.model.service.ReviewService;
import org.kosta.watflix.model.vo.CommentsListVO;
import org.kosta.watflix.model.vo.MemberVO;
import org.kosta.watflix.model.vo.PointHistoryListVO;
import org.kosta.watflix.model.vo.PointHistoryVO;
import org.kosta.watflix.model.vo.ProductOrderListVO;
import org.kosta.watflix.model.vo.ProductOrderVO;
import org.kosta.watflix.model.vo.ReportListVO;
import org.kosta.watflix.model.vo.ReviewListVO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.management.snmp.jvmmib.EnumJvmMemoryGCVerboseLevel;

@Controller
public class MemberController {
	
	@Resource
	MemberService memberService;
	
	@Resource
	ReviewService reviewService;
	@Resource
	CommentsService commentsService;
	
	@Resource
	PointHistoryService pointHistoryService;
	
	@Resource
	ProductOrderService productOrderService;
	
	@Resource
	ReportService reportService;
	
	@RequestMapping("loginForm.do")
	public String loginForm() {
		return "loginForm.tiles";
	}
	
	@RequestMapping("accessDeniedView.do")
	public String accessDeniedView(){
		return "member/accessDeniedView";
	}
	
	//로그인 실패
	@RequestMapping("login_fail.do")
	public String loginFail() {
		return "member/login_fail";
	}
	
	//로그인 후 안내페이지
	@Secured("ROLE_MEMBER")
	@RequestMapping("login_result.do")
	public String login() {
		MemberVO memberVO = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		//휴먼계정인지 확인
		if(memberVO.getAccStatusVO().getAccStatusNo()==1) {
			//정상계정
			memberService.sMemberStatusUpdate(memberVO.getId(), 0);
			return "member/login_inactive";
		}
		else {
			return "member/login_result";
		}
	}
	
	//로그아웃 후 안내페이지
	@RequestMapping("logout_result.do")
	public String logout() {
		return "member/logout_result";
	}
	//회원가입폼(이용약관) 이동
	@RequestMapping("memberRegisterFormTerms.do")
	public String memberRegisterFormTerms(HttpSession session){
		session.setAttribute("terms", "yes");
		return "member/registerFormTerms.tiles";
	}
	//회원가입폼 이동
	@RequestMapping("memberRegisterForm.do")
	public String memberRegisterForm(String IsSelect, Model model,HttpSession session){
		if(session.getAttribute("terms")!=null){
			System.out.println(session.getAttribute("terms"));
			//마케팅 수신동의 여부
			model.addAttribute("ISselectMarketing",IsSelect);
			return "member/registerForm.tiles";
		}
		else {
			return "member/wrong_access";
		}
	}
	//회원가입
	@PostMapping("memberRegister.do")
	public String memberRegister(MemberVO memberVO,HttpSession session){
		memberService.sMemberRegister(memberVO);
		session.invalidate();
		System.out.println("회원가입완료");
		return "redirect:memberRegister_result.do?id="+memberVO.getId();
	}
	//회원가입 후 안내페이지
	@RequestMapping("memberRegister_result.do")
	public String memberRegisterResult(MemberVO memberVO,Model model) {
		model.addAttribute("id",memberVO.getId());
		return "member/memberRegister_result";
	}
	//아이디 중복체크
	@RequestMapping("memberIdCheck.do")
	@ResponseBody
	public String memberIdCheck(String id) {
		if(memberService.idcheck(id)!=null) {
			return "yes";
		}
		else {
			return "no";
		}
	}
	
	//회원정보수정폼으로 이동
	@Secured("ROLE_MEMBER")
	@RequestMapping("memberUpdateForm.do")
	public String memberUpdateForm() {
		return "member/updateForm.tiles";
	}
	
	//회원정보 수정
	@Secured("ROLE_MEMBER")
	@PostMapping("memberUpdate.do")
	public String memberUpdate(MemberVO memberVO) {
		memberService.sMemberUpdate(memberVO);
		// 회원정보 수정위해 Spring Security 세션 회원정보를 반환받는다
		MemberVO pvo = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pvo.setPassword(memberVO.getPassword());
		pvo.setName(memberVO.getName());
		pvo.setTel(memberVO.getTel());
		pvo.setEmail(memberVO.getEmail());
		pvo.setAddress(memberVO.getAddress());
		pvo.setAgreement(memberVO.getAgreement());
		pvo.setSex(memberVO.getSex());
		pvo.setBirth(memberVO.getBirth());
		return "redirect:update_result.do?id="+memberVO.getId();
	}
	//회원정보 수정 결과
	@Secured("ROLE_MEMBER")
	@RequestMapping("update_result.do")
	public String memberUpdateResult(MemberVO memberVO,Model model) {
		model.addAttribute("id",memberVO.getId());
		return "member/update_result";
	}
	
	//회원정보 탈퇴
	@Secured("ROLE_MEMBER")
	@RequestMapping("memberLeave.do")
	public String memberLeave(Model model) {
		MemberVO memberVO = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberService.sMemberLeave(memberVO.getId());
		model.addAttribute("id",memberVO.getId());
		return "member/leave_result";
	}
	
	/*이용약관동의 start*/
	@RequestMapping("watflixMustAgree.do")
	public String watflixMustAgree() {
		return "member/watflixMustAgree";
	}
	@RequestMapping("watflixMustPersonal.do")
	public String watflixMustPersonal() {
		return "member/watflixMustPersonal";
	}
	@RequestMapping("watflixSelectMarketing.do")
	public String watflixSelectMarketing() {
		return "member/watflixSelectMarketing";
	}
	/*이용약관동의 end*/
	
	// 내 게시물 리스트
	@Secured("ROLE_MEMBER")
	@RequestMapping("myPostList.do")
	public String MyPostList(Model model, String pageNo) {
		System.out.println("myPostList.do 실행");
		MemberVO mvo = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("commentsListVO", commentsService.sMyCommentsGetList(mvo.getId(), pageNo));
		model.addAttribute("reviewListVO",reviewService.sGetMyReviewList(mvo.getId(), pageNo));
		return "my_post_list.tiles";
	}	
	
	// 내 리뷰 리스트 Ajax
	@Secured("ROLE_MEMBER")
	@RequestMapping("myReviewList.do")
	@ResponseBody
	public ReviewListVO myReviewList(String pageNo) {
		MemberVO mvo = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return reviewService.sGetMyReviewList(mvo.getId(), pageNo);
	}
	

	// 내 Comments 리스트 Ajax
	@Secured("ROLE_MEMBER")
	@RequestMapping("myCommentsList.do")
	@ResponseBody
	public CommentsListVO myCommentsList(String pageNo) {
		MemberVO mvo = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return commentsService.sMyCommentsGetList(mvo.getId(), pageNo);
	}
	
	// 내 신고 리스트
	// ResponseBody는 비동기 통신에 필요한 어노테이션이다.
	@RequestMapping("myReportBoard.do")
	@ResponseBody
	public ReportListVO myReportReviewBoard(String reportPageNo, boolean reportType) {
		System.out.println("myReportBoard.do 실행");
		MemberVO mvo = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id = mvo.getId();
		if(reportType) {
			return reportService.sGetMyReportCommentsList(id, reportPageNo);
		} else {
			return reportService.sGetMyReportReviewList(id, reportPageNo);
		}
	}

	//포인트사용내역 조회
	@Secured("ROLE_MEMBER")
	@RequestMapping("memberPointHistoryCheck.do")
	public String memberPointHistoryCheck(Model model,String pageNo) {
		MemberVO mvo = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PagingBean pagingBean=null;
		int totalMemberSelectPoint = pointHistoryService.sProductHistoryGetTotalCount(mvo.getId());
		System.out.println("총 포인트사용내역 건수"+totalMemberSelectPoint);
		if(pageNo==null) {
			pagingBean = new PagingBean(totalMemberSelectPoint);
		}
		else {
			pagingBean = new PagingBean(totalMemberSelectPoint, Integer.parseInt(pageNo));
		}
		
		List<PointHistoryVO> list = pointHistoryService.sMemberPointHistoryCheck(mvo.getId(),pagingBean);
		model.addAttribute("pointHistoryListVO",new PointHistoryListVO(list,pagingBean));
		return "member/pointHistoryCheck.tiles";
	}
	
	//포인트조회
	@Secured("ROLE_MEMBER")
	@PostMapping("memberPointCheck.do")
	@ResponseBody
	public MemberVO memberPointCheck() {
		MemberVO mvo = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//세션에 포인트값을 갱신함
		MemberVO smvo = memberService.sFindMemberById(mvo.getId());
		mvo.setPoint(smvo.getPoint());
		return mvo;
	}
	
	//구매내역조회
	@Secured("ROLE_MEMBER")
	@RequestMapping("memberProductOrderList.do")
	public String memberProductOrderList(String pageNo,Model model) {
		MemberVO mvo = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//구매내역 총건수
		int productOrderCount = productOrderService.sProductOrderCount(mvo.getId());
		PagingBean pagingBean;
		
		if(pageNo==null) {
			pagingBean = new PagingBean(productOrderCount);
		}
		else {
			pagingBean = new PagingBean(productOrderCount, Integer.parseInt(pageNo));
		}
		//id를 가지고 구매내역 조회
		List<ProductOrderVO> list = productOrderService.sProductOrderList(pagingBean,mvo.getId());
		ProductOrderListVO polvo = new ProductOrderListVO(list, pagingBean);
		model.addAttribute("productOrderListVO",polvo);
		return "member/productOrderList.tiles";
	}
		
	//구매내역 상세조회
	@Secured("ROLE_MEMBER")
	@RequestMapping("productOrderDetail.do")
	public String productOrderDetail(ProductOrderVO productOrderVO,Model model) {
		//security에서 세션값 불러옴
		MemberVO mvo = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("productOrderDetail",productOrderService.sProductOrderDetail(mvo.getId(),productOrderVO.getOrderNo()));
		return "member/productOrderDetail.tiles";
	}
}
