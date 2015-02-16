package cn.fyg.pg.interfaces.module.fileup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pg.application.ImgFileService;

@Controller
@RequestMapping("fileimg")
public class FileImgCtl {

	@Autowired
	ImgFileService imgFileService;

	@RequestMapping(value = "{filename:.+}", method = RequestMethod.GET)
	public void fileimg(@PathVariable String filename,
			HttpServletResponse response) {

		FileInputStream fis = null;
		response.setContentType("image/jpg");
		try {
			OutputStream out = response.getOutputStream();
			File file = imgFileService.getImgFile(filename);
			fis = new FileInputStream(file);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			out.write(b);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
