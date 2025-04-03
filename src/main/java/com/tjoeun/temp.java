package com.tjoeun;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class temp {
	public static void main(String[] args) throws IOException {
		File file = new File("f:/temp.sql");
		FileOutputStream fs = new FileOutputStream(file);
		BufferedOutputStream bs = new BufferedOutputStream(fs);
		for(int i = 0; i < 100; i++) {
			String sql = "insert into board(board_id,title, content, user) values(0, 'title" + i + "', 'content " + i + "', 6);\n";
			bs.write(sql.getBytes());
			bs.flush();
		}
		bs.close();
	}
}
