package org.kosta.watflix.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kosta.watflix.model.service.MemberService;
import org.kosta.watflix.model.vo.MemberVO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	
	@Resource
	MemberService memberService;
	
	@RequestMapping("loginForm.do")
	public String loginForm() {
		return "loginForm.tiles";
	}
	
	//로그인 실패
	@RequestMapping("login_fail.do")
	public String loginFail() {
		return "member/login_fail";
	}
	
	//로그인 후 안내페이지
	@RequestMapping("login_result.do")
	public String login() {
		return "member/login_result";
	}
	
	//로그아웃 후 안내페이지
	@RequestMapping("logout_result.do")
	public String logout() {
		return "member/logout_result";
	}
	//회원가입폼(이용약관) 이동
	@RequestMapping("memberRegisterFormTerms.do")
	public String memberRegisterFormTerms(){
		return "member/registerFormTerms.tiles";
	}
	//회원가입폼 이동
	@RequestMapping("memberRegisterForm.do")
	public String memberRegisterForm(String IsSelect, Model model){
		//마케팅 수신동의 여부
		model.addAttribute("ISselectMarketing",IsSelect);
		return "member/registerForm.tiles";
	}
	//회원가입
	@RequestMapping("memberRegister.do")
	public String memberRegister(MemberVO memberVO){
		memberService.sMemberRegister(memberVO);
		return "redirect:memberRegister_result.do?id="+memberVO.getId();
	}
	//회원가입 후 안내페이지
	@RequestMapping("memberRegister_result.do")
	public String memberRegisterResult(MemberVO memberVO,Model model) {
		model.addAttribute("id",memberVO.getId());
		return "member/memberRegister_result";
	}
	//아이디 중복체크
	@ResponseBody
	@RequestMapping("memberIdCheck.do")
	public String memberIdCheck(String id) {
		return memberService.idcheck(id);
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
}
