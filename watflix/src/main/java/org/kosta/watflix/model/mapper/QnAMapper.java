package org.kosta.watflix.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosta.watflix.model.service.PagingBean;
import org.kosta.watflix.model.vo.QnAAnswerVO;
import org.kosta.watflix.model.vo.QnAVO;

@Mapper
public interface QnAMapper {
	// QnA 작성
	void mQnAWrite(QnAVO qnaVO);
	// QnA 상세보기
	QnAVO mQnADetail(int QnANo);
	// QnA 리스트
	List<QnAVO> mGetQnAList(PagingBean pagingBean);
	// Qna 리스트
	List<QnAVO> mGetQnAListById(@Param("id") String id, @Param("pagingBean") PagingBean pagingBean);
	// QnA 삭제
	void mQnADelete(int qnaNo);
	// QnA 갯수 불러오기
	int mGetTotalQnACount();
	// QnA 답변 작성
	void mQnAAnswerWrite(QnAAnswerVO qnaAnswerVO);
	// 각 QnA 별 QnAAnswer 수 조회
	int mQnAAnswerCountByQnANo(int qnaNo);
	// 각 QnA 별 QnAAnswer 조회
	List<QnAAnswerVO> mQnAAnswerByQnANo(@Param("qnaNo")int qnaNo, @Param("pagingBean")PagingBean pagingBean);
}
