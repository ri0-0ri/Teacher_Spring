package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.dto.BoardDTO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	// 여러 요청 주소를 하나의 메소드에 매핑하는 방법
	@GetMapping(value = {"writeview"})
	public void replace() {}
	
	// 데이터 수집 -> 처리 -> 응답 생성 및 응답기
	@PostMapping("WriteOk")
	public String joinOk(BoardDTO board, HttpServletResponse resp) throws Exception{
		//DB처리
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/gb";
		String user = "root";
		String password = "1234";
		
		Connection conn = DriverManager.getConnection(url, user, password);
		
		String sql = "insert into test_board(boardtitle, boardcontents, userid) values(?,?,?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, board.getBoardtitle());
		ps.setString(2, board.getBoardcontents());
		ps.setString(3, board.getUserid());
		
		int result = ps.executeUpdate();
		
		if(result == 1) {
			// redirect 붙이면 사용자가 요청을 다시 보냄(쿠키 변화 등등도 함께)
			Cookie cookie = new Cookie("w", "t");
			cookie.setMaxAge(30);
			cookie.setPath("/");
			resp.addCookie(cookie);
		}
		else {
			Cookie cookie = new Cookie("w", "f");
			cookie.setMaxAge(30);
			cookie.setPath("/");
			resp.addCookie(cookie);
		}
		
		//forward(길 찾는 사람 손 잡고 함께 가기)
		// ex) return "/user/main";
		//		/user/main.jsp 를 해석해서 바로 응답
		
		//redirect(길 찾는 사람에게 가는 방법 알려주기)
		// ex) return "redirect:/user/main";
		//		/user/main 으로 재요청 유도
		
		return "redirect:/user/main";
	}
}

