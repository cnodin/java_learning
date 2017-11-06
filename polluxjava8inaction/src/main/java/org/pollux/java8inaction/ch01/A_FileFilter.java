package org.pollux.java8inaction.ch01;

import java.io.File;
import java.io.FileFilter;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 17/10/14
 * Time: 上午9:00
 * To change this template use File | Settings | File Templates.
 * Description: demo function references
 */
public class A_FileFilter {

	private static File[] filterFiles(File file){
		File[] hiddenFiles = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isHidden();
			}
		});

		return hiddenFiles;
	}

	/**
	 * FileFilter是函数接口
	 * @param file
	 * @return
	 */
	private static File[] filterFilesJava8(File file){
		return file.listFiles(File::isHidden);
	}

	public static void main(String[] args) {
		System.out.println(filterFiles(new File("/Users/pollux")).length ==
						filterFilesJava8(new File("/Users/pollux")).length);
	}

}
