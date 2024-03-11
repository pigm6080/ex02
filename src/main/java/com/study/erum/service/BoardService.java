package com.study.erum.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.study.erum.dto.BoardDTO;
import com.study.erum.dto.PageDTO;
import com.study.erum.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	
	public int save(BoardDTO boardDTO) {
		System.out.println("Service Dto : " + boardDTO);
		return boardRepository.save(boardDTO);
	}
	
	public List<BoardDTO> findAll(){
		return boardRepository.findAll();
	}
	public BoardDTO findById(Long id) {
		return boardRepository.findById(id);
	}
	public void updateHits(Long id) {
		boardRepository.updateHits(id);
	}
	public void delete(Long id) {
		boardRepository.delete(id);
	}
	public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }
	int pageLimit = 3;
	int blockLimit = 3;
	public List<BoardDTO>pageList(int page){
		int pagingStart = (page-1) * pageLimit;
		Map<String , Integer> pagingParams = new HashMap<String, Integer>();
		pagingParams.put("start",pagingStart);
		pagingParams.put("limit", pageLimit);
		List<BoardDTO> paingList = boardRepository.pagingList(pagingParams);
		
		return paingList;
	}
	
	public PageDTO pagingParam(int page) {
		int boardCount = boardRepository.boardCount();
		int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
	}
	
}
