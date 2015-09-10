package com.learning.app.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTOneCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learning.app.service.UploadFileService;

@Service("uploadFileService")
public class UploadFileServiceImpl implements UploadFileService {

	public List<String> uploadMultipleFiles(MultipartFile[] files) throws Exception {
		if (files != null && files.length > 0) {

			for (MultipartFile file : files) {

				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(file.getBytes());
				stream.close();
				createWorkBook(file.getBytes());
			}
		}

		return null;
	}

	private void createWorkBook(byte[] bytes) throws Exception {

		String path = "D:/temp/Test.xlsx";

		String sheetName = "Order1";// name of sheet
		
		

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);
		
		CellStyle cellStyle = wb.createCellStyle();
		CreationHelper createHelper = wb.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MMMM dd, yyyy"));

		XSSFRow row0 = sheet.createRow(0);
		XSSFCell cell0 = row0.createCell(0);
		cell0.setCellValue("Parchuniye");

		XSSFRow row1 = sheet.createRow(1);
		XSSFCell cell1 = row1.createCell(0);
		cell1.setCellValue("Kiryana Store");

		XSSFRow row2 = sheet.createRow(2);
		XSSFCell cell2 = row2.createCell(0);
		cell2.setCellValue(new Date());
		cell2.setCellStyle(cellStyle);

		XSSFRow row3 = sheet.createRow(3);
		XSSFCell cell3 = row3.createCell(0);
		cell3.setCellValue(" ");

		
		int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);

		XSSFDrawing drawing = sheet.createDrawingPatriarch();

        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 500, 400, 0,5,0,0);

        createPicture(anchor, pictureIdx, drawing);

        sheet.autoSizeColumn(0);

		FileOutputStream out = new FileOutputStream(new File(path));

		// write this workbook to an Outputstream.
		wb.write(out);
		out.flush();
		out.close();
	}
	
	 public static XSSFPicture createPicture(XSSFClientAnchor anchor, int pictureIndex, XSSFDrawing drawing)
			    throws Exception
			    {
			        Method m = XSSFDrawing.class.getDeclaredMethod("addPictureReference", int.class);
			        m.setAccessible(true);
			        PackageRelationship rel = (PackageRelationship)m.invoke(drawing, (Integer)pictureIndex);

			        long shapeId = 1000+drawing.getCTDrawing().sizeOfOneCellAnchorArray();
			        CTOneCellAnchor ctAnchor = createOneCellAnchor(drawing, anchor);
			        CTPicture ctShape = ctAnchor.addNewPic();

			        m = XSSFPicture.class.getDeclaredMethod("prototype");
			        m.setAccessible(true);
			        CTPicture ctp = (CTPicture)m.invoke(null);
			        ctShape.set(ctp);
			        ctShape.getNvPicPr().getCNvPr().setId(shapeId);

			        Constructor<XSSFPicture> picCon = XSSFPicture.class
			            .getDeclaredConstructor(XSSFDrawing.class, CTPicture.class);
			        picCon.setAccessible(true);

			        XSSFPicture shape = picCon.newInstance(drawing, ctShape);
			        Field f = XSSFShape.class.getDeclaredField("anchor");
			        f.setAccessible(true);
			        f.set(shape, anchor);

			        m = XSSFPicture.class.getDeclaredMethod("setPictureReference", PackageRelationship.class);
			        m.setAccessible(true);
			        m.invoke(shape, rel);
			        return shape;
			    }

			    public static CTOneCellAnchor createOneCellAnchor(XSSFDrawing drawing, XSSFClientAnchor anchor) {
			        final int pixel2emu = 12700;
			        CTOneCellAnchor ctAnchor = drawing.getCTDrawing().addNewOneCellAnchor();

			        long cx = (anchor.getTo().getRowOff()-anchor.getFrom().getRowOff())*pixel2emu;
			        long cy = (anchor.getTo().getColOff()-anchor.getFrom().getColOff())*pixel2emu;
			        CTPositiveSize2D size = CTPositiveSize2D.Factory.newInstance();
			        size.setCx(cx);
			        size.setCy(cy);
			        ctAnchor.setExt(size);

			        ctAnchor.setFrom(anchor.getFrom());
			        CTMarker m = ctAnchor.getFrom();
			        m.setColOff(m.getColOff()*pixel2emu);
			        m.setRowOff(m.getRowOff()*pixel2emu);
			        ctAnchor.addNewClientData();
			        try {
			            Method mt = XSSFClientAnchor.class.getDeclaredMethod("setFrom", CTMarker.class);
			            mt.setAccessible(true);
			            mt.invoke(anchor, ctAnchor.getFrom());
			        } catch (Exception e) {
			            throw new RuntimeException("handle me", e);
			        }

			        return ctAnchor;
			    }

}
