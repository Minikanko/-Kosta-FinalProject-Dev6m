package org.kosta.watflix.model.service;

import javax.annotation.Resource;

import org.kosta.watflix.model.mapper.FaqMapper;
import org.kosta.watflix.model.vo.FaqListVO;
import org.kosta.watflix.model.vo.FaqVO;
import org.springframework.stereotype.Service;
@Service
public class FaqServiceImpl implements FaqService {

	@Resource
	private FaqMapper faqMapper;
	
	//FAQ 작성
	@Override
	public void sFaqWrite(FaqVO faqVO) {
		// TODO Auto-generated method stub
		faqMapper.mFaqWrite(faqVO);
		
	}

	@Override
	public void sFaqUpdate(FaqVO faqVO) {
		// TODO Auto-generated method stub
		faqMapper.mFaqUpdate(faqVO);
		
	}

	@Override
	public FaqVO sFaqDetail(int faqNo) {
		// TODO Auto-generated method stub
		return faqMapper.mFaqDetail(faqNo);
	}

	@Override
	public FaqListVO sGetFaqList(String pageNo) {
		int totalPostCount = faqMapper.mGetTotalFaqCount();
		PagingBean pagingBean=null;
		if(pageNo==null) {
			pagingBean = new PagingBean(totalPostCount);
		}else {
			pagingBean = new PagingBean(totalPostCount,Integer.parseInt(pageNo));
		}
		FaqListVO faqListVO=new FaqListVO(faqMapper.mGetFaqList(pagingBean),pagingBean);
		return faqListVO;
	}

	@Override
	public int sGetTotalFaqCount() {
		return faqMapper.mGetTotalFaqCount();
	}

	@Override
	public FaqListVO sGetFaqList() {
		return sGetFaqList("1");
	}

	@Override
	public void sFaqDelete(int faqNo) {
			faqMapper.mFaqDelete(faqNo);
	}

	@Override
	public void sFaqUpdateHits(int faqNo) {
			faqMapper.mFaqUpdateHits(faqNo);
	}

	@Override
	public FaqVO sFaqDetailNoHits(int faqNo) {
		return faqMapper.mFaqDetail(faqNo);
	}

}
