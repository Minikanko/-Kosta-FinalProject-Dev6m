package org.kosta.watflix.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosta.watflix.model.service.PagingBean;
import org.kosta.watflix.model.vo.Authority;
import org.kosta.watflix.model.vo.MemberVO;
import org.kosta.watflix.model.vo.ReviewVO;
@Mapper
public interface ReviewMapper {
	//리뷰작성
	void mReviewWrite(ReviewVO reviewVO);
	//리뷰수정
	void mReviewUpdate(ReviewVO reviewVO);
	//총리뷰개수
	int mGetTotalReviewCount();
	// 특정 컨텐츠에 대한 리뷰개수
	int mGetContentsReviewCount(String contentsNo);
	//리뷰리스트 with 페이징
	List<ReviewVO> mGetReviewList(PagingBean pagingBean);
	// 특정 컨텐츠에 대한 리뷰리스트
	List<ReviewVO> mGetReviewListByContentsNo(@Param("pagingBean")PagingBean pagingBean, @Param("contentsNo")String contentsNo);
	//리뷰상세보기
	ReviewVO mGetReviewDetail(int reviewNo);
	//리뷰 삭제
	void mReviewDelete(int reviewNo);
	//리뷰 조회수 업데이트
	void mReviewHitsUpdate(int reviewNo);
	//리뷰 추천수 업데이트
	void mReviewLikesUpdate(int reviewNo);
	//리뷰 추천수 감소
	void mReviewLikesRemove(int reviewNo);
	

}




