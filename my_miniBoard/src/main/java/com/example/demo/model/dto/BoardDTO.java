package com.example.demo.model.dto;

import lombok.Data;

@Data //게터세터 꼭 필요함
public class BoardDTO {
	// 수집할때 세터로 수집하기 때문에 무조건 세터 필요함
	// 
	private int boardnum;
	private String boardtitle;
	private String boardcontents;
	private String regdate;
	private String userid;
}
