package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.mapper.BoardAttachMapper;
import com.company.mapper.BoardMapper;
import com.company.mapper.ReplyMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private BoardAttachMapper boardAttachMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Transactional
	@Override
	public boolean register(BoardDTO insertDto) {
		// 게시물 등록
		boolean result = mapper.insert(insertDto)>0?true:false;
				
		// 첨부파일 등록
		if(insertDto.getAttachList() == null || insertDto.getAttachList().size() <= 0) {
			return false;
		}
		
		insertDto.getAttachList().forEach(attach -> {
			attach.setBno(insertDto.getBno());
			boardAttachMapper.insert(attach);
		});
		
		return result;
	}


	@Override
	public List<BoardDTO> getList(Criteria cri) {		
		return mapper.listAll(cri);
	}


	@Override
	public BoardDTO getRow(int bno) {
		return mapper.read(bno);
	}

	@Transactional
	@Override
	public boolean update(BoardDTO updateDto) {
		
		// 기존 첨부파일 삭제
		boardAttachMapper.deleteAll(updateDto.getBno());
		// 게시물 수정
		boolean modifyResult = mapper.update(updateDto) == 1;
		
		// 첨부물이 없다면 돌아가기
		if(updateDto.getAttachList() == null || updateDto.getAttachList().size() <= 0) {
			return modifyResult;
		}
		
		// 첨부물이 있는 경우
		if(modifyResult && updateDto.getAttachList().size() > 0) {
			updateDto.getAttachList().forEach(attach -> {
				attach.setBno(updateDto.getBno());
				boardAttachMapper.insert(attach);
			});
		}
		
		return modifyResult;
	}

	@Transactional
	@Override
	public boolean remove(int bno) {
		// 댓글 삭제
		replyMapper.deleteAll(bno);
		
		// 첨부물 삭제
		boardAttachMapper.deleteAll(bno);
		
		return mapper.delete(bno)>0?true:false;
	}


	@Override
	public int getTotalCount(Criteria cri) {
		return mapper.totalCnt(cri);
	}


	@Override
	public List<AttachFileDTO> findByBno(int bno) {
		return boardAttachMapper.read(bno);
	}


	@Override
	public boolean attachRemove(int bno) {
		return boardAttachMapper.deleteAll(bno) == 1;
	}

}







