package org.kosta.watflix.model.mapper;

import java.util.HashMap;
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
	List<ContentsVO> mGetContentsAllForTypeLogin(@Param("map")Map<String,String> map,@Param("id")String id);
	List<ContentsVO> mContentsHighHits();
	List<ContentsVO> mContentsHighCommentsCount();
	List<ContentsVO> mContentsHighCommentsCountLogin(String id);
	List<ContentsVO> mContentsHighHitsLogin(String id);
	List<ContentsVO> mContentsHighAvgStars();
	List<ContentsVO> mContentsHighAvgStarsLogin(String id);
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
	//컨텐츠 검색
	List<GenreVO> mGenreSelectForTitle(String contentsTitle);
	List<ContentsVO> mGetContentsSelectForTitle(String contentsTitle);
	List<ContentsVO> mGetAllContentsListSortByNewLogin(@Param("map")Map<String, String> map, @Param("id")String id);
	List<ContentsVO> mGetAllContentsListSortByOldLogin(@Param("map")Map<String, String> map, @Param("id")String id);
	List<ContentsVO> mGetAllContentsForGenreListSortByNewLogin(@Param("map")Map<String, String> map, @Param("id")String id);
	List<ContentsVO> mGetAllContentsForGenreListSortByOldLogin(@Param("map")Map<String, String> map, @Param("id")String id);
	List<ContentsVO> mGetContentsAllForTypeAndGenreLogin(@Param("map")Map<String, String> map, @Param("id")String id);
}