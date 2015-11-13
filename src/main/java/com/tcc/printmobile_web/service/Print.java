package com.tcc.printmobile_web.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PageRanges;

import com.sun.jersey.core.util.Base64;
import com.tcc.printmobile_web.model.File;
import com.tcc.printmobile_web.model.Img;
import com.tcc.printmobile_web.model.Pdf;

public class Print {

	public void print(File file) throws PrintException, IOException {

		Pdf pdf = null;
		if (file instanceof Pdf)
			pdf = (Pdf) file;

		byte[] bytearray = Base64.decode(file.getByteBase64());

		InputStream prin = null;

		if (file instanceof Img) {
			BufferedImage imag = ImageIO.read(new ByteArrayInputStream(
					bytearray));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// ImageIO.write(imag, "jpg", new java.io.File("/home/ddutra9",
			// "snap.jpg"));escreve a imagem
			ImageIO.write(imag, "jpg", baos);

			prin = new ByteArrayInputStream(baos.toByteArray());
		} else
			prin = new ByteArrayInputStream(bytearray);

		DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		SimpleDoc documentoTexto = new SimpleDoc(prin, docFlavor, null);
		PrintService impressora = PrintServiceLookup
				.lookupDefaultPrintService(); // pega a //impressora padrao
		PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();
		printerAttributes.add(new JobName("Impressao", null));

		if (!file.getLandscape())
			printerAttributes.add(OrientationRequested.PORTRAIT);
		else
			printerAttributes.add(OrientationRequested.LANDSCAPE);

		if (file.getColorful())
			printerAttributes.add(Chromaticity.COLOR);
		else
			printerAttributes.add(Chromaticity.MONOCHROME);

		if (pdf != null && !pdf.getIntervalPage().isEmpty()) {
			int startPage = 0, endPage = 0;
			if (pdf.getIntervalPage().contains("-")) {
				String[] pageRange = pdf.getIntervalPage().split("-");
				startPage = Integer.parseInt(pageRange[0]);
				endPage = Integer.parseInt(pageRange[1]);
			} else {
				startPage = Integer.parseInt(pdf.getIntervalPage());
				endPage = startPage;
			}

			printerAttributes.add(new PageRanges(startPage, endPage));
		}

		printerAttributes.add(new Copies(file.getCopies().intValue()));
		printerAttributes.add(MediaSizeName.ISO_A4); // informa o tipo de
														// folha
		DocPrintJob printJob = impressora.createPrintJob();

		printJob.print(documentoTexto,
				(PrintRequestAttributeSet) printerAttributes); // tenta
																// imprimir

		prin.close();
	}

	private byte[] convertByte(java.io.File f) {
		FileInputStream fileInputStream = null;

		byte[] bFile = new byte[(int) f.length()];

		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(f);
			fileInputStream.read(bFile);
			fileInputStream.close();

			for (int i = 0; i < bFile.length; i++) {
				System.out.print((char) bFile[i]);
			}

			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bFile;
	}

}
