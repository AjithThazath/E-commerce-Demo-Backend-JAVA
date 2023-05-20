/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.training.MyShoppingApp.constants.constants;
import com.training.MyShoppingApp.entity.OrderAndProducts;
import com.training.MyShoppingApp.entity.Orders;
import com.training.MyShoppingApp.entity.Product;
import com.training.MyShoppingApp.entity.User;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class PdfService {

	public void generateInvoice(Orders order, HttpServletResponse response)
			throws DocumentException, IOException {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		// title
		Font fontTiltle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTiltle.setSize(20);
		Paragraph paragraph1 = new Paragraph("My Shopping App", fontTiltle);
		paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph1);
		Font invoice = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		invoice.setSize(16);
		Paragraph invoicePara = new Paragraph("Invoice", invoice);
		invoicePara.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(invoicePara);
		Font orderDetailsFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		orderDetailsFont.setColor(CMYKColor.BLACK);

		// User details
		User user = order.getUser();
		Paragraph userName = new Paragraph("Name : " + user.getName(),
				orderDetailsFont);
		document.add(userName);

		Paragraph userAddress = new Paragraph("Address : " + user.getAddress(),
				orderDetailsFont);
		document.add(userAddress);

		Paragraph mobile = new Paragraph("mobile : " + user.getMobile_number(),
				orderDetailsFont);
		document.add(mobile);

		// Order details

		Paragraph orderId = new Paragraph("Order ID : #" + order.getId(),
				orderDetailsFont);
		orderId.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(orderId);
		Paragraph orderDate = new Paragraph(
				"Order Date : " + order.getOrderDate(), orderDetailsFont);
		orderDate.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(orderDate);
		Paragraph total = new Paragraph("Total : " + order.getTotalAmount(),
				orderDetailsFont);
		total.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(total);

		// products table
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100f);
		table.setWidths(new int[]{3, 3, 3, 3});
		table.setSpacingBefore(5);
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(CMYKColor.gray);
		cell.setPadding(5);
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(CMYKColor.BLACK);
		cell.setPhrase(new Phrase("Product ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Product Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Quantity", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Price", font));
		table.addCell(cell);
		List<OrderAndProducts> products = order.getProducts();
		for (OrderAndProducts prod : products) {
			Product p = prod.getProduct();
			table.addCell(String.valueOf(p.getId()));
			table.addCell(String.valueOf(p.getTitle()));
			table.addCell(String.valueOf(p.getQuantity()));
			table.addCell(String.valueOf(p.getPrice()));
		}
		document.add(table);
		Font footerText = FontFactory
				.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
		footerText.setColor(CMYKColor.BLACK);

		Paragraph footer = new Paragraph(constants.OTP_PDF_FOOTER, footerText);
		footer.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(footer);

		document.close();
	}
}
