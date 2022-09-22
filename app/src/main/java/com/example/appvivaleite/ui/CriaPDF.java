package com.example.appvivaleite.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CriaPDF {

    private File pasta;
    private File arquivo;
    private Context context;

    public CriaPDF(File pasta, Context context){
        this.pasta = pasta;
        this.context = context;

        if(!pasta.exists()){
            pasta.mkdirs();
        }
    }

    public String salvaPDF(Bitmap bitmap, String nomeArquivo){
        arquivo = new File(pasta, nomeArquivo + ".pdf");
        PdfDocument pdf = new PdfDocument();
        PdfDocument.PageInfo info = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1 ).create();
        PdfDocument.Page page = pdf.startPage(info);
        Canvas canvas = page.getCanvas();
        canvas.drawBitmap(bitmap,null, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), null);
        pdf.finishPage(page);
        try {
            arquivo.createNewFile();
            OutputStream streamSaida=  new FileOutputStream(arquivo);
            pdf.writeTo(streamSaida);
            streamSaida.close();
            pdf.close();
        } catch (IOException e) {
            return "Falha ao criar o arquivo PDF";
        }
        return "sucesso";
    }
}
