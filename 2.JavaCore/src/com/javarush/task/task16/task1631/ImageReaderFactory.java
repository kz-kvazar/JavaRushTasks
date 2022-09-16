package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes jpg) {
        if(jpg == null){
            throw new IllegalArgumentException("Неизвестный тип картинки");
        }else if (jpg.equals(ImageTypes.JPG)) {
            return new JpgReader();
        } else if (jpg.equals(ImageTypes.BMP)) {
            return new BmpReader();
        } else if (jpg.equals(ImageTypes.PNG)) {
            return new PngReader();
        } else {
            throw new IllegalArgumentException("Неизвестный тип картинки");
        }
    }
}