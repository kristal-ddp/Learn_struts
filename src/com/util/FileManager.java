package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileManager {
	
//	파일 다운로드 메소드
	public static boolean doFileDownload(HttpServletResponse response,
			String saveFileName, String originalFileName, String path) {//path: 위치를 아는데 필요한 인수
		
		try {
			
			String fullPath = path + File.separator + saveFileName;
			
			if(originalFileName==null || originalFileName.equals("")) {
				originalFileName = saveFileName;
			}
			
//			한글파일 이름 깨짐 방지
			originalFileName =
					new String(originalFileName.getBytes("euc-kr"),"ISO-8859-1");//파일을 클릭해 다운로드 받을때, 한글이 깨지지 않도록 한다
			
			File f = new File(fullPath);
			
			if(!f.exists()) {
				return false;
			}
			
//			파일 다운로드
			response.setContentType("application/octet-stream");
			
			response.setHeader("Content-disposition",
					"attachment;fileName=" + originalFileName);
			
			BufferedInputStream bis =
					new BufferedInputStream(new FileInputStream(f));//처리가 가장 빠른게 BufferedInputStream. 다른거 써도 상관없지만, 이게 best
			
			OutputStream out = response.getOutputStream();
			
			int data;
			byte[] bytes = new byte[4096];
			while((data=bis.read(bytes, 0, 4096))!=-1) {
				out.write(bytes, 0, data);				
			}
			
			out.flush();//버퍼가 꽉 차지 않아도 강제로 나가게 함
			out.close();
			bis.close();			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
		return true;
		
	}
	
	
//	파일 삭제 메소드 - file, DB 둘다 지워야함
	public static void doFileDelete(String fileName, String path) {
		
		try {
			
			String filePath = path + File.separator + fileName;
			
			File f = new File(filePath);
			
			if(f.exists()) {
				f.delete();//물리적 파일 삭제. 폴더에 있는 파일을 삭제함
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

}
