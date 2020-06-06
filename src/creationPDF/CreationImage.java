package creationPDF;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

import donnees.Images;

public class CreationImage {
	private Images monImage;
	
	/**
	 * Constructeur
	 * @param Images images
	 */
	public CreationImage(Images images) {
		this.monImage = images;
	}
	
	public Image creerImage() {
		// Creation d un buffer de bytes
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// Transfert de l image dans le buffer de bytes
			ImageIO.write(monImage.getImage(), "png", baos);
			
			// Creation d une instance d Image iText
			Image image = Image.getInstance(baos.toByteArray());
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}