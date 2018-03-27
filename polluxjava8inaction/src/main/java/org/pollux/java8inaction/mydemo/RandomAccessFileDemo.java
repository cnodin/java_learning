package org.pollux.java8inaction.mydemo;

import com.wwyl.lark.util.io.ResourceUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 02/12/2017
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class RandomAccessFileDemo {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100000; i++) {
            long begin = System.currentTimeMillis();
            String filePath = "/Users/pollux/projects/wwyl/phoenix/puppet-dynamic-module/file1";
            long lineCount = Files.lines(Paths.get(filePath)).count();
            Path file = Paths.get(filePath);
            String content = ResourceUtil.toString("puppet/init.pp.template");
            insertStringInFile(file.toFile(), lineCount - 1, content);

            System.out.println(String.valueOf(i) + ":" + String.valueOf(System.currentTimeMillis() - begin));
        }
    }

    public static void insertStringInFile
            (File inFile, long lineno, String lineToBeInserted)
            throws Exception {
        // temp file
        File outFile = new File("$$$$$$$$.tmp");

        // input
        try(FileInputStream fis  = new FileInputStream(inFile)) {

            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            // output
            try(FileOutputStream fos = new FileOutputStream(outFile)){
                PrintWriter out = new PrintWriter(fos);

                String thisLine;
                int i = 1;
                while ((thisLine = in.readLine()) != null) {
                    if (i == lineno) {
                        out.println(lineToBeInserted);
                    }
                    out.println(thisLine);
                    i++;
                }
                out.flush();
            }//close fos

            inFile.delete();
            outFile.renameTo(inFile);
        }//close fis
    }

    /**
     *
     * @param fileName 文件
     * @param pos 插入位置
     * @param insertContent 插入内容
     * @throws IOException
     */
    public static void insert(String fileName, long pos, String insertContent)throws IOException{

        RandomAccessFile raf = null;
        File tmp = File.createTempFile("tmp", null);
        FileOutputStream tmpOut = null;
        FileInputStream tmpIn = null;
        tmp.deleteOnExit();

        try{

            raf = new RandomAccessFile(fileName, "rw");

            tmpOut = new FileOutputStream(tmp);

            tmpIn = new FileInputStream(tmp);

            raf.seek(pos);

            byte[] bbuf = new byte[64];

            int hasRead = 0;

            while ((hasRead = raf.read(bbuf)) > 0){

                tmpOut.write(bbuf, 0, hasRead);

            }

            raf.seek(pos);

            raf.write(insertContent.getBytes());

            while ((hasRead = tmpIn.read(bbuf)) > 0){

                raf.write(bbuf, 0, hasRead);
            }

        }finally{
            raf.close();
        }
    }

}
