package com.cos.blog.test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

public class Sha256Test {
	
	private final static String salt = "코스";

	@Test
	public void encSha256() {
		
		String plain="1234";
		String result="";
		
		byte [] bytePlain=plain.getBytes(); //평문을 byte 단위로 쪼갬.
		
		byte [] byteSalt=salt.getBytes();
		
		byte [] bytePlaninAndSalt=new byte[bytePlain.length+byteSalt.length];
		
		System.arraycopy(bytePlain, 0, bytePlaninAndSalt, 0, bytePlain.length);
		System.arraycopy(byteSalt, 0, bytePlaninAndSalt, bytePlain.length, byteSalt.length);
		
		try {
			MessageDigest md=MessageDigest.getInstance("SHA-256");
			md.update(bytePlaninAndSalt);
			
			byte[] byteData=md.digest();
			//Critical Section 있는 것이 StringBuilder과 다름
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toHexString((byteData[i] & 0xFF)+256).substring(1));
			}
			result=sb.toString();
			System.out.println(result);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
