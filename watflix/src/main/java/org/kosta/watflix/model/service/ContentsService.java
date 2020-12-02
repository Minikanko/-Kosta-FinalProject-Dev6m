package org.kosta.watflix.model.service;


import java.util.List;

import org.kosta.watflix.model.vo.ContentsListVO;
import org.kosta.watflix.model.vo.ContentsVO;

public interface ContentsService {

	ContentsListVO sGetAllContentsList(PagingBean pagingBean);
	int sGetTotalContentsCount();
	int sGetTotalContentsCountForType(String contentsType);
	ContentsListVO sGetAllContentsListForType(PagingBean pagingBean);
	List<ContentsVO> sContentsHighHits();
	List<ContentsVO> sContentsHighAvgStars();

}