package org.kosta.watflix.controller;

import javax.annotation.Resource;

import org.kosta.watflix.model.service.CommentsService;
import org.kosta.watflix.model.service.ContentsService;
import org.kosta.watflix.model.service.ReviewService;
import org.kosta.watflix.model.vo.ContentsVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContentsController {

	@Resource
	ContentsService contentsService;
	@Resource
	ReviewService reviewService;
	@Resource
	CommentsService commentsService;
	
	@RequestMapping("contentsDetail.do")
//<<<<<<< HEAD
	public String ContentsDetail(Model model, ContentsVO contentsVO, String pageNo) {
		//CommentsListByContentsNo in ContentsDetail
		model.addAttribute("contentsVO", contentsService.sFindContentsByNo(contentsVO.getContentsNo()));
		model.addAttribute("commentsListByContentsNo", commentsService.sCommentsGetListByContentsNo(pageNo, contentsVO.getContentsNo()));
		//ReviewListByContentsNo in ContentsDetail
		model.addAttribute("reviewListByContentsNo",reviewService.sGetReviewListByContentsNo(pageNo, contentsVO.getContentsNo()));
		model.addAttribute("contentsNo", contentsVO.getContentsNo());
		return "c_commentsList_reviewList.tiles";
//=======

	//public ModelAndView ContentsDetail(ContentsVO contentsVO, String commentsPageNo, String reviewPageNo) {
		//ModelAndView modelAndView = new ModelAndView();
		//modelAndView.setViewName("contents/contents_detail.tiles");
		//modelAndView.addObject("contentsVO", contentsService.sFindContentsByNo(contentsVO.getContentsNo()));
		//modelAndView.addObject("contentsNo", contentsVO.getContentsNo());
		
//		if(commentsPageNo==null)
//			commentsPageNo = "1"; 
//		
//		if(reviewPageNo==null)
//			reviewPageNo = "1";
//		
//		if(commentsPageNo!=null) {
//			modelAndView.addObject("commentsListByContentsNo", commentsService.sCommentsGetListByContentsNo(commentsPageNo, contentsVO.getContentsNo()));
//		} 
//		if(reviewPageNo!=null) {
//			modelAndView.addObject("lvo", reviewService.sGetReviewList(reviewPageNo));
//		}
		//System.out.println(contentsVO.getContentsNo());
		//return modelAndView;
	}
	
	//
	@RequestMapping("contentsByMovie.do")
	public String contentsByMovie(Model model) {
		//네비바를 위한것
		model.addAttribute("navBarTab","MOVIE");
		model.addAttribute("contentsList",contentsService.sGetContentsSelectForType("영화"));
		model.addAttribute("genreList",contentsService.sGetGenreSelectForType("영화"));
		
		return "contents/contentsByMovie.tiles";
	}
	@RequestMapping("contentsByTV.do")
	public String contentsByTV(Model model) {
		//네비바를 위한것
		model.addAttribute("navBarTab","TV");
		model.addAttribute("contentsList",contentsService.sGetContentsSelectForType("TV"));
		model.addAttribute("genreList",contentsService.sGetGenreSelectForType("TV"));
		
		return "contents/contentsByTV.tiles";
//>>>>>>> branch 'master' of https://github.com/Minikanko/-Kosta-FinalProject-Dev6m.git
	}
}
