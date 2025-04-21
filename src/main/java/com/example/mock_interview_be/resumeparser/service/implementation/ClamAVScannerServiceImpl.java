package com.example.mock_interview_be.resumeparser.service.implementation;

import com.example.mock_interview_be.resumeparser.exception.ResumeParseException;
import com.example.mock_interview_be.resumeparser.service.interfaces.ClamAVScannerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.Socket;

@Service
public class ClamAVScannerServiceImpl implements ClamAVScannerService {

//    @Value("${clamav.host}")
    private static final String CLAMAV_HOST = "localhost";

//    @Value("${clamav.port}")
    private static final int CLAMAV_PORT = 3310;

    private static final byte[] STREAM_COMMAND = "zINSTREAM\0".getBytes();

    @Override
    public void scanFile(MultipartFile file) {
        try(Socket socket = new Socket(CLAMAV_HOST, CLAMAV_PORT);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            InputStream fileInputStream = file.getInputStream()){

            out.write(STREAM_COMMAND);

            byte[] buffer = new byte[2048];
            int read;
            while ((read = fileInputStream.read(buffer)) >= 0) {
                byte[] size = {
                        (byte) ((read >> 24) & 0xFF),
                        (byte) ((read >> 16) & 0xFF),
                        (byte) ((read >> 8) & 0xFF),
                        (byte) (read & 0xFF)
                };
                out.write(size);
                out.write(buffer, 0, read);
            }

            out.write(new byte[]{0, 0, 0, 0});
            out.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String result = reader.readLine();

            if (result != null && result.contains("FOUND")) {
                throw new ResumeParseException("Malware detected: " + result);
            }

        }catch (IOException e){
            throw new ResumeParseException("Failed to scan file with ClamAV", e);
        }

    }
}
