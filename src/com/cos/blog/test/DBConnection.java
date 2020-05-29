package com.cos.blog.test;

import org.junit.jupiter.api.Test;

import com.cos.blog.db.DBConn;

public class DBConnection {
	
		@Test
		public void 데이터베이스_연결_테스트() {
			DBConn.getConnection();
		}

}
