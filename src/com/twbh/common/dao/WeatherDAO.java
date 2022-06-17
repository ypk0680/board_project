package com.twbh.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.twbh.common.dto.BoardVO;
import com.twbh.common.dto.WeatherVO;

import util.DBManager;

public class WeatherDAO {
	private WeatherDAO(){
		
	}
	
	private static WeatherDAO instance = new WeatherDAO();
	
	public static WeatherDAO getInstance(){
		return instance;
	}
	
	
	public WeatherVO selectLocation(String loc){
		String sql = "select 격자_X,격자_Y from weather_loc where city_step1=? and city_step2 is null";
		
		WeatherVO wVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, loc);  
			
			rs = pstmt.executeQuery(); // sql 실행값을 rs에 저장
			
			while(rs.next()){
				wVo = new WeatherVO();
				wVo.setLoc_x(rs.getInt("격자_X"));
				wVo.setLoc_y(rs.getInt("격자_Y"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return wVo;
	}
	


}
