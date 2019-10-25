package com.ahsj.smartparkcore.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UpdateFile {
	public static void main(String[] args) throws IOException {
		File  f= new File("d:/test");
		UpdateFile updateFile = new UpdateFile();
		updateFile.update(f);
	}
	public void update(File f) throws FileNotFoundException, IOException{
		if(f.isFile()){
			updateFile(f);			
		}else{
			File[] files = f.listFiles();
			for(File file : files){
				update(file);
			}
		}
	}

	public void updateFile(File f) throws FileNotFoundException, IOException {
		File f2 = new File(f.getAbsolutePath()+"2");
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
		br = new BufferedReader(new FileReader(f));
		bw = new BufferedWriter(new FileWriter(f2));
		
		String line = null;
		while((line = br.readLine()) != null){
			String newLine = line;
			if(line.indexOf("@Column")!=-1){
				String part1 = line.substring(0, line.indexOf("\"")+1);
				String part2 = line.substring(line.indexOf("\"")+1);
				String column = part2.substring(0, part2.indexOf("\""));
				column = column.toLowerCase();
				
				String part3 = part2.substring(part2.indexOf("\""));
				
				newLine = part1 + column+part3;
			}
			
			bw.write(newLine);
			bw.newLine();
		}
		bw.flush();
		} finally {
			if(bw != null){
				bw.close();
			}
			if(br != null){
				br.close();
			}
			/*boolean isDelete = */f.delete();
			String f2Name = f2.getName();
//			System.out.println(f2Name);
//			System.out.println(isDelete);
			f2.renameTo(f);
			
			System.out.println("更新完成文件："+f.getAbsolutePath());
		}
	}
}