package com.beini.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Created by beini on 2017/11/8.
 */
public class FileUtil {

    /**
     * 拼接
     *
     * @param cellsPath
     * @param newFilePath
     */
    public static void merge(List<String> cellsPath, String newFilePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(newFilePath);
            ArrayList<FileInputStream> cellsInputString = new ArrayList<FileInputStream>();

            for (int i = 0; i < cellsPath.size(); i++) {
                cellsInputString.add(new FileInputStream(cellsPath.get(i)));
            }
            final Iterator<FileInputStream> iterator = cellsInputString.iterator();
            Enumeration<FileInputStream> enumeration = new Enumeration<FileInputStream>() {
                public boolean hasMoreElements() {
                    return iterator.hasNext();
                }

                public FileInputStream nextElement() {
                    return iterator.next();
                }
            };

            SequenceInputStream sequenceInputStream = new SequenceInputStream(enumeration);
            int count;

            byte[] buf = new byte[100 * 1024];
            while ((count = sequenceInputStream.read(buf)) != -1) {
                fileOutputStream.write(buf, 0, count);
            }
            sequenceInputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void appendFile(String str, File dest) {
        RandomAccessFile randomAccessStr;
        RandomAccessFile randomAccessDest;
        try {
            randomAccessStr = new RandomAccessFile(str, "rw");
            randomAccessDest = new RandomAccessFile(dest, "rw");

            long strLength = randomAccessStr.length();
            long destLength = randomAccessDest.length();
            randomAccessStr.setLength(strLength + destLength);
            FileChannel fileChannelStr = randomAccessStr.getChannel();
            FileChannel fileChannel1Dest = randomAccessDest.getChannel();
            fileChannelStr.transferFrom(fileChannel1Dest, strLength, fileChannel1Dest.size());
            randomAccessStr.close();
            randomAccessDest.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
