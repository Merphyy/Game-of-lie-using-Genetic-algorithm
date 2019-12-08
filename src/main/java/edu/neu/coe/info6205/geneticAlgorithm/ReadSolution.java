package edu.neu.coe.info6205.geneticAlgorithm;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadSolution {
	public ReadSolution() {
		// TODO Auto-generated constructor stub
	}

	public int[][] read() {

		// 讀取配置文件名

		SAXReader reader = new SAXReader();
		InputStream in = getClass().getClassLoader().getResourceAsStream("solution.xml");
		String solution = "";
		try {
			// 讀取smartmvc配置文件的內容
			Document doc = reader.read(in);
			// 找到跟節點
			Element root = doc.getRootElement();
			// 找到跟節點下面的所有子節點
			List<Element> elements = root.elements();
			// 遍歷所有子節點
			
			for (Element ele : elements) {
				// 讀取class屬性值(即處理器類名)
				solution = ele.attributeValue("string");
				System.out.println("solution: " + solution);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("solution length"+ solution.length());
		
		int[][] board = new int[200][200];
		int index =0;
		int count = 0;
		for(int i=0;i<200;i++) {
			for(int j=0;j<200;j++) {
				board[i][j] =  Integer.parseInt(String.valueOf(solution.charAt(index)));
				index++;
				if(board[i][j]==1) {
					count++;
				}
			}
		}
		
		System.out.println("------------live cells: "+count+"----------");
		
		return board;
	}

}
