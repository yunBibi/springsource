package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.domain.ReplyPageDTO;
import com.company.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public boolean insertReply(ReplyDTO insertDto) {		
		return replyMapper.insert(insertDto)>0?true:false;
	}

	@Override
	public ReplyDTO getRow(int rno) {		
		return replyMapper.get(rno);
	}

	@Override
	public boolean updateReply(ReplyDTO updateDto) {		
		return replyMapper.update(updateDto)>0?true:false;
	}

	@Override
	public boolean deleteReply(int rno) {		
		return replyMapper.delete(rno)>0?true:false;
	}

	@Override
	public ReplyPageDTO getList(Criteria cri, int bno) {		
		return new ReplyPageDTO(replyMapper.getCountByBno(bno),replyMapper.list(cri, bno));
	}

}

