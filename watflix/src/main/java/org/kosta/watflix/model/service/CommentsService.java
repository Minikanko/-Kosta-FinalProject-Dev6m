package org.kosta.watflix.model.service;

import java.util.List;

import org.kosta.watflix.model.vo.CommentsListVO;
import org.kosta.watflix.model.vo.CommentsVO;
import org.kosta.watflix.model.vo.GenreVO;

public interface CommentsService {

	int sCommentsGetTotalPostCount();

	int sCommentsGetTotalPostCountByContentNo(String contentsNo);

	CommentsListVO sCommentsGetList();

	CommentsListVO sCommentsGetList(String pageNo);

	CommentsListVO sCommentsGetListByContentsNo(String contentsNo);

	CommentsListVO sCommentsGetListByContentsNo(String pageNo, String contentsNo);
	
	void sCommentsWrite(CommentsVO commentsVO);
	
	void sCommentsDelete(int commentsNo);
	
}