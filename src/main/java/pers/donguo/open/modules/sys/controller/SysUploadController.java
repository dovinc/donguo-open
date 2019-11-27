package pers.donguo.open.modules.sys.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
/**
 * !!!暂未使用 ---- 系统上传 控制器
 * <p>Title: SysUploadController.java </p>
 * <p>Description: </p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@Controller
public class SysUploadController {

//跳转到上传文件的页面    

	@RequestMapping(value = "/gouploadimg", method = RequestMethod.GET)

	public String goUploadImg() {

//跳转到 templates 目录下的 uploadimg.html        

		return "uploadimg";

	}

//处理文件上传    

	@ResponseBody // 返回json数据
	@RequestMapping(value = "/testUploadimg", method = RequestMethod.POST)
	public String uploadImg(@RequestParam("file") MultipartFile file,

			HttpServletRequest request) {

//		String contentType = file.getContentType();

		String fileName = file.getOriginalFilename();

		String filePath = "D:/img";

		if (file.isEmpty()) {

			return "文件为空！";

		}

		try {

			uploadFile(file.getBytes(), filePath, fileName);

		} catch (Exception e) {

// TODO: handle exception        

		}

//返回json        

		return "上传成功";

	}

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {

		File targetFile = new File(filePath);

		if (!targetFile.exists()) {

			targetFile.mkdirs();

		}

		FileOutputStream out = new FileOutputStream(filePath + "/" + fileName);

		out.write(file);

		out.flush();

		out.close();

	}

}