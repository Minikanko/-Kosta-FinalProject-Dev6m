package org.kosta.watflix.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosta.watflix.model.vo.ContentsVO;
import org.kosta.watflix.model.vo.GenreVO;
@Mapper
public interface ContentsMapper {

	void mGenreRegister(GenreVO gvo);
	void mContentsRegister(ContentsVO cvo);
	int mFindGenreByCode(String genreCode);
	ContentsVO mFindContentsByNo(String contentsNo);
	int mGetTotalContentsCount();
	List<ContentsVO> mGetAllContentsList();
	List<ContentsVO> mGetContentsAllForTypeAndGenre(Map<String,String> map);
	List<ContentsVO> mGetContentsAllForType(Map<String,String> map);
	List<ContentsVO> mContentsHighHits();
	List<ContentsVO> mContentsHighCommentsCount();
	List<ContentsVO> mContentsHighHitsLogin();
	List<ContentsVO> mContentsHighAvgStars();
	List<ContentsVO> mContentsHighAvgStarsLogin();
	List<GenreVO> mGetAllGenreList();
	List<GenreVO> mGetGenreSelectForType(String contentsType);
	List<ContentsVO> mGetContentsSelectForType(String contentsType);
	//컨텐츠 추천수 업데이트
	void mContentsLikeUpdate(String contentsNo);
	//컨텐츠 추천수 감소
	void mContentsLikeRemove(String contentsNo);
	//각 content에 contents_avg_stars 넣기
	void mUpdateAvgStars(@Param("avgStars")double avgStars, @Param("contentsNo")String contentsNo);
	List<ContentsVO> mGetAllContentsListSortByNew(Map<String, String> map);
	List<ContentsVO> mGetAllContentsListSortByOld(Map<String, String> map);
	List<ContentsVO> mGetAllContentsForGenreListSortByNew(Map<String, String> map);
	List<ContentsVO> mGetAllContentsForGenreListSortByOld(Map<String, String> map);
}




