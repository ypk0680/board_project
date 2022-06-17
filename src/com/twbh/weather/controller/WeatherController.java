/*
 * package com.twbh.weather.controller;
 * 
 * import java.io.BufferedReader; import java.io.IOException; import
 * java.io.InputStreamReader; import java.net.URL; import
 * java.text.SimpleDateFormat; import java.util.Date;
 * 
 * import javax.servlet.RequestDispatcher; import
 * javax.servlet.ServletException; import javax.servlet.annotation.WebServlet;
 * import javax.servlet.http.HttpServlet; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.json.simple.JSONArray; import org.json.simple.JSONObject; import
 * org.json.simple.parser.JSONParser;
 * 
 * import com.twbh.common.dao.WeatherDAO; import com.twbh.common.dto.WeatherVO;
 * import com.twbh.weather.controller.action.LocationMatch; import
 * com.twbh.weather.controller.action.timeTrans;
 * 
 *//**
	 * Servlet implementation class WeatherController
	 *//*
		 * @WebServlet("/weather.do") public class WeatherController extends HttpServlet
		 * { private static final long serialVersionUID = 1L;
		 * 
		 * public WeatherController() { super(); // TODO Auto-generated constructor stub
		 * }
		 * 
		 * 
		 * protected void doGet(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException { String url = "weather.jsp";
		 * String loc = request.getParameter("loc"); //버튼으로 넘어온 파라미터에서 도시 코드 추출
		 * 
		 * //1. 도시 코드 이름으로 변환 LocationMatch lm = new LocationMatch(); loc =
		 * lm.matchCity(loc);
		 * 
		 * request.setAttribute("locaiton", loc);
		 * 
		 * //2. 디비에서 입력된 좌표값 가져오기 WeatherDAO wDao = WeatherDAO.getInstance(); WeatherVO
		 * wVo = wDao.selectLocation(loc); // 가져 온 값 vo에 저장
		 * 
		 * request.setAttribute("loc_x", wVo.getLoc_x()); request.setAttribute("loc_y",
		 * wVo.getLoc_y());
		 * 
		 * //3. 좌표값을 이용해 날씨 가져오기
		 * 
		 * 요청정보입력 아래와 같은 정보들은 사용자 가이드를 확인하여 찾아주시면 됩니다. 위도 경도는 엑셀파일 안에 있습니다.
		 * 
		 * try { timeTrans tt = new timeTrans();
		 * 
		 * String baseTime =tt.TransTime(); String baseDate = tt.TransDate(); int nx =
		 * wVo.getLoc_x(); // 경도 int ny = wVo.getLoc_y(); // 위도 //String baseDate =
		 * format.format(date); // 자신이 조회하고싶은 날짜를 입력해주세요 //String baseTime = "1700"; //
		 * 0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회) String serviceKey =
		 * "n%2BkpgrioAqgkWr4CRZkD2H8EqS0EcU70vCvLRdeuHr122yFRKVOwkSCgSBNQrZyIfdeRSq1pBaQuxTEqE0URkw%3D%3D";
		 * 
		 * // 정보를 모아서 URL정보를 만들면됩니다. 맨 마지막 "&_type=json"에 따라 반환 데이터의 형태가 정해집니다. String
		 * urlStr =
		 * "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?"
		 * + "serviceKey=" + serviceKey + "&base_date=" + baseDate + "&base_time=" +
		 * baseTime + "&nx=" + nx + "&ny=" + ny + "&_type=json";
		 * System.out.println(urlStr); URL parsing_url = new URL(urlStr); // 위 urlStr을
		 * 이용해서 URL 객체를 만들어줍니다. BufferedReader bf; String line = ""; String result = "";
		 * 
		 * // 날씨 정보를 받아옵니다. bf = new BufferedReader(new
		 * InputStreamReader(parsing_url.openStream()));
		 * 
		 * // 버퍼에 있는 정보를 하나의 문자열로 변환. while ((line = bf.readLine()) != null) { result =
		 * result.concat(line); }
		 * 
		 * // Json parser를 만들어 만들어진 문자열 데이터를 객체화 합니다. JSONParser parser = new
		 * JSONParser(); JSONObject obj = (JSONObject) parser.parse(result);
		 * 
		 * // Top레벨 단계인 response 키를 가지고 데이터를 파싱합니다. JSONObject parse_response =
		 * (JSONObject) obj.get("response"); // response 로 부터 body 찾아옵니다. JSONObject
		 * parse_body = (JSONObject) parse_response.get("body"); // body 로 부터 items
		 * 받아옵니다. JSONObject parse_items = (JSONObject) parse_body.get("items");
		 * 
		 * // items로 부터 itemlist 를 받아오기 itemlist : 뒤에 [ 로 시작하므로 jsonarray이다 JSONArray
		 * parse_item = (JSONArray) parse_items.get("item");
		 * 
		 * String category; JSONObject obj1; // parse_item은 배열형태이기 때문에 하나씩 데이터를 하나씩 가져올때
		 * 사용합니다.
		 * 
		 * String pop, pty, r06, reh, s06, sky, t3h, tmn, tmx, uuu, vec, vvv, wav, wsd;
		 * // 필요한 데이터만 가져오려고합니다. for (int i = 0; i < parse_item.size(); i++) { obj1 =
		 * (JSONObject) parse_item.get(i); category = (String) obj1.get("category");
		 * 
		 * // 검색한 카테고리와 일치하는 변수에 문자형으로 데이터를 저장합니다. // 데이터들이 형태가 달라 문자열로 통일해야 편합니다. 꺼내서
		 * 사용할때 다시변환하는게 좋습니다. switch (category) { case "POP": pop =
		 * (obj1.get("fcstValue")).toString(); System.out.println("pop 강수확률:" + pop);
		 * request.setAttribute("pop", pop); break; case "PTY": pty =
		 * (obj1.get("fcstValue")).toString(); System.out.println("pty 강수형태: " + pty);
		 * if(pty.equals("0")){ pty="0"; }else if(pty.equals("1")){ pty="비"; }else
		 * if(pty.equals("2")){ pty="비/눈"; }else if(pty.equals("3")){ pty="눈"; }
		 * request.setAttribute("pty", pty); break; case "R06": r06 =
		 * (obj1.get("fcstValue")).toString(); System.out.println("r06 강수량:" + r06);
		 * request.setAttribute("r06", r06); break; case "REH": reh =
		 * (obj1.get("fcstValue")).toString(); System.out.println("reh 습도:" + reh);
		 * request.setAttribute("reh", reh); break; case "SKY": sky =
		 * (obj1.get("fcstValue")).toString(); System.out.println("sky 하늘상태:" + sky);
		 * if(sky.equals("1")){ sky="맑음"; }else if(sky.equals("2")){ sky="구름조금"; }else
		 * if(sky.equals("3")){ sky="구름많음"; }else if(sky.equals("4")){ sky="흐림"; }
		 * request.setAttribute("sky", sky); break; case "T3H": t3h =
		 * (obj1.get("fcstValue")).toString(); System.out.println("t3h 기온:" + t3h);
		 * request.setAttribute("t3h", t3h); break; case "TMN": tmn =
		 * (obj1.get("fcstValue")).toString(); System.out.println("tmn 아침 최저기온:" + tmn);
		 * request.setAttribute("tmn", tmn); break; case "TMX": tmx =
		 * (obj1.get("fcstValue")).toString(); System.out.println("tmx 낮 최고기온:" + tmx);
		 * request.setAttribute("tmx", tmx); break; case "UUU": uuu =
		 * (obj1.get("fcstValue")).toString(); System.out.println("uuu 풍속(동서성분):" +
		 * uuu); break; case "VEC": vec = (obj1.get("fcstValue")).toString();
		 * System.out.println("vec 풍향:" + vec); break; case "VVV": vvv =
		 * (obj1.get("fcstValue")).toString(); System.out.println("vvv 풍속(남북성분):" +
		 * vvv); break; case "WAV": wav = (obj1.get("fcstValue")).toString();
		 * System.out.println("wav 파고:" + wav); break; case "WSD": wsd =
		 * (obj1.get("fcstValue")).toString(); System.out.println("wsd 풍속:" + wsd);
		 * break; case "S06": s06 = (obj1.get("fcstValue")).toString();
		 * System.out.println("s06 신적설:" + s06); break; }
		 * 
		 * }
		 * 
		 * bf.close(); } catch (Exception e) { System.out.println("에러:" +
		 * e.getMessage()); }
		 * 
		 * RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		 * dispatcher.forward(request, response); }
		 * 
		 * 
		 * protected void doPost(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException { // TODO Auto-generated
		 * method stub doGet(request, response); }
		 * 
		 * }
		 */