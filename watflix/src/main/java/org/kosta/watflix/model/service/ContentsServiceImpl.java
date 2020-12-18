package org.kosta.watflix.model.service;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.kosta.watflix.model.mapper.ContentsMapper;
import org.kosta.watflix.model.mapper.ReviewMapper;
import org.kosta.watflix.model.vo.ContentsVO;
import org.kosta.watflix.model.vo.GenreVO;
import org.springframework.stereotype.Service;

@Service
public class ContentsServiceImpl implements ContentsService {
	
	@Resource
	ContentsMapper contentsMapper;
	@Resource
	ReviewMapper reviewMapper;
	
	//컨텐츠 총 개수
	@Override
	public int sGetTotalContentsCount() {
		return contentsMapper.mGetTotalContentsCount();
	}

	//모든 컨텐츠 리스트
	@Override
	public List<ContentsVO> sGetAllContentsList() {
		return contentsMapper.mGetAllContentsList();
	}
	
	//장르&타입의 컨텐츠 리스트
	@Override
	public List<ContentsVO> sGetContentsAllForTypeAndGenre(Map<String,String> map) {
		return contentsMapper.mGetContentsAllForTypeAndGenre(map);
	}
	//특정 타입의 컨텐츠 리스트
	@Override
	public List<ContentsVO> sGetContentsAllForType(Map<String,String> map) {
		return contentsMapper.mGetContentsAllForType(map);
	}
	//특정 타입의 컨텐츠 리스트 with Login
	@Override
	public List<ContentsVO> sGetContentsAllForTypeLogin(Map<String,String> map,String id) {
		return contentsMapper.mGetContentsAllForTypeLogin(map,id);
	}
	 
	//조회수 높은순
	@Override
	public List<ContentsVO> sContentsHighHits() {
		return contentsMapper.mContentsHighHits();
	}
	
	//조회수 높은순 with Login
	@Override
	public List<ContentsVO> sContentsHighHitsLogin(String id) {
		return contentsMapper.mContentsHighHitsLogin(id);
	}
	
	//평점 높은순
	@Override
	public List<ContentsVO> sContentsHighAvgStars() {
		return contentsMapper.mContentsHighAvgStars();
	}
	
	//평점 높은순 with Login
	@Override
	public List<ContentsVO> sContentsHighAvgStarsLogin(String id) {
		return contentsMapper.mContentsHighAvgStarsLogin(id);
	}
	
	//장르 출력 리스트
	@Override
	public List<GenreVO> sGetAllGenreList() {
		return contentsMapper.mGetAllGenreList();
	}
	//컨텐츠 상세보기
	@Override
	public ContentsVO sFindContentsByNo(String contentsNo) {
		ContentsVO contentsVO = contentsMapper.mFindContentsByNo(contentsNo);
		contentsVO.setContentsReviewCount(reviewMapper.mGetContentsReviewCount(contentsNo));
		return contentsVO;
	}
	
	//컨텐츠 타입별 select문
	@Override
	public List<ContentsVO> sGetContentsSelectForType(String contentsType) {
		return contentsMapper.mGetContentsSelectForType(contentsType);
	}
	
	// 컨텐츠 타입별 장르
	@Override
	public List<GenreVO> sGetGenreSelectForType(String contentsType) {
		return contentsMapper.mGetGenreSelectForType(contentsType);
	}

	//컨텐츠추천수증가
	@Override
	public void sContentsLikesUpdate(String contentsNo) {
		contentsMapper.mContentsLikeUpdate(contentsNo);
	}

	//컨텐츠추천수감소
	@Override
	public void sContentsLikeRevmoew(String contentsNo) {
		contentsMapper.mContentsLikeRemove(contentsNo);
	}
	
	//각 content에 contents_avg_stars 넣기
	@Override
	public void sUpdateAvgStar(double avgStars, String contentsNo) {
		contentsMapper.mUpdateAvgStars(avgStars, contentsNo);
	}

	//최다평점순
	@Override
	public List<ContentsVO> sContentsHighCommentsCount() {
		return contentsMapper.mContentsHighCommentsCount();
	}

	//출시일이 최신순인 컨텐츠 리스트
	@Override
	public List<ContentsVO> sGetAllContentsListSortByNew(Map<String, String> map) {
		return contentsMapper.mGetAllContentsListSortByNew(map);
	}
	//출시일이 오래된순인 컨텐츠 리스트
	@Override
	public List<ContentsVO> sGetAllContentsListSortByOld(Map<String, String> map) {
		return contentsMapper.mGetAllContentsListSortByOld(map);
	}

	//특정 장르에서 출시일이 최신순인 컨텐츠 리스트
	@Override
	public List<ContentsVO> sGetAllContentsForGenreListSortByNew(Map<String, String> map) {
		return contentsMapper.mGetAllContentsForGenreListSortByNew(map);
	}
	//특정 장르에서 출시일이 오래된순인 컨텐츠 리스트
	@Override
	public List<ContentsVO> sGetAllContentsForGenreListSortByOld(Map<String, String> map) {
		return contentsMapper.mGetAllContentsForGenreListSortByOld(map);
	}

	//검색된 컨텐츠 장르 출력
	@Override
	public List<GenreVO> sGenreSelectForTitle(String contentsTitle) {
		return contentsMapper.mGenreSelectForTitle(contentsTitle);
	}

	//컨텐츠 검색
	@Override
	public List<ContentsVO> sGetContentsSelectForTitle(String contentsTitle) {
		return contentsMapper.mGetContentsSelectForTitle(contentsTitle);
	}

	//출시일이 최신순인 컨텐츠 리스트 with login
	@Override
	public List<ContentsVO> sGetAllContentsListSortByNewLogin(Map<String, String> map, String id) {
		return contentsMapper.mGetAllContentsListSortByNewLogin(map,id);
	}

	//출시일이 오래된순인 컨텐츠 리스트 with login
	@Override
	public List<ContentsVO> sGetAllContentsListSortByOldLogin(Map<String, String> map, String id) {
		return contentsMapper.mGetAllContentsListSortByOldLogin(map,id);
	}
	//특정 장르에서 출시일이 최신순인 컨텐츠 리스트 with login
	@Override
	public List<ContentsVO> sGetAllContentsForGenreListSortByNewLogin(Map<String, String> map, String id) {
		return contentsMapper.mGetAllContentsForGenreListSortByNewLogin(map,id);
	}
	//특정 장르에서 출시일이 오래된순인 컨텐츠 리스트 with login
	@Override
	public List<ContentsVO> sGetAllContentsForGenreListSortByOldLogin(Map<String, String> map, String id) {
		return contentsMapper.mGetAllContentsForGenreListSortByOldLogin(map,id);
	}
	//장르&타입의 컨텐츠 리스트 with login
	@Override
	public List<ContentsVO> sGetContentsAllForTypeAndGenreLogin(Map<String, String> map, String id) {
		return contentsMapper.mGetContentsAllForTypeAndGenreLogin(map,id);
	}

	//최다평점순 with Login
	@Override
	public List<ContentsVO> sContentsHighCommentsCountLogin(String id) {
		return contentsMapper.mContentsHighCommentsCountLogin(id);
	}
	
}
