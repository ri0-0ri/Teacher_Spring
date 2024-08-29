package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.dto.BoardDTO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	// 여러 요청 주소를 하나의 메소드에 매핑하는 방법
	@GetMapping(value = { "writeview" })
	public void replace() {
	}

	// 데이터 수집 -> 처리 -> 응답 생성 및 응답기
	@PostMapping("WriteOk")
	public String WriteOk(BoardDTO board, HttpServletResponse resp) throws Exception {
		// DB처리
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

		if (result == 1) {
			// redirect 붙이면 사용자가 요청을 다시 보냄(쿠키 변화 등등도 함께)
			Cookie cookie = new Cookie("w", "t");
			cookie.setMaxAge(5);
			cookie.setPath("/");
			resp.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("w", "f");
			cookie.setMaxAge(5);
			cookie.setPath("/");
			resp.addCookie(cookie);
		}

		// forward(길 찾는 사람 손 잡고 함께 가기)
		// ex) return "/user/main";
		// /user/main.jsp 를 해석해서 바로 응답

		// redirect(길 찾는 사람에게 가는 방법 알려주기)
		// ex) return "redirect:/user/main";
		// /user/main 으로 재요청 유도

		return "redirect:/user/main";
	}

	// 데이터 수집 -> 처리 -> 응답 생성 및 응답하기
	@GetMapping("getview")
	public void getview(int boardnum, Model model) throws Exception { // 매개변수 선언 = 수집
		// DB처리
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/gb";
		String user = "root";
		String password = "1234";

		Connection conn = DriverManager.getConnection(url, user, password);

		String sql = "select * from test_board where boardnum = " + boardnum;
		;

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			BoardDTO board = new BoardDTO();
			board.setBoardnum(rs.getInt("boardnum"));
			board.setBoardtitle(rs.getString("boardtitle"));
			board.setBoardcontents(rs.getString("boardcontents"));
			board.setRegdate(rs.getString("regdate"));
			board.setUserid(rs.getString("userid"));

			model.addAttribute("board", board);
		}
	}

	@GetMapping("getDetail")
	@ResponseBody
	public BoardDTO getDetail(int boardnum) throws Exception {
		// DB처리
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/gb";
		String user = "root";
		String password = "1234";

		Connection conn = DriverManager.getConnection(url, user, password);

		String sql = "select * from test_board where boardnum = " + boardnum;
		;

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			BoardDTO board = new BoardDTO();
			board.setBoardnum(rs.getInt("boardnum"));
			board.setBoardtitle(rs.getString("boardtitle"));
			board.setBoardcontents(rs.getString("boardcontents"));
			board.setRegdate(rs.getString("regdate"));
			board.setUserid(rs.getString("userid"));

			return board;
		}
		return null;
	}

	@GetMapping("modifyview")
	public void modifyview(int boardnum, Model model) throws Exception { // 매개변수 선언 = 수집
		// DB처리
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/gb";
		String user = "root";
		String password = "1234";

		Connection conn = DriverManager.getConnection(url, user, password);

		String sql = "select * from test_board where boardnum = " + boardnum;
		;

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			BoardDTO board = new BoardDTO();
			board.setBoardnum(rs.getInt("boardnum"));
			board.setBoardtitle(rs.getString("boardtitle"));
			board.setBoardcontents(rs.getString("boardcontents"));
			board.setRegdate(rs.getString("regdate"));
			board.setUserid(rs.getString("userid"));

			model.addAttribute("board", board);
		}
	}

	@PostMapping("ModifyOk")
	public String ModifyOk(BoardDTO board, HttpServletResponse resp) throws Exception {
		// DB처리
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/gb";
		String user = "root";
		String password = "1234";

		Connection conn = DriverManager.getConnection(url, user, password);

		String sql = "update test_board set boardtitle=?, boardcontents=? where boardnum=?";

		PreparedStatement ps = conn.prepareStatement(sql);
		
		System.out.println(board.getBoardtitle());
		System.out.println(board.getBoardcontents());
		System.out.println(board.getBoardnum());
		
		ps.setString(1, board.getBoardtitle());
		ps.setString(2, board.getBoardcontents());
		ps.setInt(3, board.getBoardnum());

		int result = ps.executeUpdate();
		
		if (result == 1) {
			Cookie cookie = new Cookie("w", "mt");
			cookie.setMaxAge(5);
			cookie.setPath("/");
			resp.addCookie(cookie);
			return "redirect:/board/getview?boardnum=" + board.getBoardnum();
		} else {
			Cookie cookie = new Cookie("w", "mf");
			cookie.setMaxAge(5);
			cookie.setPath("/");
			resp.addCookie(cookie);
			return "redirect:/user/main";
		}

	}
	
	@GetMapping("DeleteOk")
	public String DeleteOk(int boardnum, HttpServletResponse resp) throws Exception {
		// DB처리
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/gb";
		String user = "root";
		String password = "1234";

		Connection conn = DriverManager.getConnection(url, user, password);

		String sql = "delete from test_board where boardnum=?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, boardnum);

		int result = ps.executeUpdate();
		
		if (result == 1) {
			Cookie cookie = new Cookie("w", "dt");
			cookie.setMaxAge(5);
			cookie.setPath("/");
			resp.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("w", "df");
			cookie.setMaxAge(5);
			cookie.setPath("/");
			resp.addCookie(cookie);
		}
		
		return "redirect:/user/main";
	}
}
