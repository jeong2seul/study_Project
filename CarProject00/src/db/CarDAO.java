package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


//而ㅻ꽖�뀡���쓣 �씠�슜�븯�뿬 �뜲�씠�꽣踰좎씠�뒪�뿉 �젒洹쇳븯�뿬 �뜲�씠�꽣瑜� �엯�젰 �닔�젙 �궘�젣 寃��깋�븷�닔 �엳�뒗 DAO�겢�옒�뒪 �꽑�뼵
public class CarDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	/* DB�뿰寃� 硫붿냼�뱶 */
	public void getCon() {
		try {
			// 1.WAS�꽌踰꾩� �뿰寃곕맂 DBApp�쎒�봽濡쒖젥�듃�쓽 紐⑤뱺 �젙蹂대�� 媛�吏�怨� �엳�뒗 而⑦뀓�뒪�듃媛앹껜 �깮�꽦
			Context init = new InitialContext();
			// 2.�뿰寃곕맂 WAS�꽌踰꾩뿉�꽌 DataSource(而ㅻ꽖�뀡��) 寃��깋�빐�꽌 媛��졇�삤湲�
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/jspbeginner");
			// DataSource(而ㅻ꽖�뀡��)�뿉�꽌 DB�뿰�룞媛앹껜 (而ㅻ꽖�뀡) 媛��졇�삤湲�
			con = ds.getConnection(); // DB�뿰寃�

		} catch (Exception err) {
			err.printStackTrace();
		}

	}// getCon()硫붿냼�뱶 �걹
	/* �쟾泥� 李⑤웾 蹂닿린 硫붿냼�뱶瑜� �옉�꽦 */

	public Vector<CarListBean> getAllCarlist() {
		// 由ы꽩�븷 Vector媛앹껜瑜� �꽑�뼵
		Vector<CarListBean> v = new Vector<CarListBean>();
		// �븯�굹�쓽 �젅肄붾뱶瑜� ���옣�븷 媛앹껜 �꽑�뼵
		CarListBean bean = null;

		try {
			// 而ㅻ꽖�뀡 硫붿냼�뱶 �샇異� �븯�뿬 DB�뿰寃곌컼泥� �븯�굹 �뼸湲�
			getCon();// DB�뿰寃�
			// 荑쇰━以�鍮� : �쟾泥� 李⑤웾 �젅肄붾뱶 寃��깋
			String sql = "select * from carlist";
			// 荑쇰━瑜� �떎�뻾�븷�닔�엳�뒗 媛앹껜 �꽑�뼵
			pstmt = con.prepareStatement(sql);
			// 荑쇰━ �떎�뻾�썑 寃곌낵瑜� 由ы꽩
			rs = pstmt.executeQuery();
			// 諛섎났臾몄쓣 �룎硫댁꽌 鍮덊겢�옒�뒪�뿉 而щ읆�뜲�씠�꽣瑜� ���옣
			while (rs.next()) {// Resultset�뿉 �젅肄붾뱶媛� 議댁옱�븷�븣源뚯� 諛섎났
				bean = new CarListBean();
				bean.setCarno(rs.getInt(1)); // 李⑤쾲�샇 �떞湲�
				bean.setCarname(rs.getString(2)); // 李⑥씠由� �떞湲�
				bean.setCarcompany(rs.getString(3));// 李⑥젣議곗궗
				bean.setCarprice(rs.getInt(4));// 李④�寃� �떞湲�
				bean.setCarusepeople(rs.getInt(5));// 李⑥씤�듅 �떞湲�
				bean.setCarinfo(rs.getString(6));// 李⑥젙蹂� �떞湲�
				bean.setCarimg(rs.getString(7));// 李⑥씠誘몄�紐� �떞湲�
				bean.setCarcategory(rs.getString(8));// 李⑥쥌瑜�(���삎,�냼�삎,以묓삎) �떞湲�
				// �떎 ���옣�맂 鍮덇컼泥대�� 諛깊꽣�뿉 ���옣
				v.add(bean);
			}
			// DB�뿰寃곌컼泥� 而ㅻ꽖�뀡 ���뿉 諛섎궔
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v; // 諛깊꽣 由ы꽩
	}// getAllCarlist()硫붿냼�뱶 �걹

	/* 移댄뀒怨좊━蹂� �옄�룞李⑤젅肄붾뱶 �뜲�씠�꽣 寃��깋硫붿냼�뱶 */
	public Vector<CarListBean> getCategoryCarList(String carcategory) {
		// 由ы꽩�븷 Vector媛앹껜瑜� �꽑�뼵
		Vector<CarListBean> v = new Vector<CarListBean>();
		// �븯�굹�쓽 �젅肄붾뱶瑜� ���옣�븷 媛앹껜 �꽑�뼵
		CarListBean bean = null;

		try {
			// 而ㅻ꽖�뀡 硫붿냼�뱶 �샇異� �븯�뿬 DB�뿰寃곌컼泥� �븯�굹 �뼸湲�
			getCon();// DB�뿰寃�
			// 荑쇰━以�鍮� : 移댄뀒怨좊━蹂� 李⑤웾 �젅肄붾뱶 寃��깋
			String sql = "select * from carlist where carcategory=?";
			// 荑쇰━瑜� �떎�뻾�븷�닔�엳�뒗 媛앹껜 �꽑�뼵
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, carcategory);
			// 荑쇰━ �떎�뻾�썑 寃곌낵瑜� 由ы꽩
			rs = pstmt.executeQuery();

			// 諛섎났臾몄쓣 �룎硫댁꽌 鍮덊겢�옒�뒪�뿉 而щ읆�뜲�씠�꽣瑜� ���옣
			while (rs.next()) {// Resultset�뿉 �젅肄붾뱶媛� 議댁옱�븷�븣源뚯� 諛섎났
				bean = new CarListBean();
				bean.setCarno(rs.getInt(1)); // 李⑤쾲�샇 �떞湲�
				bean.setCarname(rs.getString(2)); // 李⑥씠由� �떞湲�
				bean.setCarcompany(rs.getString(3));// 李⑥젣議곗궗
				bean.setCarprice(rs.getInt(4));// 李④�寃� �떞湲�
				bean.setCarusepeople(rs.getInt(5));// 李⑥씤�듅 �떞湲�
				bean.setCarinfo(rs.getString(6));// 李⑥젙蹂� �떞湲�
				bean.setCarimg(rs.getString(7));// 李⑥씠誘몄�紐� �떞湲�
				bean.setCarcategory(rs.getString(8));// 李⑥쥌瑜�(���삎,�냼�삎,以묓삎) �떞湲�
				// �떎 ���옣�맂 鍮덇컼泥대�� 諛깊꽣�뿉 ���옣
				v.add(bean);
			}
			// DB�뿰寃곌컼泥� 而ㅻ꽖�뀡 ���뿉 諛섎궔
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v; // 諛깊꽣 由ы꽩
	}// getCategoryCarList硫붿냼�뱶 �걹

	/* �븯�굹�쓽 �옄�룞李� �젙蹂대�� 由ы꽩�븯�뒗 硫붿냼�뱶 */
	public CarListBean getOneCar(int carno) {
		// 由ы꽩�븷 �븯�굹�쓽 �젅肄붾뱶瑜� ���옣�븷 媛앹껜 �꽑�뼵
		CarListBean bean = null;
		try {
			// 而ㅻ꽖�뀡 硫붿냼�뱶 �샇異� �븯�뿬 DB�뿰寃곌컼泥� �븯�굹 �뼸湲�
			getCon();// DB�뿰寃�
			// 荑쇰━以�鍮� : 留ㅺ컻蹂��닔濡� �쟾�떖諛쏆� 李⑤꽆踰꾩뿉 �빐�떦�븯�뒗 李⑤웾 �젅肄붾뱶 寃��깋
			String sql = "select * from carlist where carno=?";
			// 荑쇰━瑜� �떎�뻾�븷�닔�엳�뒗 媛앹껜 �꽑�뼵
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, carno);
			// 荑쇰━ �떎�뻾�썑 寃곌낵瑜� 由ы꽩
			rs = pstmt.executeQuery();

			// 諛섎났臾몄쓣 �룎硫댁꽌 鍮덊겢�옒�뒪�뿉 而щ읆�뜲�씠�꽣瑜� ���옣
			while (rs.next()) {// Resultset�뿉 �젅肄붾뱶媛� 議댁옱�븷�븣源뚯� 諛섎났
				bean = new CarListBean();
				bean.setCarno(rs.getInt(1)); // 李⑤쾲�샇 �떞湲�
				bean.setCarname(rs.getString(2)); // 李⑥씠由� �떞湲�
				bean.setCarcompany(rs.getString(3));// 李⑥젣議곗궗
				bean.setCarprice(rs.getInt(4));// 李④�寃� �떞湲�
				bean.setCarusepeople(rs.getInt(5));// 李⑥씤�듅 �떞湲�
				bean.setCarinfo(rs.getString(6));// 李⑥젙蹂� �떞湲�
				bean.setCarimg(rs.getString(7));// 李⑥씠誘몄�紐� �떞湲�
				bean.setCarcategory(rs.getString(8));// 李⑥쥌瑜�(���삎,�냼�삎,以묓삎) �떞湲�
			}
			// DB�뿰寃곌컼泥� 而ㅻ꽖�뀡 ���뿉 諛섎궔
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean; // 鍮� 由ы꽩
	}// getOneCar硫붿냼�뱶�걹

	
	// �젋�듃移� 二쇰Ц�뜲�씠�꽣瑜� �떞怨� �엳�뒗 CarOrderBean媛앹껜瑜� �꽆寃⑤컺�븘...
	// DB�뿉 ���옣�븯�뒗 硫붿냼�뱶
	public void insertCarOrder(CarOrderBean cbean) {
		try {
			// 而ㅻ꽖�뀡 硫붿냼�뱶 �샇異� �븯�뿬 DB�뿰寃곌컼泥� �븯�굹 �뼸湲�
			getCon();// DB�뿰寃�
			//荑쇰━以�鍮�
			String sql = "insert into carorder(carno,carqty,carreserveday,"
			+ "carbegindate,carins,carwifi,carnave,carbabyseat,memberphone,memberpass) "
			+ "values(?,?,?,?,?,?,?,?,?,?)";
			//荑쇰━瑜� �떎�뻾�븷 媛앹껜 �꽑�뼵
			pstmt = con.prepareStatement(sql);
			//?�뿉 媛믪쓣 ���엯
			pstmt.setInt(1, cbean.getCarno());
			pstmt.setInt(2, cbean.getCarqty());
			pstmt.setInt(3, cbean.getCarreserveday());
			pstmt.setString(4, cbean.getCarbegindate());
			pstmt.setInt(5, cbean.getCarins());
			pstmt.setInt(6, cbean.getCarwifi());
			pstmt.setInt(7, cbean.getCarnave());
			pstmt.setInt(8, cbean.getCarbabyseat());
			pstmt.setString(9, cbean.getMemberphone());
			pstmt.setString(10, cbean.getMemberpass());
			
			//荑쇰━瑜� �떎�뻾�븯�떆�삤.
			pstmt.executeUpdate();
			
			//�뵒鍮꾩뿰寃� 媛앹껜 諛섎궔
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//insertCarOrder硫붿냼�뱶 �걹

	/*�삁�빟�솗�씤 �럹�씠吏��뿉�꽌..�젋�듃移댁삁�빟�떆 �옉�꽦�븳..
	 * �쟾�솕踰덊샇�� �뙣�뒪�썙�뱶�뿉 �빐�떦�븯�뒗  �삁�빟�븳 二쇰Ц�젙蹂대�� 紐⑤몢 媛��졇�삤�뒗 硫붿냼�뱶  */
	public Vector<CarConfirmBean> getAllCarOrder(String memberphone, String memberpass) {
		//�젋�듃�삁�빟 �젙蹂대�� �떞怨좎엳�뒗 媛곴컖�쓽 CarConfirmBean媛앹껜瑜� �떞湲곗쐞�븳 而щ젆�뀡 媛앹껜 �깮�꽦
		Vector<CarConfirmBean> v = new Vector<CarConfirmBean>();
		//DB�뿉�꽌 寃��깋�븳 �젋�듃�삁�빟 �젙蹂� 媛앹껜(CarConfirmBean媛앹껜)瑜� ���옣�븷 李몄“蹂��닔 �꽑�뼵 
		CarConfirmBean bean = null;
		try {
			//DB�뿰寃�
			getCon();
			//�젋�듃�삁�빟�븳 �궇�옄媛�  �쁽�옱�궇吏쒕낫�떎 �겕怨�,
			//�젋�듃�삁�빟�떆 �옉�꽦�븳 鍮꾪쉶�썝 �쟾�솕踰덊샇怨� �뙣�뒪�썙�뱶�뿉 �빐�떦�븯�뒗 �젋�듃�삁�빟�젙蹂대�� 寃��깋�븯�뒗�뜲..
			//carorder�뀒�씠釉붽낵 carorder�뀒�씠釉붿쓣 natural 議곗씤�븯�뿬 寃��깋.
			
			//李멸퀬! String ���엯�쓣 Date���엯�쑝濡� 蹂�寃� �뻽�떎 
			String sql = "select * from carorder natural join carlist where "
					+ "now() < str_to_date(carbegindate , '%Y-%m-%d') and "
					+ "memberphone=? and memberpass=?";
			
			//李멸퀬
			//SELECT 臾몄뿉 *�� 媛숈씠 蹂꾨룄�쓽 而щ읆 �닚�꽌瑜� 吏��젙�븯吏� �븡�쑝硫�...
			//NATURAL JOIN�쓽 湲곗��씠 �릺�뒗 而щ읆�뱾�씠 �떎瑜� 而щ읆蹂대떎 癒쇱� 異쒕젰�맂�떎. 
			//�씠 �븣 NATURAL JOIN�� JOIN�뿉 �궗�슜�맂 媛숈� �씠由꾩쓽 而щ읆�쓣 以묐났異쒕젰�븯吏� �븡怨� �븯�굹濡� 泥섎━�븳�떎. 


			//?瑜� �젣�쇅�븳 select援щЦ�쓣 �떞�� 荑쇰━�떎�뻾 媛앹껜 諛섑솚
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberphone);//? 媛� �뀑�똿  : �삁�빟�떆 �옉�꽦�븳 �쟾�솕踰덊샇
			pstmt.setString(2, memberpass);//? 媛� �뀑�똿 : �삁�빟�떆 �옉�꽦�븳 鍮꾨�踰덊샇 
			//select�븳 �젋�듃�삁�빟�젙蹂대�� �떞怨� �엳�뒗 ResultSet媛앹껜 諛섑솚
			rs = pstmt.executeQuery();
			//�젋�듃 �삁�빟 �젙蹂� �븯�굹�븯�굹瑜�  CarConfirmBean()媛앹껜�뿉 ���옣
			while(rs.next()){
				bean = new CarConfirmBean(); //dto �깮�꽦
				bean.setOrderid(rs.getInt(2));//�옄�룞李� �젋�듃(���뿬)�븳  二쇰Цid �떞湲�
				bean.setCarqty(rs.getInt(3));//�젋�듃 李⑤웾 ���뿬�븳 �닔�웾 ���옣
				bean.setCarreserveday(rs.getInt(4));//���뿬�븳 湲곌컙 ���옣
				bean.setCarbegindate(rs.getString(5));//�옄�룞李⑤�� �젋�듃(���뿬)�엺 �떆�옉�궇吏� ���옣.
				bean.setCarins(rs.getInt(6));//�젋�듃�떆 蹂댄뿕�쟻�슜 �씪�닔 ���옣
				bean.setCarwifi(rs.getInt(7));//�젋�듃�떆 臾댁꽑wifi�쟻�슜 �씪�닔 ���옣
				bean.setCarnave(rs.getInt(8));//�젋�듃�떆 �꽕鍮꾧쾶�씠�뀡 �쟻�슜�뿬遺� ���옣 
				bean.setCarbabyseat(rs.getInt(9));//�젋�듃�떆 踰좎씠鍮꾩떆�듃�쟻�슜 �씪�닔 ���옣
				bean.setCarname(rs.getString(12));//�젋�듃�삁�빟�븳 �옄�룞李� �씠由� ���옣
				bean.setCarprice(rs.getInt(14));//�젋�듃�삁�빟�븳 �옄�룞李� 媛�寃⑹��옣
				bean.setCarimg(rs.getString(17));//�젋�듃�삁�빟�븳 �옄�룞李� �씠誘몄� ���옣
				v.add(bean);//諛깊꽣�뿉 �떞湲�
			}
			//�옄�썝�빐�젣
			con.close();		
			
		} catch (Exception e) {
			System.out.println("getAllCarOrder硫붿냼�뱶�뿉�꽌 �삤瑜� : " + e);
		}
		
		
		//諛깊꽣 諛섑솚
		return v;
	}
	
	//�븯�굹�쓽 二쇰Ц�븘�씠�뵒瑜� �쟾�떖諛쏆븘... �븯�굹�쓽 二쇰Ц �젙蹂대�� 由ы꽩�븯�뒗 硫붿냼�뱶
	public CarConfirmBean getOneOrder(int orderid) {
		// 由ы꽩���엯 �꽑�뼵
		CarConfirmBean cbean =null;
		try {
			getCon();
			//荑쇰━以�鍮�
			String sql ="select * from carorder where orderid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderid);
			//寃곌낵瑜� 由ы꽩
			rs=pstmt.executeQuery();
			if(rs.next()){
				cbean = new CarConfirmBean();
				cbean.setOrderid(orderid);//�삁�빟 id
				cbean.setCarbegindate(rs.getString(5));//���뿬 �삁�빟(二쇰Ц)�궇吏�
				cbean.setCarreserveday(rs.getInt(4));//���뿬湲곌컙
				cbean.setCarins(rs.getInt(6));//蹂댄뿕 �쟻�슜 �뿬遺�
				cbean.setCarwifi(rs.getInt(7));//wifi �쟻�슜 �뿬遺�
				cbean.setCarnave(rs.getInt(8));//�꽕鍮� �쟻�슜 �뿬遺�
				cbean.setCarbabyseat(rs.getInt(9));//踰좎씠鍮꾩떆�떚 �쟻�슜 �뿬遺� 
			}
			con.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return cbean;
	}//getOneOrder硫붿냼�뱶 �걹


	//�삁�빟 �닔�젙�궡�슜�쓣 �쟾�떖諛쏆븘.. �떎�떆�삁�빟�쓣 �닔�젙�븯�뒗 硫붿냼�뱶	
	public void carOrderUpdate(CarOrderBean bean) {
		try {
			getCon();
		String sql ="update carorder set carbegindate=? , carreserveday=? , carqty=?"
					+ ", carins=? , carwifi=? , carbabyseat=? where orderid=? "
					+ "and memberpass=?";
			//荑쇰━ �떎�뻾�븷 媛앹껜 �깮�꽦
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, bean.getCarbegindate());
			pstmt.setInt(2, bean.getCarreserveday());
			pstmt.setInt(3, bean.getCarqty());
			pstmt.setInt(4, bean.getCarins());
			pstmt.setInt(5, bean.getCarwifi());
			pstmt.setInt(6, bean.getCarbabyseat());
			pstmt.setInt(7, bean.getOrderid());
			pstmt.setString(8, bean.getMemberpass());
			pstmt.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//�븯�굹�쓽 二쇰Ц �젙蹂대�� �궘�젣 �븯�뒗 硫붿냼�뱶
	public int carOrderDelete(int orderid, String memberpass) {
		int result=0;
		try {
			getCon();
			String sql ="delete from carorder where orderid=? and memberpass=?";
			pstmt = con.prepareStatement(sql);
			//?�뿉 媛믪쓣 ���엯
			pstmt.setInt(1, orderid);
			pstmt.setString(2, memberpass);
			//荑쇰━媛� �떎�뻾�릺吏� �븡�븯�떎硫� 0媛믪씠 由ы꽩 �떎�뻾�씠 �릺硫� 1�씠 由ы꽩�맗�땲�떎.
			result = pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return result;
	}

}