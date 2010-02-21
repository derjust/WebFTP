package de.zeeman.webftp.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import de.zeeman.webftp.domain.SharedFile;

public class ListFilesController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest pRequest,
			HttpServletResponse pResponse) throws Exception {
		
		List<SharedFile> files = new ArrayList<SharedFile>(3);
		SharedFile aFile = new SharedFile();
		aFile.setFileName("Hello World!");
		aFile.setMimeType("mime/jpeg");
		aFile.setSize(30000);
		
		files.add(aFile);
		files.add(aFile);
		files.add(aFile);
		
		
		return new ModelAndView("listFiles", "files", files);
	}

}
