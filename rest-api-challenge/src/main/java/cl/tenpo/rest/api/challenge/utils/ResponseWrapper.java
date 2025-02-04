package cl.tenpo.rest.api.challenge.utils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream capture;
    private final ServletOutputStream outputStream;
    private final PrintWriter writer;

    public ResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        capture = new ByteArrayOutputStream();
        outputStream = new ServletOutputStream() {
            @Override
            public void write(int b) {
                capture.write(b);
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
                // No implementation needed
            }
        };
        writer = new PrintWriter(outputStream, true);
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    public String getCapturedResponse() throws IOException {
        writer.flush();
        return capture.toString("UTF-8");
    }
}