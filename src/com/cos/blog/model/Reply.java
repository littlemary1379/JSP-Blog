package com.cos.blog.model;

import com.sun.jmx.snmp.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	private int id;
	private int userId;
	private int boardId;
	private String content;
	private Timestamp createDate;
}
