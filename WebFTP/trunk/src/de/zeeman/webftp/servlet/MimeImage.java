package de.zeeman.webftp.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MimeImage
 */
public class MimeImage extends HttpServlet {
	private static final long serialVersionUID = -3811696214296634501L;
	private enum KnownMimeType {
		JPEG("jpeg.png"), GIF("gif.png"), BMP("bmp.png"), PDF("pdf.png"), UNKNOWN("unkown");
		private String fileName;
		private KnownMimeType(String fileName) {
			this.fileName = fileName;
		}
	}
	private class Image {
		public final byte[] image;
		public Image(byte[] image) {
			this.image = image;
		}
	}
    private final Map<KnownMimeType, Image> imageCache = new HashMap<KnownMimeType, Image>(KnownMimeType.values().length+1, 1.0f);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MimeImage() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mimeType = request.getParameter("mimeType");
		
		if ("image/jpeg".equals(mimeType)) {
			sendImage(KnownMimeType.JPEG, response);
		} else {
			sendImage(KnownMimeType.UNKNOWN, response);
		}
	}
	
	private void sendImage(KnownMimeType mimeType, HttpServletResponse response) throws IOException {
		byte[] image = getImage(mimeType);
		
		//FIXME
		response.setHeader("Content-Type", "mime/png");
		response.setIntHeader("Content-Size", image.length);
		Writer writer = response.getWriter();
		for (byte b : image) {
			writer.write(b);
		}
		writer.flush();
	}

	private byte[] getImage(KnownMimeType mimeType) throws IOException {
		byte[] image = getImageFromCache(mimeType);
		if (image == null) {
			image = readImageFromFS(mimeType.fileName);
			addImageToCache(mimeType, image);
		}
		return image;
	}
	
	private void addImageToCache(KnownMimeType mimeType, byte[] image) {
		synchronized (imageCache) {
			imageCache.put(mimeType, new Image(image));
		}
	}

	private byte[] readImageFromFS(String fileName) throws IOException {
		BufferedReader br = null;
		try {
			Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			File f = new File(fileName);
			byte[] image = new byte[(int)f.length()];
			Reader fr = new FileReader(f);
			br = new BufferedReader(fr);
			int i=0;
			int read;
			while ((read = br.read()) != -1) {
				image[i++] = (byte)read;
			}
			return image;
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	private byte[] getImageFromCache(KnownMimeType mimeType) {
		Image image = imageCache.get(mimeType);
		return image == null ? null : image.image;
	}
}
