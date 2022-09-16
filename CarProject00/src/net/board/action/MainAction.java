package net.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class MainAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MainAction execute()");
		// �뵒鍮� 媛앹껜 �깮�꽦 BoardDAO  bdao
		BoardDAO bdao=new BoardDAO();
		// int count =  硫붿꽌�뱶�샇異�  getBoardCount()   count(*)
		int count=bdao.getBoardCount();
		String pageNum="1";
		List<BoardBean> boardList=null;
		//  �떆�옉�뻾踰덊샇 1踰�     5媛� 媛��졇�삤湲�
	    // boardList=硫붿꽌�뱶�샇異�  getBoardList(�떆�옉�뻾startRow,紐뉕컻pageSize)
		if(count!=0){
			boardList=bdao.getBoardList(1, 5);
		}
		// ���옣 count   boardList   pageNum
		request.setAttribute("count", count);
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageNum", pageNum);
		// �씠�룞 ./main/main.jsp
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./main/main.jsp");
		return forward;
	}
}
