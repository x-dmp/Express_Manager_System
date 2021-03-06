package cn.edu.cuit.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSClientUtil {

	private static TrackerClient trackerClient = null;
	private static TrackerServer trackerServer = null;
	private static StorageServer storageServer = null;
	private static StorageClient1 storageClient = null;

	static {
		try {
			// 加载类路径下配置文件
			ClientGlobal.init(FastDFSClientUtil.class.getResource("/").getPath() + "fast_client.conf");
			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageServer = null;
			storageClient = new StorageClient1(trackerServer, storageServer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件
	 * @param fileName 文件全路径
	 * @param extName 文件扩展名，不包含（.）
	 * @param metas 文件扩展信息
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
		String result = storageClient.upload_file1(fileName, extName, metas);
		return result;
	}

	public static String uploadFile(String fileName) throws Exception {
		return uploadFile(fileName, null, null);
	}

	public static String uploadFile(String fileName, String extName) throws Exception {
		return uploadFile(fileName, extName, null);
	}

	/**
	 * 上传文件方法
	 * @param fileContent 文件的内容，字节数组
	 * @param extName 文件扩展名
	 * @param metas 文件扩展信息
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

		String result = storageClient.upload_file1(fileContent, extName, metas);
		return result;
	}

	public static String uploadFile(byte[] fileContent) throws Exception {
		return uploadFile(fileContent, null, null);
	}

	public static String uploadFile(byte[] fileContent, String extName) throws Exception {
		return uploadFile(fileContent, extName, null);
	}
}
