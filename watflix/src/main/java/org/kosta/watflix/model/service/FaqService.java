package org.kosta.watflix.model.service;

import org.kosta.watflix.model.vo.FaqListVO;
import org.kosta.watflix.model.vo.FaqVO;


public interface FaqService {
	void sFaqWrite(FaqVO faqVO); 
	void sFaqUpdate(FaqVO faqVO);
	void sFaqDelete(FaqVO faqVO);
	FaqVO sFaqDetail(int faqNo);
	int sGetTotalFaqCount();
	FaqListVO sGetFaqList(String pageNo);
	FaqListVO sGetFaqList();
}
