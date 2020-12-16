package org.kosta.watflix.model.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.watflix.model.vo.ContentsLikeVO;
import org.kosta.watflix.model.vo.MemberVO;
@Mapper
public interface ContentsLikeMapper {
	
	//컨텐츠좋아요 여부
	int mContentsLikeExist(ContentsLikeVO contentsLikeVO);
	//컨텐츠좋아요++
	void mContentsLikeAdd(ContentsLikeVO contentsLikeVO);
	//컨텐츠좋아요 제거
	void mContentsLikeRemove(ContentsLikeVO contentsLikeVO);
	//컨텐츠좋아요 개수
	int mGetContentsLikeCount();
	int mIsLike(HashMap<String, String> map);
}
