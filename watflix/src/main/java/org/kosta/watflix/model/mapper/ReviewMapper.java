package org.kosta.watflix.model.mapper;

import java.util.HashMap;
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
	//ReviewVO mGetReviewDetail(map);
	ReviewVO mGetReviewDetail(HashMap<String, Object> map);
	//리뷰 삭제
	void mReviewDelete(int reviewNo);
	//리뷰 조회수 업데이트
	void mReviewHitsUpdate(int reviewNo);
	//리뷰 추천수 업데이트
	void mReviewLikesUpdate(int reviewNo);
	//리뷰 추천수 감소
	void mReviewLikesRemove(int reviewNo);
	//내 리뷰리스트 with 페이징
	List<ReviewVO> mGetMyReviewList(@Param("id")String id, @Param("pagingBean")PagingBean pagingBean);
	//내 총리뷰 수
	int mGetMyTotalReviewCount(String id);
	//해당 컨텐츠에 접속한 유저의 아이디로 작성된 review가 있는지 확인
	int mCheckReviewExist(@Param("id")String id, @Param("contentsNo")String contentsNo);
}




